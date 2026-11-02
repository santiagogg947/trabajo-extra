package com.aumentodeparcial.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.aumentodeparcial.demo.dto.ProductoRequest;
import com.aumentodeparcial.demo.dto.ProductoResponse;
import com.aumentodeparcial.demo.entity.Producto;
import com.aumentodeparcial.demo.exception.ProductoNoEncontradoException;
import com.aumentodeparcial.demo.repository.ProductoRepository;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Transactional
    public ProductoResponse crear(ProductoRequest request) {
        Producto producto = new Producto(
                request.nombre().trim(),
                request.descripcion(),
                request.precio(),
                request.stock());
        return ProductoResponse.desde(productoRepository.save(producto));
    }

    @Transactional(readOnly = true)
    public List<ProductoResponse> listar() {
        return productoRepository.findAll().stream()
                .map(ProductoResponse::desde)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProductoResponse buscarPorId(Long id) {
        return productoRepository.findById(id)
                .map(ProductoResponse::desde)
                .orElseThrow(() -> new ProductoNoEncontradoException(id));
    }

    @Transactional
    public void eliminar(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ProductoNoEncontradoException(id);
        }
        productoRepository.deleteById(id);
    }

}
