package com.davy.trans.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    private Integer transactionId;

    private Integer categoryId;

    private Integer userId;

    private Double amount;

    private String note;

    private Long transactionDate;

}
