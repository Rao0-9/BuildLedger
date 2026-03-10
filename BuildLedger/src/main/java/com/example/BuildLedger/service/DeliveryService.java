package com.example.BuildLedger.service;

import com.example.BuildLedger.model.Delivery;
//import com.example.BuildLedger.domain.Delivery.Status;
import com.example.BuildLedger.repository.DeliveryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {
    private DeliveryRepository repo;

    public DeliveryService(DeliveryRepository repo) {
        this.repo = repo;
    }

    public Delivery create(Delivery d) {
        return repo.save(d);
    }

    public List<Delivery> list() {
        return repo.findAll();
    }

    public Optional<Delivery> get(Long id) {
        return repo.findById(id);
    }

//    public List<Delivery> findByContract(Long contractId) {
//        return repo.findByContractId(contractId);
//    }
//
//    public List<Delivery> findByStatus(Status status) {
//        return repo.findByStatus(status);
//    }
//
//    public List<Delivery> findByDateRange(LocalDate start, LocalDate end) {
//        return repo.findByDeliveryDateBetween(start, end);
//    }

    public Optional<Delivery> update(Long id, Delivery updated) {
        return repo.findById(id).map(existing -> {
            // Only update a few fields for now (simple)
            existing.setContractId(updated.getContractId());
            existing.setDeliveryDate(updated.getDeliveryDate());
            existing.setItem(updated.getItem());
            existing.setQuantity(updated.getQuantity());
            existing.setStatus(updated.getStatus());
            return repo.save(existing);
        });
    }

    public boolean delete(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}