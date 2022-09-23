/*
 * Copyright (C) Tamedia AG 2022
 */

package org.example.repository;

import jakarta.inject.Inject;
import org.example.commons.AbstractTest;
import org.example.model.entity.ExampleEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.testcontainers.shaded.org.apache.commons.lang3.RandomUtils.nextLong;

class ExampleEntityRepositoryTest extends AbstractTest {

    @Inject
    private ExampleEntityRepository repository;

    private ExampleEntity entity;

    @BeforeEach
    void setup() {
        repository.deleteAll().block();

        entity = ExampleEntity.builder().exampleData(nextLong()).build();
        repository.save(entity).block();
    }

    @Test
    void updateEntity_callsConverterTwice_fails() {
        //when
        entity.setExampleData(nextLong());

        Assertions.assertDoesNotThrow(() ->
                repository.update(entity).block()
        );
    }
}

