import React, { useState } from 'react';
import axios from 'axios';

const WeatherForm = ({ onSubmit }) => {
  // State to hold form data
  const [formData, setFormData] = useState({
    pincode: '',
    city: '',
    state: '',
    date: '',
    temp: 0,
    humidity: 0.0,
  });

  // Handle form input changes
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: name === 'temp' ? parseInt(value, 10) : name === 'humidity' ? parseFloat(value) : value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      // Send a POST request to your backend API to store weather data
      await axios.post('http://localhost:8080/api/add-details', formData);

      // Optionally, you can reset the form after successful submission
      setFormData({
        pincode: '',
        city: '',
        state: '',
        date: '',
        temp: 0,
        humidity: 0.0,
      });

      // Add any additional logic or user feedback for successful submission
    } catch (error) {
      // Handle errors, e.g., display an error message to the user
      console.error('Error submitting weather data:', error.message);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      {/* Add your form fields for each parameter of the Weather class */}
      <label>
        Pincode:
        <input
          type="number"
          name="pincode"
          value={formData.pincode}
          onChange={handleInputChange}
          required
        />
      </label>

      <label>
        City:
        <input
          type="text"
          name="city"
          value={formData.city}
          onChange={handleInputChange}
          required
        />
      </label>

      <label>
        State:
        <input
          type="text"
          name="state"
          value={formData.state}
          onChange={handleInputChange}
          required
        />
      </label>

      <label>
        Date:
        <input
          type="text"
          name="date"
          value={formData.date}
          onChange={handleInputChange}
          required
        />
      </label>

      <label>
        Temperature:
        <input
          type="number"
          name="temp"
          value={formData.temp}
          onChange={handleInputChange}
          required
        />
      </label>

      <label>
        Humidity:
        <input
          type="number"
          name="humidity"
          value={formData.humidity}
          onChange={handleInputChange}
          required
        />
      </label>

      {/* Submit button */}
      <button type="submit">Submit</button>
    </form>
  );
};

export default WeatherForm;