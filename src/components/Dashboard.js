// Dashboard.js

import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import WeatherForm from './WeatherForm';
import WeatherList from './WeatherList';
import WeatherUpdate from './WeatherUpdate';
import WeatherDelete from './WeatherDelete';
import CoastalCities from './CoastalCities';
import WeatherRecByUser from './WeatherRecByUser';

const Dashboard = () => {
  return (
    <Router>
      <div className="dashboard-container">
        {/* Navigation */}
        <nav>
          <h1>Dash Board</h1>
          <ul>
            <li><Link to="/dashboard/form">Add Weather Record</Link></li>
            <li><Link to="/dashboard/list">Show Weather List</Link></li>
            <li><Link to="/dashboard/update">Update Weather Record</Link></li>
            <li><Link to="/dashboard/delete">Delete Weather Record</Link></li>
            <li><Link to="/dashboard/coastal-cities">Coastal Cities</Link></li>
            <li><Link to="/dashboard/records-by-user">No. of Records by User</Link></li>
          </ul>
        </nav>

        {/* Route Configuration */}
        <Routes>
          <Route path="/dashboard/form" element={<WeatherForm />} />
          <Route path="/dashboard/list" element={<WeatherList />} />
          <Route path="/dashboard/update" element={<WeatherUpdate />} />
          <Route path="/dashboard/delete" element={<WeatherDelete />} />
          <Route path="/dashboard/coastal-cities" element={<CoastalCities />} />
          <Route path="/dashboard/records-by-user" element={<WeatherRecByUser />} />
        </Routes>
      </div>
    </Router>
  );
};

export default Dashboard;
