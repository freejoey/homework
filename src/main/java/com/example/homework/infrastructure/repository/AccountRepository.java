package com.example.homework.infrastructure.repository;

import com.example.homework.infrastructure.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Transactional
    @Modifying
    @Query(value = "update Account a set a.properties = a.properties + :delta where a.uid = :uid")
    int updatePropertiesByUid(@Param("uid") long uid, @Param("delta") BigDecimal delta);
}
