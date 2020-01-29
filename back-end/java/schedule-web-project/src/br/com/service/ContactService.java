package br.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.model.Contact;
import br.com.repository.ContactRepository;
import javassist.NotFoundException;

@Service
public class ContactService {

	private ContactRepository repository;

	@Autowired
	public ContactService(ContactRepository repository) {
		this.repository = repository;
	}

	public void create() {
		Contact contact = new Contact();
		this.repository.save(contact);
	}

	public Contact get(Long id) throws NotFoundException {
		Contact contact = this.repository.findById(id).orElseThrow(() -> new NotFoundException("Not found contact"));
		return contact;
	}

}
