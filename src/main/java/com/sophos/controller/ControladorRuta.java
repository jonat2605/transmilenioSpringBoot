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

import com.sophos.entity.Ruta;
import com.sophos.model.RepositorioRuta;

@RestController
@Controller
public class ControladorRuta {
	
	@Autowired
	RepositorioRuta repositorioRuta;
	
	@PostMapping(value = "api/ruta")
	 public ResponseEntity<String> crearRuta(@RequestBody Ruta ruta){
			 repositorioRuta.save(ruta);
			 return new ResponseEntity<>("Ruta agregada con éxito", HttpStatus.CREATED);
	 }
	 
	 @PostMapping(value = "api/rutas")
	 public ResponseEntity<String> crearRutas(@RequestBody List<Ruta> rutas){
		 if(rutas.isEmpty())
			 return new ResponseEntity<String>("No puede agregar datos vacíos", HttpStatus.BAD_REQUEST);
		 else {
			 repositorioRuta.saveAll(rutas);
			 return new ResponseEntity<String>("Rutas agregadas con éxito", HttpStatus.CREATED);
		 }
	 }
	 
	 @GetMapping(value = "api/ruta/{codRuta}")
	 public ResponseEntity<Ruta> obtenerRuta(@PathVariable String codRuta){
		Optional<Ruta> estacion = repositorioRuta.findById(codRuta);
		if(estacion.isPresent())
			return new ResponseEntity<Ruta>(estacion.get(), HttpStatus.OK);
		else
			return new ResponseEntity<Ruta>(HttpStatus.NOT_FOUND);
	 }
	 
	 @GetMapping(value = "api/rutas")
	 public ResponseEntity<List<Ruta>> obtenerRutas(){
		 List<Ruta> rutas = (List<Ruta>) repositorioRuta.findAll();
		 return new ResponseEntity<List<Ruta>>(rutas, HttpStatus.OK);
	 }
	 
	 @DeleteMapping(value = "api/ruta/{codRuta}")
	 public ResponseEntity<String> borrarRuta(@PathVariable String codEstacion){
		 try {
			 repositorioRuta.deleteById(codEstacion);
		} catch (Exception e) {
			return new ResponseEntity<String>("No se encuentra el código de la ruta", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Ruta eliminada con éxito", HttpStatus.OK);
	 }
	 
	 @PutMapping(value = "api/ruta")
	 public ResponseEntity<String> actualizarRuta(@RequestBody Ruta ruta){
		 List<Ruta> estaciones = (List<Ruta>) repositorioRuta.findAll();
		 if(estaciones.contains(ruta)) {
			 repositorioRuta.save(ruta);
			 return new ResponseEntity<>("Estación agregada con éxito", HttpStatus.OK);
		 }else
			 return new ResponseEntity<String>("La estación no existe", HttpStatus.NOT_FOUND);
	 }

}
