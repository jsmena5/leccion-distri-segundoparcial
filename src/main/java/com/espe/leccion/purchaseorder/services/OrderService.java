package com.espe.leccion.purchaseorder.services;

import com.espe.leccion.purchaseorder.dto.OrderRequestDto;
import com.espe.leccion.purchaseorder.modelentities.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> buscarTodos();

    Optional<Order> buscarPorId(Long id);

    Order crear(OrderRequestDto dto);

    Optional<Order> actualizar(Long id, Order order);

    void eliminarPorId(Long id);

    // FALTA ESTE - ¡AGRÉGALO!
    List<Order> filtrar(
            String q,
            String status,
            String currency,
            BigDecimal minTotal,
            BigDecimal maxTotal,
            LocalDateTime from,
            LocalDateTime to
    );
}