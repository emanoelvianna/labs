package br.com.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.service.ContactService;
import javassist.NotFoundException;

@Controller
public class ContactController {

	private ContactService service;

	@RequestMapping("/{id}")
	public ResponseEntity<?> getContact(@PathVariable Long id) throws NotFoundException {
		return ResponseEntity.ok(service.get(id));
	}

}
