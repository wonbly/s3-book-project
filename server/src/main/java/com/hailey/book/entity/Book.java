package com.hailey.book.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 고유 번호

    private String title; // 책 제목
    private Integer price; // 가격

    @Column(columnDefinition = "TEXT")
    private String imageUrl; // S3에 저장된 이미지 주소
}