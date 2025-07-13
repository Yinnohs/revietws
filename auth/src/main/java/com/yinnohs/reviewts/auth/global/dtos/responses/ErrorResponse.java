package com.yinnohs.reviewts.auth.global.dtos.responses;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {

}