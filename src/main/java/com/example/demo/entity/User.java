package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * User entity which uses for mapped data from/to Database
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
	/**
	 * User's ID.
	 * Set to auto incrementing User's ID with {@code <code>@GeneratedValue (strategy = GenerationType.IDENTITY)</code>}
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private long id;

	/**
	 * User's first name
	 */
	@Column(name = "first_name")
	private String firstName;

	/**
	 * User's last name
	 */
	@Column(name = "last_name")
	private String lastName;

	/**
	 * User's Password. Should be saved as encoded.
	 */
	@Column(name = "password")
	private String password;

	/**
	 * User's geographical data.
	 * Set Many to One relationship with 'join geographical data's ID' column in user table.
	 */
	@ManyToOne
	@JoinColumn(name = "geo_data_id")
	private GeoData geoData;

	public User(String firstName, String lastName, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}
}
