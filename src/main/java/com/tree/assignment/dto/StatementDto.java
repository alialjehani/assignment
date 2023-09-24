package com.tree.assignment.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatementDto {
    private long id;
    private double accountId;
    private String datefield;
    private String amount;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAccountId() {
        return accountId;
    }

    public void setAccountId(double accountId) {
        this.accountId = accountId;
    }

    public String getDatefield() {
        return datefield;
    }

    public void setDatefield(String datefield) {
        this.datefield = datefield;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}

