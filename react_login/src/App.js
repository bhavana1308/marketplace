import React from 'react';
import { BrowserRouter } from 'react-router-dom'
import { Routes, Route, Link } from 'react-router-dom';

import LoginForm from './LoginForm';

function App() {
  return (
    <Routes>
      <Route path="/login" element={<LoginForm />} />
      <Route path="/productList" />
    </Routes>
  );
}

export default App;
