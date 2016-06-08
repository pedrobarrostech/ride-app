package br.com.s2it.snakes.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.s2it.snakes.models.Authentication;

@Repository
public interface AuthenticationRepository extends MongoRepository<Authentication, String> {
	public Authentication findByHash(String hash);

}
