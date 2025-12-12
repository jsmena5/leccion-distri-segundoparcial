package com.espe.leccion.purchaseorder.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderRequestDto {

    @NotBlank(message = "El número de orden no puede estar vacío")
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

    @NotNull(message = "La fecha esperada de entrega no puede ser nula")
    private LocalDate expectedDeliveryDate;

    // Getters & setters
    // Getters y Setters
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

    public LocalDate getExpectedDeliveryDate() { return expectedDeliveryDate; }
    public void setExpectedDeliveryDate(LocalDate expectedDeliveryDate) { this.expectedDeliveryDate = expectedDeliveryDate; }
}