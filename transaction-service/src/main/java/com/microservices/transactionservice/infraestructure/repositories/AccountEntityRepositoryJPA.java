package com.microservices.transactionservice.infraestructure.repositories;

import com.microservices.transactionservice.infraestructure.entities.AccountEntity;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AccountEntityRepositoryJPA extends JpaRepository<AccountEntity, Long> {


    @Query(value = """
       select DISTINCT a from AccountEntity a 
       LEFT JOIN FETCH a.movementEntity ar 
       where (:accountNumber IS NULL OR a.accountNumber = :accountNumber)  
       order by a.code DESC
       """)
    AccountEntity findAccountEntityByAccountNumber(@Param("accountNumber") String accountNumber);


    @Query(value = """
       select DISTINCT a from AccountEntity a 
       JOIN FETCH a.movementEntity ar 
       where (:identification IS NULL OR a.identification = :identification)  
       and (:startDate IS NULL OR ar.date >= :startDate)\s
       and (:endDate IS NULL OR ar.date <= :endDate)\s
       """)
    List<AccountEntity> findAccountEntityByIdentification(
            @Param("identification") String identification,
            @Param("startDate") @Nullable LocalDateTime startDate,
            @Param("endDate") @Nullable LocalDateTime endDate);

}