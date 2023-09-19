package com.market.app_online_market.domain;

import com.market.app_online_market.domain.template.ABCEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity(name = "Products")
@Getter
@Setter
@ToString
@RequiredArgsConstructor

public class Product extends ABCEntity {

    private String name;
    private String price;
    private String colors;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return getId() != null && Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
