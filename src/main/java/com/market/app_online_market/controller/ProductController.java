package com.market.app_online_market.controller;

import com.market.app_online_market.domain.Product;
import com.market.app_online_market.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BaseController.baseController + ProductController.path)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    public static final String path = "/products/";

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/all")
    public Page<Product> getAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return productService.getAllProducts(pageable);
    }


}
