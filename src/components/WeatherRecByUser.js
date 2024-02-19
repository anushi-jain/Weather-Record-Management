import React, { useState, useEffect } from 'react';
import axios from 'axios';

const WeatherRecByUser = ({ userId }) => {
  const [weatherRecords, setWeatherRecords] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchWeatherRecords = async () => {
      try {
        // Make a request to your backend API to get weather records by user ID
        const response = await axios.get(`http://localhost:8081/api/showRecordsByUserId/${userId}`);
        setWeatherRecords(response.data);
      } catch (error) {
        console.error('Error fetching weather records:', error);
      } finally {
        setLoading(false);
      }
    };

    fetchWeatherRecords();
  }, [userId]);

  return (
    <div>
      <h2>Weather Records for User ID {userId}</h2>
      {loading ? (
        <p>Loading...</p>
      ) : weatherRecords.length > 0 ? (
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
              {/* Add any additional columns as needed */}
            </tr>
          </thead>
          <tbody>
            {weatherRecords.map((weather) => (
              <tr key={weather.id}>
                <td>{weather.id}</td>
                <td>{weather.pincode}</td>
                <td>{weather.city}</td>
                <td>{weather.state}</td>
                <td>{weather.date}</td>
                <td>{weather.temp}</td>
                <td>{weather.humidity}</td>
                {/* Add any additional cells as needed */}
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No weather records available for User ID {userId}.</p>
      )}
    </div>
  );
};

export default WeatherRecByUser;
