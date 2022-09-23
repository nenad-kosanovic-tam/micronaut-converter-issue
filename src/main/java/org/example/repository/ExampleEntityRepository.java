/*
 * Copyright (C) Tamedia AG 2022
 */

package org.example.repository;

import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import org.example.model.entity.ExampleEntity;

import java.util.UUID;

@R2dbcRepository(dialect = Dialect.MYSQL)
public interface ExampleEntityRepository extends ReactorCrudRepository<ExampleEntity, UUID> {
}

