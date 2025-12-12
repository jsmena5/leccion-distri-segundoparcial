package com.espe.leccion.purchaseorder.specifications;

import com.espe.leccion.purchaseorder.modelentities.Order;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderSpecifications {

    // 1) q - búsqueda en orderNumber y supplierName (case-insensitive)
    public static Specification<Order> textSearch(String q) {
        return (root, query, cb) -> {
            if (q == null || q.trim().isEmpty()) return null;

            String pattern = "%" + q.toLowerCase() + "%";

            return cb.or(
                    cb.like(cb.lower(root.get("orderNumber")), pattern),
                    cb.like(cb.lower(root.get("supplierName")), pattern)
            );
        };
    }

    // 2) status - solo valores válidos
    public static Specification<Order> hasStatus(String status) {
        return (root, query, cb) -> {
            if (status == null || status.isBlank()) return null;
            return cb.equal(root.get("status"), status);
        };
    }

    // 3) currency - exact match
    public static Specification<Order> hasCurrency(String currency) {
        return (root, query, cb) -> {
            if (currency == null || currency.isBlank()) return null;
            return cb.equal(root.get("currency"), currency);
        };
    }

    // 4) minTotal - totalAmount >= minTotal
    public static Specification<Order> minTotal(BigDecimal minTotal) {
        return (root, query, cb) -> {
            if (minTotal == null) return null;
            return cb.greaterThanOrEqualTo(root.get("totalAmount"), minTotal);
        };
    }

    // 5) maxTotal - totalAmount <= maxTotal
    public static Specification<Order> maxTotal(BigDecimal maxTotal) {
        return (root, query, cb) -> {
            if (maxTotal == null) return null;
            return cb.lessThanOrEqualTo(root.get("totalAmount"), maxTotal);
        };
    }

    // 6) Fechas: createdAt >= from
    public static Specification<Order> fromDate(LocalDateTime from) {
        return (root, query, cb) -> {
            if (from == null) return null;
            return cb.greaterThanOrEqualTo(root.get("createdAt"), from);
        };
    }

    // createdAt <= to
    public static Specification<Order> toDate(LocalDateTime to) {
        return (root, query, cb) -> {
            if (to == null) return null;
            return cb.lessThanOrEqualTo(root.get("createdAt"), to);
        };
    }
}