package br.com.s2it.snakes.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="cars")
public class Car {
	
	@Id
	private String id;
	private String licensePlate;
	private String name;
	private String model;
	private String year;
	private String unit;
	private String parkingSpace;
	private String picture;
	private Long mileage;
	private List<CarDamage> damages;
	private List<CarCheckList> checklist;
	
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
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getParkingSpace() {
		return parkingSpace;
	}
	public void setParkingSpace(String parkingSpace) {
		this.parkingSpace = parkingSpace;
	}
	public Long getMileage() {
		return mileage;
	}
	public void setMileage(Long mileage) {
		this.mileage = mileage;
	}
	public List<CarDamage> getDamages() {
		return damages;
	}
	public void setDamages(List<CarDamage> damages) {
		this.damages = damages;
	}
	public List<CarCheckList> getChecklist() {
		return checklist;
	}
	public void setChecklist(List<CarCheckList> checklist) {
		this.checklist = checklist;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}	
	
}
