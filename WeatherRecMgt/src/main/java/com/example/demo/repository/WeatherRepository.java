package com.example.demo.repository;

import java.util.List;
import java.util.Optional;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
 
import com.example.demo.entity.Weather;
 
@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer>{
	Optional<Weather> findByPincodeAndDate(int pincode, String date);
 
	List<Weather> findByUserId(int userId);

	@Query("SELECT DISTINCT w.city FROM Weather w " +
	        "WHERE w.city IN (" +
	        "    SELECT w2.city FROM Weather w2 " +
	        "    WHERE w2.date LIKE '___11_____' OR w2.date LIKE '___12_____' OR w2.date LIKE '___01_____' " +
	        "    GROUP BY w2.city " +
	        "    HAVING AVG(w2.temp) BETWEEN 25 AND 32" +
	        ") " +
	        "AND w.city IN (" +
	        "    SELECT w3.city FROM Weather w3 " +
	        "    WHERE w3.date LIKE '___06_____' OR w3.date LIKE '___07_____' " +
	        "    GROUP BY w3.city " +
	        "    HAVING AVG(w3.humidity) BETWEEN 77 AND 95" +
	        ")")
	List<String> findCoastalCities();

	 @Query("SELECT city " +
	           "FROM Weather  " +
	           "GROUP BY city " +
	           "ORDER BY AVG(temp) DESC " +
	           "LIMIT 3")
	List<String> hottestCities();
 
	boolean existsByPincodeAndDate(int pincode, String date);

	List<Weather> findByCity(String city);
	
	
 
}
