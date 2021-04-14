package com.example.demo.infrastructure.converter;

import com.example.demo.entity.GeoData;
import com.example.demo.entrypoint.dto.GeoDataRequestDto;
import com.example.demo.entrypoint.dto.GeoDataResponseDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GeoDataConverter {

	public GeoData convert(GeoDataRequestDto dto) {
		return new GeoData(dto.getStreet(), dto.getCity(), dto.getCountry());
	}

	public GeoDataResponseDto convert(GeoData geoData) {
		if (geoData == null) return null;
		return new GeoDataResponseDto(geoData.getId(), geoData.getStreet(), geoData.getCity(), geoData.getCountry());
	}
}
