package sideproject.board.product.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sideproject.board.product.domain.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
