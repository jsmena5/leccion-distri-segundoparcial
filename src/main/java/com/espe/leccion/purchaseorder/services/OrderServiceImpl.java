package com.espe.leccion.purchaseorder.services;

import com.espe.leccion.purchaseorder.dto.OrderRequestDto;
import com.espe.leccion.purchaseorder.modelentities.Order;
import com.espe.leccion.purchaseorder.repositories.OrderInterface;
import com.espe.leccion.purchaseorder.specifications.OrderSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderInterface orderRepo;

    @Override
    public List<Order> buscarTodos() {
        return orderRepo.findAll();
    }

    @Override
    public Optional<Order> buscarPorId(Long id) {
        return orderRepo.findById(id);
    }

    @Override
    public Order crear(OrderRequestDto dto) {

        Order order = new Order();

        order.setOrderNumber(dto.getOrderNumber());
        order.setSupplierName(dto.getSupplierName());
        order.setStatus(dto.getStatus());
        order.setTotalAmount(dto.getTotalAmount());
        order.setCurrency(dto.getCurrency());
        order.setExpectedDeliveryDate(dto.getExpectedDeliveryDate());
        order.setCreatedAt(LocalDateTime.now());

        return orderRepo.save(order);
    }
    @Override
    public Optional<Order> actualizar(Long id, Order order) {
        return orderRepo.findById(id).map(existing -> {
            existing.setOrderNumber(order.getOrderNumber());
            existing.setSupplierName(order.getSupplierName());
            existing.setStatus(order.getStatus());
            existing.setCurrency(order.getCurrency());
            existing.setTotalAmount(order.getTotalAmount());
            existing.setExpectedDeliveryDate(order.getExpectedDeliveryDate());
            return orderRepo.save(existing);
        });
    }

    @Override
    public void eliminarPorId(Long id) {
        orderRepo.deleteById(id);
    }

    @Override
    public List<Order> filtrar(String q, String status, String currency,
                               BigDecimal minTotal, BigDecimal maxTotal,
                               LocalDateTime from, LocalDateTime to) {

        if (from != null && to != null && from.isAfter(to)) {
            throw new IllegalArgumentException("La fecha 'from' no puede ser mayor que 'to'");
        }

        Specification<Order> spec = Specification
                .where(OrderSpecifications.textSearch(q))
                .and(OrderSpecifications.hasStatus(status))
                .and(OrderSpecifications.hasCurrency(currency))
                .and(OrderSpecifications.minTotal(minTotal))
                .and(OrderSpecifications.maxTotal(maxTotal))
                .and(OrderSpecifications.fromDate(from))
                .and(OrderSpecifications.toDate(to));

        return orderRepo.findAll(spec);
    }
}