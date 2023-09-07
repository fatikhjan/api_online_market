package com.market.app_online_market.repositores;


import com.market.app_online_market.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepo extends JpaRepository<Product, UUID> {
    Page<Product> findall(Pageable pageble);
}

