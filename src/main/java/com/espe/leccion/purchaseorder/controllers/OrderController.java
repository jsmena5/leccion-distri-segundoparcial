package com.espe.leccion.purchaseorder.controllers;

import com.espe.leccion.purchaseorder.dto.OrderRequestDto;
import com.espe.leccion.purchaseorder.modelentities.Order;
import com.espe.leccion.purchaseorder.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/purchase-orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // CREATE
    @PostMapping
    public Order create(@RequestBody @Valid OrderRequestDto dto) {
        return orderService.crear(dto);
    }

    // GET ALL (with filters)
    @GetMapping
    public List<Order> filter(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String currency,
            @RequestParam(required = false) BigDecimal minTotal,
            @RequestParam(required = false) BigDecimal maxTotal,
            @RequestParam(required = false) LocalDateTime from,
            @RequestParam(required = false) LocalDateTime to
    ) {
        return orderService.filtrar(q, status, currency, minTotal, maxTotal, from, to);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id) {
        return orderService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id " + id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public Order update(@PathVariable Long id, @RequestBody @Valid OrderRequestDto dto) {
        // Crear una entidad temporal para actualizar
        Order order = new Order();
        order.setOrderNumber(dto.getOrderNumber());
        order.setSupplierName(dto.getSupplierName());
        order.setStatus(dto.getStatus());
        order.setTotalAmount(dto.getTotalAmount());
        order.setCurrency(dto.getCurrency());
        order.setExpectedDeliveryDate(dto.getExpectedDeliveryDate());

        return orderService.actualizar(id, order)
                .orElseThrow(() -> new RuntimeException("Order not found with id " + id));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderService.eliminarPorId(id);
    }
}