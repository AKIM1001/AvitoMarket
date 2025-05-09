package com.example.Market.repository.jpa.order;

import com.example.Market.entities.order.TransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionLogRepository extends JpaRepository<TransactionLog, Long> {
}
