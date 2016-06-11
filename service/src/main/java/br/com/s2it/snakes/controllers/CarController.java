package br.com.s2it.snakes.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.s2it.snakes.models.Car;
import br.com.s2it.snakes.models.CarCheckList;
import br.com.s2it.snakes.models.CarDamage;
import br.com.s2it.snakes.models.CarReservation;
import br.com.s2it.snakes.services.CarService;
import br.com.s2it.snakes.vos.CarForReservationVO;

@RestController
@RequestMapping("/cars")
public class CarController {
	@Autowired
	private CarService service;

	@Autowired
	private JavaMailSenderImpl mailSender;

	@CrossOrigin("*")
	@RequestMapping
	public List<Car> listAllCars() {
		return service.listAllCars();
	}	
	
	@CrossOrigin("*")
	@RequestMapping(value = "/damage/{licensePlate}", method = RequestMethod.PUT)
	public ResponseEntity<Car> doDamage(final @RequestBody List<CarDamage> damage,
			final @PathVariable(value = "licensePlate") String licensePlate) {
		if(licensePlate == null || licensePlate.isEmpty() || damage == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		
		try {
			Car car = service.findByLicensePlate(licensePlate);
			
			if(car.getDamages() == null)
				car.setDamages(new ArrayList<>());
			
			car.getDamages().addAll(damage);
			
			return ResponseEntity.status(HttpStatus.OK).body(service.save(car));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@CrossOrigin("*")
	@RequestMapping(value = "/checklist/{licensePlate}", method = RequestMethod.PUT)
	public ResponseEntity<Car> doChecklist(final @RequestBody CarCheckList checklist,
			final @PathVariable(value = "licensePlate") String licensePlate) {
		if(licensePlate == null || licensePlate.isEmpty() || checklist == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		
		try {
			Car car = service.findByLicensePlate(licensePlate);
			
			if(car.getChecklist() == null)
				car.setChecklist(new ArrayList<>());
			
			car.getChecklist().add(checklist);
			
			return ResponseEntity.status(HttpStatus.OK).body(service.save(car));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@CrossOrigin("*")
	@RequestMapping(value = "/reservation/{reservation}/cancel", method = RequestMethod.GET)
	public ResponseEntity<String> removePassengerToReservation(
			final @PathVariable(value = "reservation") String reservation) {
		if (reservation == null || reservation.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

		try {
			service.cancelCarReservation(reservation);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@CrossOrigin("*")
	@RequestMapping(value = "/reservation/cancel", method = RequestMethod.PUT)
	public ResponseEntity<CarReservation> removePassengerIntoReservation(
			final @RequestParam(value = "reservation") String reservation,
			final @RequestParam(value = "passengers") Integer passengers) {

		if (reservation == null || passengers == null || passengers <= 0 || reservation.isEmpty())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(service.removePassengerIntoReservation(reservation, passengers));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@CrossOrigin("*")
	@RequestMapping(value = "/reservation", method = RequestMethod.PUT)
	public ResponseEntity<CarReservation> addPassengerToReservation(
			final @RequestParam(value = "reservation") String reservation,
			final @RequestParam(value = "passengers") Integer passengers) {

		if (reservation == null || passengers == null || passengers <= 0 || reservation.isEmpty())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(service.addPassengersIntoReservation(reservation, passengers));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@CrossOrigin("*")
	@RequestMapping(value = "/reservation", method = RequestMethod.POST)
	public ResponseEntity<CarReservation> createReservation(@RequestBody CarReservation reservation) {

		if (reservation == null || reservation.getLicensePlate() == null 
				|| reservation.getLicensePlate() == null || reservation.getCustomer() == null
				|| reservation.getDestiny() == null || reservation.getPassengers() <= 0
				|| reservation.getInitialReservationDate() == null 
				|| reservation.getFinalReservationDate() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		try {		
			boolean reservationIsPossible = service.reservationIsPossible(reservation.getLicensePlate(),
					reservation.getInitialReservationDate(), reservation.getFinalReservationDate());
			if (reservationIsPossible) {
				return ResponseEntity.status(HttpStatus.OK).body(service.createReservation(reservation));
			} else {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

	}
	
	@CrossOrigin("*")
	@RequestMapping(value = "/reservations", method = RequestMethod.GET)
	public ResponseEntity<List<CarForReservationVO>> listAllReservations() {
		List<CarReservation> reservations = service.listAllReservations();
		List<CarForReservationVO> vos = new ArrayList<>();
		
		if(reservations != null && !reservations.isEmpty()) {
			for(CarReservation reservation : reservations) {
				Car car;
				
				try {
					car = service.findByLicensePlate(reservation.getLicensePlate());
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
				}
				
				if(car == null)
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
				
				CarForReservationVO crvo = new CarForReservationVO();
				
				crvo.setPassengers(reservation.getPassengers());
				crvo.setReservationId(reservation.getId());
				crvo.setId(car.getId());
				crvo.setModel(car.getModel());
				crvo.setName(car.getName());
				crvo.setPicture(car.getPicture());
				crvo.setLicensePlate(car.getLicensePlate());
				
				vos.add(crvo);
			}
		}
		
		return ResponseEntity.ok(vos);
	}

	@CrossOrigin("*")
	@RequestMapping(value = "/reservation", method = RequestMethod.GET)
	public ResponseEntity<List<CarForReservationVO>> listCarsForReservation(
			@RequestParam(value = "initialDate") final String initialDate,
			@RequestParam(value = "finalDate") final String finalDate) {

		if (finalDate == null || initialDate == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			
			Date initial = sdf.parse(initialDate);
			Date end = sdf.parse(finalDate);
			
			List<CarReservation> reservations = service.listPossibleReservations(initial, end);
			List<Car> cars = service.listAllCars();
			List<CarForReservationVO> possibleCars = new ArrayList<>();

			for (Car car : cars) {
				CarForReservationVO crvo = new CarForReservationVO();
				crvo.setPassengers(0);

				for (CarReservation reservation : reservations) {
					if (car.getLicensePlate().equals(reservation.getLicensePlate())) {
						crvo.setPassengers(reservation.getPassengers());
						crvo.setReservationId(reservation.getId());

						break;
					}
				}

				crvo.setId(car.getId());
				crvo.setModel(car.getModel());
				crvo.setName(car.getName());
				crvo.setPicture(car.getPicture());
				crvo.setLicensePlate(car.getLicensePlate());

				possibleCars.add(crvo);
			}

			return ResponseEntity.ok(possibleCars);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@CrossOrigin("*")
	@RequestMapping("/{licensePlate}")
	public ResponseEntity<Car> listByLicensePlate(@PathVariable(value = "licensePlate") final String licensePlate) {

		if (licensePlate == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

		try {
			Car car = service.findByLicensePlate(licensePlate);

			if (car != null && car.getId() != null)
				return ResponseEntity.ok(car);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@RequestMapping("/testeReservation")
	public ResponseEntity<CarReservation> testeReservation() {
		CarReservation r = new CarReservation();
		r.setCustomer("usuario");
		r.setDestiny("destino");
		r.setFinalReservationDate(new Date());
		r.setInitialReservationDate(new Date());
		r.setLicensePlate("placa");
		r.setPassengers(2);

		return ResponseEntity.status(HttpStatus.OK).body(r);
	}

	@RequestMapping("/teste")
	public Car save() {
		return service.create();
	}

	@RequestMapping("/email")
	public void email() {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage);

			mailMsg.setFrom("snakeshackathon@gmail.com");
			mailMsg.setTo("daniel@balieiro.com");
			mailMsg.setSubject("Test mail");
			mailMsg.setText("Hello World!");

			mailSender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
