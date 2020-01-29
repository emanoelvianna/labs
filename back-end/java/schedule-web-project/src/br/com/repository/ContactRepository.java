package br.com.repository;

import org.springframework.stereotype.Repository;

import com.infobip.spring.data.ExtendedQueryDslJpaRepository;

import br.com.model.Contact;

@Repository
public interface ContactRepository extends ExtendedQueryDslJpaRepository<Contact, Long> {

}
