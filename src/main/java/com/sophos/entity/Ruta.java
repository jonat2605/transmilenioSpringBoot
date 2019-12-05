package com.sophos.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Ruta")
public class Ruta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "idRuta", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Id
	@Column(nullable = false, length = 3)
	private String codRuta;
	
	@NotBlank
	private String nombre;
	
	@JsonFormat(timezone = "UTC", pattern = "HH:mm:ss")
	@Temporal(TemporalType.TIME)
	private Date inicioOperacion; 
	
	@JsonFormat(timezone = "UTC", pattern = "HH:mm:ss")
	@Temporal(TemporalType.TIME)	
	private Date finOperacion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodRuta() {
		return codRuta;
	}

	public void setCodRuta(String codRuta) {
		this.codRuta = codRuta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getInicioOperacion() {
		return inicioOperacion;
	}

	public void setInicioOperacion(Date inicioOperacion) {
		this.inicioOperacion = inicioOperacion;
	}

	public Date getFinOperacion() {
		return finOperacion;
	}

	public void setFinOperacion(Date finOperacion) {
		this.finOperacion = finOperacion;
	}
	
}
