package com.example.BuildLedger.model;


import jakarta.persistence.*;  //JPA annotation(entity,table,id,column,id)
import jakarta.validation.constraints.*; //Bean Validation that validate data before saving(notNull,notBlank,size,digit)
import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Column → “What does the column look like in MySQL?”
// @Size (and other validators) → “What inputs will I accept in Java?”



@Entity  //@Entity marks this class as a JPA entity—Hibernate will manage it.
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "deliveries")  // binds it to the deliveries table we created in MySQL.
public class delivery {


    public enum Status {
        PENDING, DELIVERED, ACCEPTED, REJECTED
    }


    @Id //marks it as the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // tells MySQL to AUTO_INCREMENT this ID.
    @Column(name = "delivery_id")
    private Long id;


    @NotNull  //@NotNull and nullable = false ensure you cannot save a delivery without specifying which contract it belongs to.
    @Column(name = "contract_id", nullable = false)
    private Long contractId;

    @NotNull
    @Column(name = "delivery_date", nullable = false)
    private LocalDate deliveryDate;

    @NotBlank  //@NotBlank ensures it’s not null/empty.
    @Size(max = 200)  //aligns with the below VARCHAR(200) in MySQL b/c if u not declare hear it will fail.
    @Column(name = "item", nullable = false, length = 200)
    private String item;

    @NotNull
    @DecimalMin(value = "0.01", inclusive = true, message = "Quantity must be > 0")  //BigDecimal is used because it’s safe for monetary/measurement precision.
    @Digits(integer = 10, fraction = 2) //restrict the numeric shape: up to 10 digits before the decimal and 2 digit after(eg, 1.00, 120.50, 9999999999.99)
    @Column(name = "quantity", nullable = false, precision = 12, scale = 2)  //“Precision 12, scale 2” means max total digits = 12, of which 2 are after the decimal point (so up to 10 digits before decimal).
    private BigDecimal quantity;



    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "ENUM('PENDING','DELIVERED','ACCEPTED','REJECTED')")
    private Status status = Status.PENDING;


    // --- Getters and Setters ---

//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public Long getContractId() { return contractId; }
//    public void setContractId(Long contractId) { this.contractId = contractId; }
//
//    public LocalDate getDeliveryDate() { return deliveryDate; }
//    public void setDeliveryDate(LocalDate deliveryDate) { this.deliveryDate = deliveryDate; }
//
//    public String getItem() { return item; }
//    public void setItem(String item) { this.item = item; }
//
//    public BigDecimal getQuantity() { return quantity; }
//    public void setQuantity(BigDecimal quantity) { this.quantity = quantity; }
//
//    public Status getStatus() { return status; }
//    public void setStatus(Status status) { this.status = status; }

}