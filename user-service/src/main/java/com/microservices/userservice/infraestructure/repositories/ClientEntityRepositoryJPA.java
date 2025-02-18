package com.microservices.userservice.infraestructure.repositories;

import com.microservices.userservice.infraestructure.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientEntityRepositoryJPA extends JpaRepository<ClientEntity, Integer> {

    @Query(value = """
       select DISTINCT a from ClientEntity a  
       where (:identification IS NULL OR a.personEntity.identification = :identification)  
       """)
    List<ClientEntity> findClientByIdentification(@Param("identification") String identification);


}