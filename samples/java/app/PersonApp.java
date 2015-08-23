package app;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;

import dao.PersonDao;
import model.Person;

public class PersonApp {

	@Autowired
	PersonDao dao;
	
	public PersonDao getDao() {
		return dao;
	}

	public void setDao(PersonDao dao) {
		this.dao = dao;
	}

	public void insertPerson(Exchange exchange){
		Person targetPerson= exchange.getIn().getBody(Person.class);
System.out.println("Person Save !!");
		dao.save(targetPerson);
		}
}
