package com.cabbooking.driverservice.model;


public class Cab {
	private int cabId;
	private String carType;
	private float ratePerKm;
	private int driverId;
	public int getCabId() {
		return cabId;
	}
	public void setCabId(int cabId) {
		this.cabId = cabId;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public float getRatePerKm() {
		return ratePerKm;
	}
	public void setRatePerKm(float ratePerKm) {
		this.ratePerKm = ratePerKm;
	}
	public int getDriverId() {
		return driverId;
	}
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}
	
	

}
