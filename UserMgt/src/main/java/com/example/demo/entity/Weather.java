package com.example.demo.entity;

import jakarta.persistence.*;
 
@Entity
@Table(name="weather")
public class Weather {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="pincode")
	private int pincode;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="date")
	private String date;
	
	@Column(name="temp")
	private int temp;	        //Temperature in celsius
 
	@Column(name="humidity")
	private double humidity;     //humidity in %
	
	@Column(name="userId")
	private int userId;
 
	
	public Weather() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Weather(int id, int pincode, String city, String state, String date, int temp, double humidity, int UserId) {
		super();
		this.id = id;
		this.pincode = pincode;
		this.city = city;
		this.state = state;
		this.date = date;
		this.temp = temp;
		this.humidity = humidity;
		this.userId = UserId;
	}
 
	
	
	public int getId() {
		return id;
	}
 
	public void setId(int id) {
		this.id = id;
	}
 
	public int getPincode() {
		return pincode;
	}
 
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
 
	public String getCity() {
		return city;
	}
 
	public void setCity(String city) {
		this.city = city;
	}
 
	public String getState() {
		return state;
	}
 
	public void setState(String state) {
		this.state = state;
	}
 
	public String getDate() {
		return date;
	}
 
	public void setDate(String date) {
		this.date = date;
	}
 
	public int getTemp() {
		return temp;
	}
 
	public void setTemp(int temp) {
		this.temp = temp;
	}
 
	public double getHumidity() {
		return humidity;
	}
 
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Weather [id=" + id + ", pincode=" + pincode + ", city=" + city + ", state=" + state + ", date=" + date
				+ ", temp=" + temp + ", humidity=" + humidity + ", userId=" + userId + "]";
	}
 
	
}
