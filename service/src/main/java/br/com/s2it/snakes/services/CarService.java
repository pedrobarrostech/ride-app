package br.com.s2it.snakes.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.s2it.snakes.models.Car;
import br.com.s2it.snakes.models.CarReservation;
import br.com.s2it.snakes.repositories.CarRepository;
import br.com.s2it.snakes.repositories.CarReservationRepository;

@Service
public class CarService {
	@Autowired
	private CarRepository repository;
	
	@Autowired
	private CarReservationRepository reservationRepository;
	
	public Car save(Car car) throws Exception{		
		if (car == null || car.getLicensePlate() == null || car.getMileage() == null || car.getModel() == null || car.getName() == null ||
				car.getParkingSpace() == null || car.getUnit() == null || car.getYear() == null){
			throw new Exception("null_params");
		}
		
		car = repository.save(car);
		return car;
	}
	
	public List<Car> listAllCars() {
		return repository.findAll();
	}
	
	public Car findByLicensePlate(String licensePlate) throws Exception{
		if (licensePlate == null || licensePlate.isEmpty()){
			throw new Exception("null_params");
		}
		return repository.findByLicensePlate(licensePlate);
	}
	
	public boolean reservationIsPossible(String licensePlate, Date initialReservationDate, Date finalReservationDate)  throws Exception{
		if(licensePlate == null || licensePlate.isEmpty() || initialReservationDate == null || finalReservationDate == null) { throw new Exception("null_params");}
		if(finalReservationDate.getTime() <= initialReservationDate.getTime()) { throw new Exception("dtinitial_greater_than_dtfinal"); }
		
		List<CarReservation> listReservation = reservationRepository.findByReservationDatesBetweenAndLicensePlate1(licensePlate, initialReservationDate, finalReservationDate);
		listReservation.addAll(reservationRepository.findByReservationDatesBetweenAndLicensePlate2(licensePlate, initialReservationDate, finalReservationDate));
		listReservation.addAll(reservationRepository.findByReservationDatesBetweenAndLicensePlate3(licensePlate, initialReservationDate, finalReservationDate));
		listReservation.addAll(reservationRepository.findByReservationDatesBetweenAndLicensePlate4(licensePlate, initialReservationDate, finalReservationDate));
		
		return listReservation.isEmpty();
	}
	
	public List<CarReservation> listPossibleReservations(Date initialReservationDate, Date finalReservationDate)  throws Exception{
		if(initialReservationDate == null || finalReservationDate == null) {throw new Exception("null_params");}
		if(finalReservationDate.getTime() <= initialReservationDate.getTime()) {throw new Exception("dtinitial_greater_than_dtfinal");}
		
		List<CarReservation> listReservation = reservationRepository.findByReservationDatesBetween1(initialReservationDate, finalReservationDate);
		listReservation.addAll(reservationRepository.findByReservationDatesBetween2(initialReservationDate, finalReservationDate));
		listReservation.addAll(reservationRepository.findByReservationDatesBetween3(initialReservationDate, finalReservationDate));
		listReservation.addAll(reservationRepository.findByReservationDatesBetween4(initialReservationDate, finalReservationDate));
		
		return listReservation;
	}
	
	public List<CarReservation> listAllReservations() {
		return reservationRepository.findAll();
	}
	
	public CarReservation createReservation(CarReservation reservation)  throws Exception{
		if(reservation == null || reservation.getCustomer() == null || reservation.getDestiny() == null || reservation.getLicensePlate() == null ||
				reservation.getPassengers() == null || reservation.getInitialReservationDate() == null || reservation.getFinalReservationDate() == null){
			throw new Exception("null_params");
		}
		
		if(reservation.getInitialReservationDate().getTime() >= reservation.getFinalReservationDate().getTime()){ throw new Exception(); }
		
		return reservationRepository.save(reservation);
	}
	
	public CarReservation removePassengerIntoReservation(String reservation, Integer passenger) throws Exception {
		CarReservation carReservation = reservationRepository.findById(reservation);
		carReservation.setPassengers(carReservation.getPassengers() - passenger);
		
		carReservation = reservationRepository.save(carReservation);
		
		return carReservation;
	}
	
	public CarReservation addPassengersIntoReservation(String reservation, Integer passenger) throws Exception {
		if(reservation == null || reservation.isEmpty()) { throw new Exception("null_params"); }
		if(passenger == null){ throw new Exception("null_params"); }
		
		CarReservation carReservation = reservationRepository.findById(reservation);
		if(carReservation == null) { throw new Exception("null_params"); }
		
		int passengersTotal = carReservation.getPassengers() + passenger;
		
		if(passengersTotal <= 5) {
			carReservation.setPassengers(passengersTotal);
			return reservationRepository.save(carReservation);
		} else
			throw new Exception("invalid_passenger");
			
	}
	
	public void cancelCarReservation(String reservationId) throws Exception {
		CarReservation carReservation = reservationRepository.findById(reservationId);
		
		if(carReservation == null || carReservation.getId() == null || carReservation.getId().isEmpty())
			throw new Exception("invalid_reservation");
			
		reservationRepository.delete(reservationId);
	}
	
	
	
	public Car create() {
		Car car = new Car();
		car.setLicensePlate("XTZ-1989");
		car.setMileage(10000l);
		car.setModel("GOL");
		car.setParkingSpace("18");
		car.setName("GOL");
		
		car.setUnit("ARARAQUARA");
		car.setYear("2010");
		
		return repository.save(car);
	}

}
