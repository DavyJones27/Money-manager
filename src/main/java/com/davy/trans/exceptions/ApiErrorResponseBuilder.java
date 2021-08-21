package com.davy.trans.exceptions;

import java.util.List;

public class ApiErrorResponseBuilder {

    private static ApiErrorResponseBuilder builder;
    private String errorId;
    private Integer status;
    private String message;
    private List<String> errors;
    private String path;

    private ApiErrorResponseBuilder() {

    }

    public synchronized static ApiErrorResponseBuilder getInstance() {
        if (builder == null) {
            builder = new ApiErrorResponseBuilder();
        }

        return builder;
    }

    public ApiErrorResponseBuilder withErrorId(String errorId) {
        builder.errorId = errorId;
        return builder;
    }

    public ApiErrorResponseBuilder withStatus(Integer status) {
        builder.status = status;
        return builder;
    }

    public ApiErrorResponseBuilder withErrors(List<String> errors) {
        builder.errors = errors;
        return builder;
    }

    public ApiErrorResponseBuilder withMessage(String message) {
        builder.message = message;
        return builder;
    }

    public ApiErrorResponseBuilder forPath(String path) {
        builder.path = path;
        return builder;
    }

    public ApiErrorResponse build() {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setErrors(builder.errors);
        apiErrorResponse.setErrorId(builder.errorId);
        apiErrorResponse.setMessage(builder.message);
        apiErrorResponse.setPath(builder.path);
        apiErrorResponse.setStatus(builder.status);

        return apiErrorResponse;
    }
}
