package com.dembasiby.productmicroservice.repositories;

import com.dembasiby.productmicroservice.models.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PriceRepository extends JpaRepository<Price, UUID> {
}
