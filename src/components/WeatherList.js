import React, { useState} from 'react';
import axios from 'axios';

const WeatherList = () => {
  const [city, setCity] = useState('');
  const [weatherData, setWeatherData] = useState([]);

  const fetchData = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/api/show-details/${city}`);
      setWeatherData(response.data);
    } catch (error) {
      console.error('Error fetching weather data:', error);
    }
  };

  const handleCityChange = (e) => {
    setCity(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    fetchData();
  };

  return (
    <div>
      <h2>Weather Details</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Enter City:
          <input type="text" value={city} onChange={handleCityChange} />
        </label>
        <button type="submit">Get Weather Details</button>
      </form>

      {weatherData.length > 0 ? (
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Pincode</th>
              <th>City</th>
              <th>State</th>
              <th>Date</th>
              <th>Temperature (Celsius)</th>
              <th>Humidity (%)</th>
            </tr>
          </thead>
          <tbody>
            {weatherData.map((weather) => (
              <tr key={weather.id}>
                <td>{weather.id}</td>
                <td>{weather.pincode}</td>
                <td>{weather.city}</td>
                <td>{weather.state}</td>
                <td>{weather.date}</td>
                <td>{weather.temp}</td>
                <td>{weather.humidity}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No weather data available for {city}.</p>
      )}
    </div>
  );
};

export default WeatherList;
