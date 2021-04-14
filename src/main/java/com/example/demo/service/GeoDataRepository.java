package com.example.demo.service;

import com.example.demo.entity.GeoData;

import java.util.List;
import java.util.Optional;

public interface GeoDataRepository {

	List<GeoData> findAll();

	Optional<GeoData> findById(long id);

	GeoData save(GeoData geoData);

	void delete(GeoData geoData);
}
