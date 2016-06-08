package br.com.s2it.snakes.models;

import java.util.Date;

public class CarCheckList {
	public enum Type {
		IN,OUT;
	}
	
	private Boolean keysInMailBox = false;
	private Boolean radio = false;
	private Boolean airConditioner = false;
	private Boolean lighter = false;
	private Boolean wrench = false;
	private Boolean powerLocks = false;
	private Boolean fireExtinguisher = false;
	private Boolean keyRing = false;
	private Boolean antenna = false;
	private Boolean tires = false;
	private Boolean hubcaps = false;
	private Boolean spareTire = false;
	private Boolean triangle = false;
	private Boolean jack = false;
	private Boolean interior = false;
	private Boolean documents = false;
	private String others;
	private Date checkListDate = new Date();
	private String gasoline;
	private Type type = Type.IN;
	private String userName;
	
	public Boolean getKeysInMailBox() {
		return keysInMailBox;
	}
	public void setKeysInMailBox(Boolean keysInMailBox) {
		this.keysInMailBox = keysInMailBox;
	}
	public Boolean getRadio() {
		return radio;
	}
	public void setRadio(Boolean radio) {
		this.radio = radio;
	}
	public Boolean getAirConditioner() {
		return airConditioner;
	}
	public void setAirConditioner(Boolean airConditioner) {
		this.airConditioner = airConditioner;
	}
	public Boolean getLighter() {
		return lighter;
	}
	public void setLighter(Boolean lighter) {
		this.lighter = lighter;
	}
	public Boolean getWrench() {
		return wrench;
	}
	public void setWrench(Boolean wrench) {
		this.wrench = wrench;
	}
	public Boolean getPowerLocks() {
		return powerLocks;
	}
	public void setPowerLocks(Boolean powerLocks) {
		this.powerLocks = powerLocks;
	}
	public Boolean getFireExtinguisher() {
		return fireExtinguisher;
	}
	public void setFireExtinguisher(Boolean fireExtinguisher) {
		this.fireExtinguisher = fireExtinguisher;
	}
	public Boolean getKeyRing() {
		return keyRing;
	}
	public void setKeyRing(Boolean keyRing) {
		this.keyRing = keyRing;
	}
	public Boolean getAntenna() {
		return antenna;
	}
	public void setAntenna(Boolean antenna) {
		this.antenna = antenna;
	}
	public Boolean getTires() {
		return tires;
	}
	public void setTires(Boolean tires) {
		this.tires = tires;
	}
	public Boolean getHubcaps() {
		return hubcaps;
	}
	public void setHubcaps(Boolean hubcaps) {
		this.hubcaps = hubcaps;
	}
	public Boolean getSpareTire() {
		return spareTire;
	}
	public void setSpareTire(Boolean spareTire) {
		this.spareTire = spareTire;
	}
	public Boolean getTriangle() {
		return triangle;
	}
	public void setTriangle(Boolean triangle) {
		this.triangle = triangle;
	}
	public Boolean getJack() {
		return jack;
	}
	public void setJack(Boolean jack) {
		this.jack = jack;
	}
	public Boolean getInterior() {
		return interior;
	}
	public void setInterior(Boolean interior) {
		this.interior = interior;
	}
	public Boolean getDocuments() {
		return documents;
	}
	public void setDocuments(Boolean documents) {
		this.documents = documents;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	public Date getCheckListDate() {
		return checkListDate;
	}
	public void setCheckListDate(Date checkListDate) {
		this.checkListDate = checkListDate;
	}
	public String getGasoline() {
		return gasoline;
	}
	public void setGasoline(String gasoline) {
		this.gasoline = gasoline;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
