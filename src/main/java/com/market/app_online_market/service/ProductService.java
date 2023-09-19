package com.market.app_online_market.service;

import com.market.app_online_market.domain.Product;
import com.market.app_online_market.repositores.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;


    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepo.findAll(pageable);
    }
}
