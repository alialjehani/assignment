package com.tree.assignment.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatementDto {
    private Long id;
    private String accountId;
    private Date datefield;
    private BigDecimal amount;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Date getDatefield() {
        return datefield;
    }

    public void setDatefield(Date datefield) {
        this.datefield = datefield;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

