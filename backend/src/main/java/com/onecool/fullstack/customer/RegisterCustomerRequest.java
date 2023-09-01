package com.onecool.fullstack.customer;

import jakarta.annotation.Nonnull;

public record RegisterCustomerRequest(
        @Nonnull String name,
        @Nonnull String email,
        @Nonnull Integer age
) {
}
