package com.yinnohs.reviwts.reviewProcesor.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yinnohs.reviwts.reviewProcesor.application.dto.IncomingReviewEvent;
import com.yinnohs.reviwts.reviewProcesor.application.usecase.ProcessCreateReviewUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class KafkaReviewEventStoreConnector {

    private final ObjectMapper mapper;
    private final ProcessCreateReviewUseCase createUseCase;

    @KafkaListener(topics = "review_write.public.review_event_store", groupId = "review-orquestrator-group")
    public void consume(GenericRecord record) throws JsonProcessingException {
        // Debezium wraps the actual data in an 'after' field in the Avro record.
        GenericRecord payload = (GenericRecord) record.get("after");
        String eventRawData = payload.get("data").toString();
        String eventName = payload.get("event_name").toString();
        if (eventRawData == null)  return;
        log.info("Consumed event: name: {} data: {}", eventName, eventRawData);
        IncomingReviewEvent event = mapper.readValue(eventRawData, IncomingReviewEvent.class);
        createUseCase.execute(event);
    }
}
