package com.musinsa.admin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "product",
        uniqueConstraints = @UniqueConstraint(
        name = "uk_product_name_price_category_brand",
        columnNames = {"name", "price", "category_id", "brand_id"}))
@Getter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE product SET is_active = false WHERE id = ?")
@Where(clause = "is_active = true")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private BrandEntity brand;

    @Column(name = "is_active")
    private boolean isActive = true;

    @Builder
    public ProductEntity(long id,String name, Long price, CategoryEntity category, BrandEntity brand, boolean isActive) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.brand = brand;
        this.isActive = isActive;
    }


}
