package br.com.s2it.snakes.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.s2it.snakes.models.Car;

@Repository
public interface CarRepository extends MongoRepository<Car, String> {
	public Car findByLicensePlate(String licensePlate);

}
