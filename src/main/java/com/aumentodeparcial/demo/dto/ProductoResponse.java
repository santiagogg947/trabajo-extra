package com.aumentodeparcial.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.aumentodeparcial.demo.entity.Producto;

public record ProductoResponse(
        Long id,
        String nombre,
        String descripcion,
        BigDecimal precio,
        Integer stock,
        LocalDateTime creadoEn) {

    public static ProductoResponse desde(Producto producto) {
        return new ProductoResponse(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getCreadoEn());
    }
}