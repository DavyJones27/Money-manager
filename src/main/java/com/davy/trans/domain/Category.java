package com.davy.trans.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    private Integer categoryId;

    private Integer userId;

    private String title;

    private String description;

    private Double totalExpense;
}
