package com.aumentodeparcial.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aumentodeparcial.demo.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}

