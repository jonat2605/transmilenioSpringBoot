package com.sophos.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sophos.entity.Estacion;

@Component
@Service
public interface RepositorioEstacion extends CrudRepository<Estacion, String>{

	
	
}
