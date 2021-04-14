package com.example.demo.entrypoint;

import com.example.demo.entity.GeoData;
import com.example.demo.entrypoint.dto.GeoDataRequestDto;
import com.example.demo.entrypoint.dto.GeoDataResponseDto;
import com.example.demo.infrastructure.converter.GeoDataConverter;
import com.example.demo.service.GeoDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v1/geo-data")
@RequiredArgsConstructor
public class GeoDataController {
	private final GeoDataService geoDataService;

	@GetMapping
	public List<GeoDataResponseDto> getAll() {
		return geoDataService.findAll().stream()
				.map(GeoDataConverter::convert)
				.collect(Collectors.toList());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GeoDataResponseDto crete(@RequestBody GeoDataRequestDto requestDto) {
		GeoData created = geoDataService.create(GeoDataConverter.convert(requestDto));
		return GeoDataConverter.convert(created);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable long id) {
		GeoData geoData = geoDataService.findById(id);
		geoDataService.delete(geoData);
	}
}
