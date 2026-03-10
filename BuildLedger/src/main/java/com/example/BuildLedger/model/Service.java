package com.example.BuildLedger.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "services")
public class Service {

    public enum Status {
        PENDING, DELIVERED, ACCEPTED, REJECTED
    }

    // --- Primary Key (ServiceID) ---
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")  //column name
    private Long id;


    @NotNull
    @Column(name = "contract_id", nullable = false)
    private Long contractId;

    // --- Description (what service task was done / to be done) ---
    @NotBlank
    @Lob // Large Object. It tells JPA/Hibernate that this field can store large content.
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    // --- CompletionDate (date-only, timezone independent) ---
    @Column(name = "completion_date")
    private LocalDate completionDate;

    // --- Status (enum stored as STRING/ENUM in DB) ---
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(
            name = "status",
            nullable = false,
            columnDefinition = "ENUM('PENDING','IN_PROGRESS','COMPLETED','ACCEPTED','REJECTED')"
    )
    private Status status = Status.PENDING;

    // --- Getters & Setters ---

//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public Long getContractId() { return contractId; }
//    public void setContractId(Long contractId) { this.contractId = contractId; }
//
//    public String getDescription() { return description; }
//    public void setDescription(String description) { this.description = description; }
//
//    public LocalDate getCompletionDate() { return completionDate; }
//    public void setCompletionDate(LocalDate completionDate) { this.completionDate = completionDate; }
//
//    public Status getStatus() { return status; }
//    public void setStatus(Status status) { this.status = status; }


}