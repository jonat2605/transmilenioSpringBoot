package com.sophos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sophos.model.RepositorioTroncal;

@RestController
public class ControladorTroncal {
	
	@Autowired
	private RepositorioTroncal repositorioTroncal;

}
