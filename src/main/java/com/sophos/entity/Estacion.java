package com.sophos.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Estacion")
public class Estacion implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "idEstacion", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Id
	@Column(nullable = false, length = 3)
	private String codEstacion;
	
	@NotBlank
	private String nombre;
	
	@NotBlank
	private String estado;
	
	@JsonFormat(timezone = "UTC", pattern = "HH:mm:ss")
	@Temporal(TemporalType.TIME)
	private Date horaApertura;
	
	@JsonFormat(timezone = "UTC", pattern = "HH:mm:ss")
	@Temporal(TemporalType.TIME)
	private Date horaCierre;
	
	@NotBlank
	private String codTroncal;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodEstacion() {
		return codEstacion;
	}

	public void setCodEstacion(String codEstacion) {
		this.codEstacion = codEstacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getHoraApertura() {
		return horaApertura;
	}

	public void setHoraApertura(Date horaApertura) {
		this.horaApertura = horaApertura;
	}

	public Date getHoraCierre() {
		return horaCierre;
	}

	public void setHoraCierre(Date horaCierre) {
		this.horaCierre = horaCierre;
	}

	public String getCodTroncal() {
		return codTroncal;
	}

	public void setCodTroncal(String codTroncal) {
		this.codTroncal = codTroncal;
	}
	
//	/* RUTA_ESTACION*/
//	@ManyToOne
//	@JoinColumn(name = "id_ruta")
//	private Ruta ruta;
//	/*RUTA*/
//	
//	@OneToMany(mappedBy = "ruta")
//	private List<RUTA_ESTACION> listRutaEstacion;
	
	
}
