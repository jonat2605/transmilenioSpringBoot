package com.sophos.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sophos.entity.Troncal;

@Component
@Service
public interface RepositorioTroncal extends CrudRepository<Troncal, String>{

}
