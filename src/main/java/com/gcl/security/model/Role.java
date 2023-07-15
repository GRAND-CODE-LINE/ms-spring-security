package com.gcl.security.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotBlank;

@Document(collection = "roles")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Role {
	@Id
	private String id;
	@NotBlank
	private ERole name;
	private String description;

	public Role(String id, @NotBlank ERole name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}