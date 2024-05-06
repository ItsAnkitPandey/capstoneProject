package com.cabbooking.tripbookingservice.entity;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tripBooking_tbl")
public class TripBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int TripBookingId;

	@NotNull
	private int customerId;
	
	private int driverId;

	@NotNull(message = "From Location cannot be Null")
	private String From_location;

	@NotNull(message = "From Location cannot be Null")
	private String To_location;

	@FutureOrPresent(message = "from trip date is only future date or present date")
	@NotNull(message = "data cannot be null")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate Fromdate_time;

	@FutureOrPresent(message = "to trip date is only future date or present date")
	@NotNull(message = "data cannot be null")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
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

	@Override
	public int hashCode() {
		return Objects.hash(From_location, Fromdate_time, Payment, To_location, Todate_time, Totalamount, TripBookingId,
				customerId, driverId, km);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TripBooking other = (TripBooking) obj;
		return Objects.equals(From_location, other.From_location) && Objects.equals(Fromdate_time, other.Fromdate_time)
				&& Payment == other.Payment && Objects.equals(To_location, other.To_location)
				&& Objects.equals(Todate_time, other.Todate_time)
				&& Float.floatToIntBits(Totalamount) == Float.floatToIntBits(other.Totalamount)
				&& TripBookingId == other.TripBookingId && customerId == other.customerId && driverId == other.driverId
				&& Float.floatToIntBits(km) == Float.floatToIntBits(other.km);
	}

	@Override
	public String toString() {
		return "TripBooking [TripBookingId=" + TripBookingId + ", customerId=" + customerId + ", driverId=" + driverId
				+ ", From_location=" + From_location + ", To_location=" + To_location + ", Fromdate_time="
				+ Fromdate_time + ", Todate_time=" + Todate_time + ", km=" + km + ", Totalamount=" + Totalamount
				+ ", Payment=" + Payment + "]";
	}

}