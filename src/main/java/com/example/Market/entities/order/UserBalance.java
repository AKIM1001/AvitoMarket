package com.example.Market.entities.order;

import jakarta.persistence.Id;

import java.math.BigDecimal;

public class UserBalance {

    @Id
    private Long userId;

    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
