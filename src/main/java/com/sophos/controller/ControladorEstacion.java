package com.sophos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sophos.entity.Estacion;
import com.sophos.entity.MessageResponse;
import com.sophos.entity.Troncal;
import com.sophos.model.RepositorioEstacion;
import com.sophos.model.RepositorioTroncal;

@CrossOrigin
@RestController
public class ControladorEstacion {
	
	 @Autowired
	 private RepositorioEstacion repositorioEstacion;
	 
	 @Autowired
	 private RepositorioTroncal repositorioTroncal;
	 
	@PostMapping(value = "api/estacion")
	public ResponseEntity<MessageResponse> crearEstacion(@RequestBody Estacion estacion) {
		MessageResponse message = comprobarAtributos(estacion);
		if (message == null) {
			if (comprobarCodTroncal(estacion.getCodTroncal())) {
				if (comprobarId(estacion.getCodEstacion())) {
					char[] charsCodigo = estacion.getCodEstacion().toCharArray();
					if (charsCodigo.length == 3) {
						repositorioEstacion.save(estacion);
						message = new MessageResponse("Estación agregada con éxito", 201);
						return new ResponseEntity<MessageResponse>(message, HttpStatus.CREATED);
					} else {
						message = new MessageResponse(
								"El código de la estación debe tener 3 caracteres como mínimo y máximo", 409);
						return new ResponseEntity<MessageResponse>(message, HttpStatus.CONFLICT);
					}
				} else {
					message = new MessageResponse("El código de la estación ya existe", 409);
					return new ResponseEntity<MessageResponse>(message, HttpStatus.CONFLICT);
				}
			} else {
				message = new MessageResponse("El código de la troncal no existe", 409);
				return new ResponseEntity<MessageResponse>(message, HttpStatus.CONFLICT);
			}
		} else {
			return new ResponseEntity<MessageResponse>(message, HttpStatus.BAD_REQUEST);
		}
	}
	 
	 @PostMapping(value = "api/estaciones")
	 public ResponseEntity<String> crearEstaciones(@RequestBody List<Estacion> estaciones){
		 if(estaciones.isEmpty())
			 return new ResponseEntity<String>("No puede agregar datos vacíos", HttpStatus.BAD_REQUEST);
		 else {
			 repositorioEstacion.saveAll(estaciones);
			 return new ResponseEntity<String>("Estaciones agregadas con éxito", HttpStatus.CREATED);
		 }
	 }
	 
	 @GetMapping(value = "api/estacion/{codEstacion}")
	 public ResponseEntity<Estacion> obtenerEstacion(@PathVariable String codEstacion){
		Optional<Estacion> estacion = repositorioEstacion.findById(codEstacion);
		 return estacion.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	 }
	 
	 @GetMapping(value = "api/estaciones")
	 public ResponseEntity<List<Estacion>> obtenerEstaciones(){
		 List<Estacion> estaciones = (List<Estacion>) repositorioEstacion.findAll();
		 return new ResponseEntity<List<Estacion>>(estaciones, HttpStatus.OK);
	 }
	 
	 @DeleteMapping(value = "api/estacion/{codEstacion}")
	 public ResponseEntity<String> borrarEstacion(@PathVariable String codEstacion){
		 try {
			 repositorioEstacion.deleteById(codEstacion);
		} catch (Exception e) {
			return new ResponseEntity<String>("No se encuentra el código de la estación", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Estación eliminada con éxito", HttpStatus.OK);
	 }
	 
	 @PutMapping(value = "api/estacion")
	 public ResponseEntity<String> actualizarEstacion(@RequestBody Estacion estacion){
		 if(!comprobarId(estacion.getCodEstacion())) {
			 repositorioEstacion.save(estacion);
			 return new ResponseEntity<>("Estación modificada con éxito", HttpStatus.OK); 
		 }else
			 return new ResponseEntity<String>("La estación no existe", HttpStatus.NOT_FOUND);
	 }
	 
	 private boolean comprobarId(String id) {
		 boolean condicion = true;
		 List<Estacion> estaciones = (List<Estacion>) repositorioEstacion.findAll();
		 for(Estacion e : estaciones) {
			 if(e.getCodEstacion().equals(id)) {
				 condicion = false;
				 break;
			 }
		 }
		 return condicion;
	 }
	 
	 private boolean comprobarCodTroncal(String codTroncal) {
		 boolean condicion = false;
		 List<Troncal> troncales = (List<Troncal>) repositorioTroncal.findAll();
		 for(Troncal t : troncales) {
			 if(t.getCodTroncal().equals(codTroncal)) {
				 condicion = true;
				 break;
			 }
		 }
		 return condicion;
	 }
	 
	 private MessageResponse comprobarAtributos(Estacion estacion) {
		 MessageResponse mensajeNulo = null;
		 if(estacion.getCodEstacion() == null || estacion.getCodEstacion().equals("")) 
			mensajeNulo = new MessageResponse("El campo código de estación no puede estar vacío", 400);
		 else
			 if(estacion.getNombre() == null || estacion.getNombre().equals(""))
				 mensajeNulo = new MessageResponse("El campo nombre no puede estar vacío", 400);
			 else
				 if(estacion.getEstado() == null || estacion.getEstado().equals(""))
					 mensajeNulo = new MessageResponse("El campo estado no puede estar vacío", 400);
				 else
					 if(estacion.getCodTroncal() == null || estacion.getCodTroncal().equals(""))
						 mensajeNulo = new MessageResponse("El campo código de troncal no puede estar vacío", 400);
		return mensajeNulo; 
	 }

}
