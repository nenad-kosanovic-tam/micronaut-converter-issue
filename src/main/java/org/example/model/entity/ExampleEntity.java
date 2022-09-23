/*
 * Copyright (C) Tamedia AG 2022
 */

package org.example.model.entity;

import io.micronaut.data.annotation.*;
import io.micronaut.data.model.DataType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.converter.MySqlUuidBinaryConverter;

import java.util.UUID;


@Data
@Builder
@MappedEntity
@NoArgsConstructor
@AllArgsConstructor
public class ExampleEntity {

    @Id
    @AutoPopulated
    @MappedProperty(definition = "binary(16)")
    @TypeDef(type = DataType.BYTE_ARRAY, converter = MySqlUuidBinaryConverter.class)
    private UUID id;

    private Long exampleData;
}
