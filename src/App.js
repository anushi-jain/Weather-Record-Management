import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Login from './components/Login'; // Make sure to provide the correct path to your Login component

function App() {
  return (
    <Router>
      <div>
        <h1>Login</h1>
        {/* Your other components/routes go here */}
        <Routes>
          <Route path="/" component={Login} />
          {/* Other routes go here */}
        </Routes>
      </div>
    </Router>
  );
}

export default App;
