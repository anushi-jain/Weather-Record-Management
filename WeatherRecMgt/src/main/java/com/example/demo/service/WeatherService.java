package com.example.demo.service;

import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.example.demo.entity.Weather;
import com.example.demo.repository.WeatherRepository;
import com.example.demo.exception.InvalidDeletionException;
import com.example.demo.exception.WeatherNotFoundException;
 
@Service
public class WeatherService {
	@Autowired
    private WeatherRepository weatherRepository;
	//for saving changes in database
	public void saveWeatherDetails(Weather weather) {
	    weatherRepository.save(weather);
	}
 
	
	//Update
    public Optional<Weather> getWeatherByPincodeAndDate(int pincode, String date) {
        return weatherRepository.findByPincodeAndDate(pincode, date);
    }
 
 
    //Display by City
	public List<Weather> getWeatherByCity(String city) {
		List<Weather> weatherList = weatherRepository.findByCity(city);
		if(weatherList.isEmpty())
			 throw new WeatherNotFoundException("Records of this city doesn't exist");
		return weatherList;
	}

	//Delete
	public void deleteWeatherById(int id) {
        Optional<Weather> weather = weatherRepository.findById(id);
 
        if (weather.isPresent()) {
            weatherRepository.deleteById(id);
        } else {
            throw new InvalidDeletionException("Weather details not found for id " + id);
        }
    }
	public List<String> findCoastalCities() {
        return weatherRepository.findCoastalCities();
    }
	
	public List<Weather> getWeatherByUserId(int userId) {
	    return weatherRepository.findByUserId(userId);
	}

}
