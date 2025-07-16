package com.yinnohs.reviewts.auth.auth.infrastructure.repositories;

import com.yinnohs.reviewts.auth.auth.infrastructure.models.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsSqlRepository extends JpaRepository<AccountModel, String> {
    Optional<AccountModel> findByEmail(String email);
}
