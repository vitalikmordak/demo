package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "geo_data")
public class GeoData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "street")
	private String street;

	@Column(name = "city")
	private String city;

	@Column(name = "country")
	private String country;

	@OneToMany(
			mappedBy = "geoData"
//			cascade = CascadeType.ALL,
//			orphanRemoval = true
	)
	private List<User> users;

	public GeoData(String street, String city, String country) {
		this.street = street;
		this.city = city;
		this.country = country;
	}
}
