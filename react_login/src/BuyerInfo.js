import React, { useState, useEffect } from 'react';
import './Styles.css';

const BuyerInfo = () => {
  const [buyerName, setBuyerName] = useState('');
  const [loyaltyPoints, setLoyaltyPoints] = useState('');

  const fetchBuyerInfo = async () => {
    try {
      const response = await fetch(`http://localhost:8080/api/buyers/buyer-info`, {
        method: 'GET',
        credentials: 'include',
      });

      if (!response.ok) {
        console.error('Error fetching buyer info. Status:', response.status);
        return;
      }

      const data = await response.json();
      console.log('Buyer Info from backend:', data);

      setBuyerName(data.buyerName);
      setLoyaltyPoints(data.loyaltyPoints);
    } catch (error) {
      console.error('Error fetching buyer info:', error);
    }
  };

  useEffect(() => {
    fetchBuyerInfo();
  }, []);

  return (
    <div className="buyer-info">
      <h1>Welcome, <span>{buyerName}</span>!</h1>
      <p>Loyalty Points: <span>{loyaltyPoints}</span></p>
    </div>
  );
};

export default BuyerInfo;
