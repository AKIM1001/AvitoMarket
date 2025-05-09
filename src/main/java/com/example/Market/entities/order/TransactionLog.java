package com.example.Market.entities.order;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
@Table(name = "Transaction_History")
public class TransactionLog {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @SequenceGenerator(name = "generator", sequenceName = "trans_log", allocationSize = 1)
        private Long order_id;

        private Long userId;
        private String action;
        private LocalDateTime timestamp;

}
