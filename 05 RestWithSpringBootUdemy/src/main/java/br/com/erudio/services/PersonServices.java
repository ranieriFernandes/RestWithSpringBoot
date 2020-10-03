package br.com.erudio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.erudio.data.model.Person;
import br.com.erudio.repository.PersonRepository;

@Service
public class PersonServices {


	@Autowired
	PersonRepository repository;
	
	public Person create(Person person) {
		return repository.save(person);
	}
	
	public Person update(Person person) {
		Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAdress(person.getAdress());
		entity.setGender(person.getGender());
		
		return repository.save(entity);
	}

	public void delete(Long id) {
		Person entity = this.findById(id);
		repository.delete(entity);
	}
	
	
	public Person findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
	}
	
	public List<Person> findAll()  {
		return repository.findAll();		
	}

//	private Person mockPerson(int i) {
//		// TODO Auto-generated method stub
//		
//		Person person = new Person();
//		person.setId(counter.incrementAndGet());
//		person.setFirstName("Joao " + i);
//		person.setLastName("Silva" + i);
//		person.setAdress("Rs - Brasil" + i);
//		person.setGender("Male");
//		
//		return person;
//	}	

}
