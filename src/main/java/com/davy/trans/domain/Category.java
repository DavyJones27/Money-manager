package com.davy.trans.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@JsonIgnoreProperties({"categoryId", "userId"})
public class Category {

    private Integer categoryId;

    private Integer userId;

    private String title;

    private String description;

//    @JsonIgnore
    private Double totalExpense;
}
