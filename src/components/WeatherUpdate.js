import React, { useState } from 'react';
import axios from 'axios';

const WeatherUpdate = () => {
  // State to manage form data
  const [updateData, setUpdateData] = useState({
    pincode: '',
    date: '',
    newTemp: '',
    newHumidity: '',
  });

  // Handle form input changes
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setUpdateData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  // Handle form submission
  const handleUpdate = async (e) => {
    e.preventDefault();

    try {
      // Send a PUT request to your backend API to update weather data
      await axios.put(
        `http://localhost:8080/api/update-details/${updateData.pincode}/${updateData.date}`,
        {
          temp: updateData.newTemp,
          humidity: updateData.newHumidity,
        }
      );

      // Optionally, you can reset the form after successful update
      setUpdateData({
        pincode: '',
        date: '',
        newTemp: '',
        newHumidity: '',
      });

      // Add any additional logic or user feedback for successful update
    } catch (error) {
      // Handle errors, e.g., display an error message to the user
      console.error('Error updating weather data:', error.message);
    }
  };

  return (
    <form onSubmit={handleUpdate}>
      {/* Add your form fields for pincode, date, newTemp, and newHumidity */}
      <label>
        Pincode:
        <input
          type="number"
          name="pincode"
          value={updateData.pincode}
          onChange={handleInputChange}
          required
        />
      </label>

      <label>
        Date:
        <input
          type="text"
          name="date"
          value={updateData.date}
          onChange={handleInputChange}
          required
        />
      </label>

      <label>
        New Temperature:
        <input
          type="number"
          name="newTemp"
          value={updateData.newTemp}
          onChange={handleInputChange}
          required
        />
      </label>

      <label>
        New Humidity:
        <input
          type="number"
          name="newHumidity"
          value={updateData.newHumidity}
          onChange={handleInputChange}
          required
        />
      </label>

      {/* Update button */}
      <button type="submit">Update</button>
    </form>
  );
};

export default WeatherUpdate;
