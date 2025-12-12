package com.espe.leccion.purchaseorder.repositories;

import com.espe.leccion.purchaseorder.modelentities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderInterface extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
}