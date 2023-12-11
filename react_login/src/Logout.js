import React, { useState } from 'react';


const Logout = () => {
  const [isSessionLoggedOut, setSessionLoggedOut] = useState(false);
  

  const handleLogout = async () => {
    
    setSessionLoggedOut(true);

    try {
      const response = await fetch(`http://localhost:8080/api/logout`, {
        method: 'POST',
        credentials: 'include',
      });

      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      console.log('User logged out successfully.');

      
      window.location.href = `/logout-message`;
    
    } catch (error) {
      console.error('Error logging out:', error);
    }
  };

  return (
    <div>
      <div id="logout-button">

      <a className="button-link" href="/api/cart/list">
          View Cart
        </a>

        <a className="button-link" href='/api/purchase-orders/list'>
          View Orders
        </a>
        
      <button className="button-link" onClick={handleLogout}>
          Logout
        </button>
       

        
        </div>
    </div>
  );
};

export default Logout;
