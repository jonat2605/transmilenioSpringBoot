package com.sophos.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sophos.entity.Ruta;

@Component
@Service
public interface RepositorioRuta extends CrudRepository<Ruta, String>{
	
	

}
