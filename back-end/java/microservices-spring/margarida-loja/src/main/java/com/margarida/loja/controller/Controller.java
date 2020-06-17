package com.margarida.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.margarida.loja.dto.CompraDTO;
import com.margarida.loja.service.CompraService;

@RestController
@RequestMapping("/margarida-loja")
public class Controller {

	@Autowired
	private CompraService service;

	@RequestMapping(value = "/compra", method = RequestMethod.POST)
	public void comprar(@RequestBody CompraDTO compra) {
		this.service.comprar(compra);
	}

}
