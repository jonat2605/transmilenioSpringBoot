package com.sophos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sophos.entity.Estacion;
import com.sophos.model.RepositorioEstacion;

@Controller
@RestController
public class ControladorEstacion {
	
	 @Autowired
	 private RepositorioEstacion repositorioEstacion;
	 
	 @PostMapping(value = "api/estacion")
	 public ResponseEntity<String> crearEstacion(@RequestBody Estacion estacion){
			 repositorioEstacion.save(estacion);
			 return new ResponseEntity<>("Estación agregada con éxito", HttpStatus.CREATED);
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
		if(estacion.isPresent())
			return new ResponseEntity<Estacion>(estacion.get(), HttpStatus.OK);
		else
			return new ResponseEntity<Estacion>(HttpStatus.NOT_FOUND);
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
		 boolean condicion = false;
		 List<Estacion> estaciones = (List<Estacion>) repositorioEstacion.findAll();
		 for(Estacion e : estaciones) {
			 if(e.getCodEstacion().equals(estacion.getCodEstacion())) {
				 condicion = true;
				 break;
			 }
		 }
		 if(condicion) {
			 repositorioEstacion.save(estacion);
			 return new ResponseEntity<>("Estación modificada con éxito", HttpStatus.OK); 
		 }else
			 return new ResponseEntity<String>("La estación no existe", HttpStatus.NOT_FOUND);
	 }

}
