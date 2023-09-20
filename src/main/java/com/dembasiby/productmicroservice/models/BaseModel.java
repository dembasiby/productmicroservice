package com.dembasiby.productmicroservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Data
@MappedSuperclass
public class BaseModel {
    @Id
    @GenericGenerator(name = "UUID", type = org.hibernate.id.uuid.UuidGenerator.class)
    @Column(name="id", columnDefinition = "binary(16)", nullable = false, updatable = false)
    private UUID uuid;
}
