package com.sophos.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Troncal")
public class Troncal implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "idTroncal", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Id
	@Column(nullable = false)
	private String codTroncal;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return codTroncal;
	}

	public void setNombre(String nombre) {
		this.codTroncal = nombre;
	}
	
}
