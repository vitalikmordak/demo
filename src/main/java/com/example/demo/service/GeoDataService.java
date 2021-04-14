package com.example.demo.service;

import com.example.demo.entity.GeoData;
import com.example.demo.entity.User;
import com.example.demo.exception.NoGeoDataFoundException;
import com.example.demo.exception.NoUserFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeoDataService {
	private final GeoDataRepository geoDataRepository;

	public List<GeoData> findAll() {
		return geoDataRepository.findAll();
	}

	@Transactional
	public GeoData create(GeoData geoData) {
		return geoDataRepository.save(geoData);
	}

	@Transactional
	public void delete(GeoData geoData) {
		for (User user : geoData.getUsers()) {
			user.setGeoData(null);
		}
		geoDataRepository.delete(geoData);
	}

	public GeoData findById(long geoDataId) {
		return geoDataRepository.findById(geoDataId).orElseThrow(() -> new NoGeoDataFoundException(geoDataId));
	}
}
