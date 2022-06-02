package com.example.myshoppingapp.service;

import com.example.myshoppingapp.exception.NotFoundException;
import com.example.myshoppingapp.model.Product;
import com.example.myshoppingapp.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository repository;

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    @SneakyThrows
    public Product findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Product not found!"));
    }
}
