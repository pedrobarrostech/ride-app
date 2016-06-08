package br.com.s2it.snakes.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.s2it.snakes.models.CarReservation;

@Repository
public interface CarReservationRepository extends MongoRepository<CarReservation, String> {
	@Query("{'initialReservationDate':{'$lte':?1}, 'finalReservationDate':{'$gte':?2}, 'licensePlate':?0}")
	public List<CarReservation> findByReservationDatesBetweenAndLicensePlate1(String plate, Date initialReservationDate, Date finalReservationDate);
	
	@Query("{'initialReservationDate':{'$gte':?1}, 'finalReservationDate':{'$lte':?2}, 'licensePlate':?0}")
	public List<CarReservation> findByReservationDatesBetweenAndLicensePlate2(String plate, Date initialReservationDate, Date finalReservationDate);
	
	@Query("{'initialReservationDate':{'$lte':?1}, 'finalReservationDate':{'$lte':?2}, 'licensePlate':?0}")
	public List<CarReservation> findByReservationDatesBetweenAndLicensePlate3(String plate, Date initialReservationDate, Date finalReservationDate);
	
	@Query("{'initialReservationDate':{'$gte':?1}, 'finalReservationDate':{'$gte':?2}, 'licensePlate':?0}")
	public List<CarReservation> findByReservationDatesBetweenAndLicensePlate4(String plate, Date initialReservationDate, Date finalReservationDate);
	
	@Query("{'initialReservationDate':{'$lte':?0}, 'finalReservationDate':{'$gte':?1}}")
	public List<CarReservation> findByReservationDatesBetween1(Date initialReservationDate, Date finalReservationDate);
	
	@Query("{'initialReservationDate':{'$gte':?0}, 'finalReservationDate':{'$lte':?1}}")
	public List<CarReservation> findByReservationDatesBetween2(Date initialReservationDate, Date finalReservationDate);
	
	@Query("{'initialReservationDate':{'$lte':?0}, 'finalReservationDate':{'$lte':?1}}")
	public List<CarReservation> findByReservationDatesBetween3(Date initialReservationDate, Date finalReservationDate);
	
	@Query("{'initialReservationDate':{'$gte':?0}, 'finalReservationDate':{'$gte':?1}}")
	public List<CarReservation> findByReservationDatesBetween4(Date initialReservationDate, Date finalReservationDate);
	
	@Query("{'id':?0}")
	public CarReservation findById(String reservation);

}
