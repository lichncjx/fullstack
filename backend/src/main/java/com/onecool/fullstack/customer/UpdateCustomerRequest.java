package com.onecool.fullstack.customer;

import jakarta.annotation.Nullable;

public record UpdateCustomerRequest(
        @Nullable String name,
        @Nullable String email,
        @Nullable Integer age
) {
}
