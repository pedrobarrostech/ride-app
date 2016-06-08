package br.com.s2it.snakes.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection="carreservations")
public class CarReservation {
	
	@Id
	private String id;

	private String licensePlate;
	private Integer passengers;
	private String destiny;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
	private Date initialReservationDate;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
	private Date finalReservationDate;
	
	private String customer;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public Integer getPassengers() {
		return passengers;
	}
	public void setPassengers(Integer passengers) {
		this.passengers = passengers;
	}
	public String getDestiny() {
		return destiny;
	}
	public void setDestiny(String destiny) {
		this.destiny = destiny;
	}
	public Date getInitialReservationDate() {
		return initialReservationDate;
	}
	public void setInitialReservationDate(Date initialReservationDate) {
		this.initialReservationDate = initialReservationDate;
	}
	public Date getFinalReservationDate() {
		return finalReservationDate;
	}
	public void setFinalReservationDate(Date finalReservationDate) {
		this.finalReservationDate = finalReservationDate;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}	
}
