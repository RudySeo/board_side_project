package sideproject.board.order.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import sideproject.board.order.domain.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
