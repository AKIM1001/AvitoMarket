package com.example.Market.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "generator", sequenceName = "users", allocationSize = 1)
    @Setter(AccessLevel.NONE)

    @NonNull
    private Long user_id;

    @NonNull
    private String name;

    @NonNull
    private String email;

    @NonNull
    private String password;

    private double funds;

}
