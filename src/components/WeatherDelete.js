import React, { useState } from 'react';
import axios from 'axios';

const WeatherDelete = () => {
  const [Id, setId] = useState('');

  const handleRecordIdChange = (e) => {
    setId(e.target.value);
  };

  const handleDelete = async (e) => {
    e.preventDefault();

    try {
      // Send a DELETE request to your backend API to delete the record by ID
      await axios.delete(`http://localhost:8080/api/delete-details/${Id}`);

      // Optionally, provide feedback to the user or perform additional actions
      console.log(`Record with ID ${Id} deleted successfully`);

      // Clear the input field after successful deletion
      setId('');
    } catch (error) {
      console.error('Error deleting record:', error.message);
    }
  };

  return (
    <div>
      <h2>Delete Weather Record</h2>
      <form onSubmit={handleDelete}>
        <label>
          Enter Record ID:
          <input type="number" value={Id} onChange={handleRecordIdChange} />
        </label>
        <button type="submit">Delete Record</button>
      </form>
    </div>
  );
};

export default WeatherDelete;
