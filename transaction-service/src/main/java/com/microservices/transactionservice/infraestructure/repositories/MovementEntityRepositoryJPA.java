package com.microservices.transactionservice.infraestructure.repositories;

import com.microservices.transactionservice.infraestructure.entities.MovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovementEntityRepositoryJPA extends JpaRepository<MovementEntity, Integer> {


}