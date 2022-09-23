/*
 * Copyright (C) Tamedia AG 2022
 */

package org.example.commons;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.test.support.TestPropertyProvider;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.TestInstance;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.utility.DockerImageName;

import static java.util.Objects.isNull;

@MicronautTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class AbstractTest implements TestPropertyProvider {

    private static final String MARIA_DB_DOCKER_IMAGE = "mariadb:10.4.8";
    private static final String DATABASE_NAME = "EXAMPLE";
    static MariaDBContainer MARIA_DB_CONTAINER;

    @Override
    public Map<String, String> getProperties() {
        if (isNull(MARIA_DB_CONTAINER)) {
            MARIA_DB_CONTAINER = new MariaDBContainer(DockerImageName.parse(MARIA_DB_DOCKER_IMAGE));
            MARIA_DB_CONTAINER.addEnv("MYSQL_INITDB_SKIP_TZINFO", "1");
            MARIA_DB_CONTAINER.withDatabaseName(DATABASE_NAME);
            MARIA_DB_CONTAINER.withReuse(true);
            MARIA_DB_CONTAINER.start();
        }

        Map<String, String> properties = new HashMap<>();
        properties.put("db.host", MARIA_DB_CONTAINER.getContainerIpAddress() + ":" + MARIA_DB_CONTAINER.getMappedPort(3306).toString());
        properties.put("db.user", MARIA_DB_CONTAINER.getUsername());
        properties.put("db.pass", MARIA_DB_CONTAINER.getPassword());
        properties.put("db.database", MARIA_DB_CONTAINER.getDatabaseName());

        return properties;
    }
}
