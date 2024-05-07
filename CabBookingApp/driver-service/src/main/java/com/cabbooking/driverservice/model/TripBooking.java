package com.cabbooking.driverservice.model;

import java.time.LocalDate;

public class TripBooking {

	private int TripBookingId;

	private int customerId;

	private int driverId;

	private String From_location;

	private String To_location;

	private LocalDate Fromdate_time;

	private LocalDate Todate_time;

	private float km;
	private float Totalamount;
	private boolean Payment;

	public int getTripBookingId() {
		return TripBookingId;
	}

	public void setTripBookingId(int tripBookingId) {
		TripBookingId = tripBookingId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomer(int customerId) {
		this.customerId = customerId;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getFrom_location() {
		return From_location;
	}

	public void setFrom_location(String from_location) {
		From_location = from_location;
	}

	public String getTo_location() {
		return To_location;
	}

	public void setTo_location(String to_location) {
		To_location = to_location;
	}

	public LocalDate getFromdate_time() {
		return Fromdate_time;
	}

	public void setFromdate_time(LocalDate fromdate_time) {
		Fromdate_time = fromdate_time;
	}

	public LocalDate getTodate_time() {
		return Todate_time;
	}

	public void setTodate_time(LocalDate todate_time) {
		Todate_time = todate_time;
	}

	public float getKm() {
		return km;
	}

	public void setKm(float km) {
		this.km = km;
	}

	public float getTotalamount() {
		return Totalamount;
	}

	public void setTotalamount(float totalamount) {
		Totalamount = totalamount;
	}

	public boolean getPayment() {
		return Payment;
	}

	public void setPayment(boolean payment) {
		Payment = payment;
	}

}