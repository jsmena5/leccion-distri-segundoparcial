package com.espe.leccion.purchaseorder.modelentities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "purchase_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El número de orden no puede estar vacío")
    @Column(unique = true)
    private String orderNumber;

    @NotBlank(message = "El nombre del proveedor no puede estar vacío")
    private String supplierName;

    @NotBlank(message = "El estado no puede estar vacío")
    private String status;

    @NotNull(message = "El total no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = "El total debe ser mayor a 0")
    private BigDecimal totalAmount;

    @NotBlank(message = "La moneda no puede estar vacía")
    private String currency;

    @NotNull(message = "La fecha de creación no puede ser nula")
    private LocalDateTime createdAt;

    @NotNull(message = "La fecha esperada de entrega no puede ser nula")
    private LocalDate expectedDeliveryDate;

    public Order() {}

    // Constructor
    public Order(Long id, String orderNumber, String supplierName, String status,
                 BigDecimal totalAmount, String currency,
                 LocalDateTime createdAt, LocalDate expectedDeliveryDate) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.supplierName = supplierName;
        this.status = status;
        this.totalAmount = totalAmount;
        this.currency = currency;
        this.createdAt = createdAt;
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }

    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDate getExpectedDeliveryDate() { return expectedDeliveryDate; }
    public void setExpectedDeliveryDate(LocalDate expectedDeliveryDate) { this.expectedDeliveryDate = expectedDeliveryDate; }
}