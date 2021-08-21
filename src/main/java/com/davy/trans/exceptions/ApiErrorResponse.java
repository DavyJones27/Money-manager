package com.davy.trans.exceptions;

import lombok.Data;

import java.util.List;

@Data

public class ApiErrorResponse {

    private String errorId;

    private Integer status;

    private String message;

    private List<String> errors;

    private String path;
}
