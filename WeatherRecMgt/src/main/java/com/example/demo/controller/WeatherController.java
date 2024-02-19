package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.example.demo.entity.Weather;
import com.example.demo.exception.InvalidDeletionException;
import com.example.demo.exception.InvalidInsertionException;
import com.example.demo.exception.InvalidWeatherDataException;
import com.example.demo.exception.WeatherNotFoundException;
import com.example.demo.repository.WeatherRepository;
import com.example.demo.service.WeatherService;
  
@RestController
@RequestMapping("/api")
@CrossOrigin
public class WeatherController {
	@Autowired
	WeatherRepository wr;
	@Autowired
	WeatherService ws;
	
	//Create
	@PostMapping("/add-details")
	public ResponseEntity<String> addWeatherDetails(@RequestBody Weather w) {
	    try {
	        // Check for invalid temperature and humidity range
	        if ((w.getTemp() < -2 || w.getTemp() > 50) || (w.getHumidity() < 15 || w.getHumidity() > 100)) {
	            throw new InvalidWeatherDataException("Invalid temperature or humidity range");
	        }
 
	        // Check for the same location and date having different temp/humidity
	        if (wr.existsByPincodeAndDate(w.getPincode(), w.getDate())) {
	            throw new InvalidInsertionException("Weather details already exist for the same location and date");
	        }
 
	        wr.save(w);
	        return new ResponseEntity<>("Weather details added", HttpStatus.OK);
	    } catch (InvalidWeatherDataException e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    } catch (InvalidInsertionException e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
	    }
	}
 
	
	//Delete
	@DeleteMapping("/delete-details/{id}")
	public ResponseEntity<String> deleteWeatherById(@PathVariable int id) {
        try {
            ws.deleteWeatherById(id);
            return new ResponseEntity<>("Weather details deleted for id " + id, HttpStatus.OK);
        } catch (InvalidDeletionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
	}
	//Update
	@PutMapping("/update-details/{pincode}/{date}")
	public ResponseEntity<String> updateWeatherById(@PathVariable int pincode,@PathVariable String date,@RequestBody Weather w ) {
		try {
		Optional<Weather> existingWeather = ws.getWeatherByPincodeAndDate(pincode, date);
 
        	if (existingWeather.isPresent()) {
        		Weather currentWeather = existingWeather.get();
        		 if ((w.getTemp() < -2 || w.getTemp() > 50) || (w.getHumidity() < 15 || w.getHumidity() > 100)) {
                     throw new InvalidWeatherDataException("Invalid temperature or humidity range");
                 	}
        		currentWeather.setTemp(w.getTemp());
        		currentWeather.setHumidity(w.getHumidity());
 
        		wr.save(currentWeather);
 
			
        		return new ResponseEntity<>("Weather details updated for pincode: "+pincode+ " on the date of: "+date, HttpStatus.OK);
        	} else {
        		throw new WeatherNotFoundException("Weather details doesn't exists for pincode: "+pincode+ " on the date of: "+date);
        	}
		} catch (InvalidWeatherDataException e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    } catch (WeatherNotFoundException e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	    }
	}
	//Display
	@GetMapping("/show-details/{city}")
	public ResponseEntity<Object> getDetailsByCity(@PathVariable String city){
 
         try{
        	 	List<Weather> weatherList = ws.getWeatherByCity(city);
        	 	return new ResponseEntity<>(weatherList, HttpStatus.OK);
         	}catch(WeatherNotFoundException e) {
         		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
         	}
	}
	//Report Generation
	//Getting Coastal Cities
	@GetMapping("/coastal-cities")
    public ResponseEntity<List<String>> getCoastalCities() {
        List<String> coastalCities = ws.findCoastalCities();
 
        if (coastalCities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
 
        return new ResponseEntity<>(coastalCities, HttpStatus.OK);
    }
	
	//Records added by specific user
	@GetMapping("/showByUserId/{userId}")
	public ResponseEntity<List<Weather>> getWeatherRecordsByUserId(@PathVariable int userId) {
	    List<Weather> weatherRecords = ws.getWeatherByUserId(userId);
	    return new ResponseEntity<>(weatherRecords, HttpStatus.OK);
	}
}
