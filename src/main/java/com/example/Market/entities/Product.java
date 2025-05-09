package com.example.Market.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Builder
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "generator", sequenceName = "products", allocationSize = 1)

    @NonNull
    private Long product_id;

    @NonNull
    private String name;

    @NonNull
    private BigDecimal price;

    @NonNull
    private String description;

    private int amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;


}
