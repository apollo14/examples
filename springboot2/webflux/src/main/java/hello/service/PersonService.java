package hello.service;

import org.springframework.stereotype.Component;

import hello.model.Person;
import reactor.core.publisher.Flux;

@Component
public class PersonService {

	public Flux<Person> getList(){
		return Flux.just(new Person("a", "A"), new Person("b", "B"));
	}
}
