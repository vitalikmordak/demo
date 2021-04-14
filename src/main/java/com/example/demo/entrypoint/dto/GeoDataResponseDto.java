package com.example.demo.entrypoint.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeoDataResponseDto {

	private long id;

	private String street;

	private String city;

	private String country;
}
