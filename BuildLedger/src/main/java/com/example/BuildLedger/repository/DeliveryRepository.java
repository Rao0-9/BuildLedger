package com.example.BuildLedger.repository;

import com.example.BuildLedger.model.Delivery;
import com.example.BuildLedger.model.Delivery.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {



//    List<Delivery> findByContractId(Long contractId);
//    List<Delivery> findByStatus(Status status);
//    List<Delivery> findByDeliveryDateBetween(LocalDate start, LocalDate end);
}