package com.tree.assignment.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Account {
    @Id
    private Long id;
    private String account_type;
    private String account_number;
    private String test;

}
