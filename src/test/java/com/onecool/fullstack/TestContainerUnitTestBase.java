package com.onecool.fullstack;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

public abstract class TestContainerUnitTestBase {

    @Container
    protected static final PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:latest")
                    .withDatabaseName("test-db")
                    .withUsername("postgres")
                    .withPassword("postgres");

}
