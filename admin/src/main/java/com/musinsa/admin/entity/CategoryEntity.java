package com.musinsa.admin.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @Column(name = "is_active")
    private boolean isActive = true;

    @Column(name = "create_ymdt")
    @CreatedDate
    private LocalDateTime createYmdt;

    @Column(name = "updated_ymdt")
    @LastModifiedDate
    private LocalDateTime updatedYmdt;

    @Builder
    public CategoryEntity(String name) {
        this.name = name;
    }

}
