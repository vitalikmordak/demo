package com.example.demo.infrastructure.repository;

import com.example.demo.entity.GeoData;
import com.example.demo.service.GeoDataRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringGeoDataRepository extends JpaRepository<GeoData, Long>, GeoDataRepository {
}
