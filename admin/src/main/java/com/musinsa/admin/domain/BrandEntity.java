package com.musinsa.admin.domain;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "brand")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "is_active")
    private boolean active = true;

//    @Column(name = "create_ymdt")
//    private LocalDateTime createYmdt;
//
//    @Column(name = "updated_ymdt")
//    private LocalDateTime updatedYmdt;
//
//    @PrePersist
//    public void prePersist() {
//        this.createYmdt = LocalDateTime.now();
//        this.updatedYmdt = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    public void preUpdate() {
//        this.updatedYmdt = LocalDateTime.now();
//    }

    @Builder
    public BrandEntity(String name) {
        this.name = name;
    }
}