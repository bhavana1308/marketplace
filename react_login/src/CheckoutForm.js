import React, { useState, useEffect } from 'react';
import BuyerInfo from './BuyerInfo';
import Logout from './Logout';
import './Styles.css';

const CheckoutForm = ({ sessionLogout, buyerName, loyaltyPoints }) => {
  const sessionBuyerId = sessionStorage.getItem('buyerId');
  const buyerId = sessionBuyerId;
  const [purchaseAmount, setPurchaseAmount] = useState(0);
  const [loyaltyPointsEarned, setLoyaltyPointsEarned] = useState(0);

  useEffect(() => {
    const fetchCheckoutData = async () => {
      console.log("BuyerId before API call:", buyerId);
      try {
        const response = await fetch(`http://localhost:8080/api/form?buyerId=${buyerId}`, {
          method: 'GET',
          credentials: 'include',

        });

        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        const data = await response.json();
        if (data !== null) {
          setPurchaseAmount(data.purchaseAmount);
          setLoyaltyPointsEarned(data.loyaltyPointsEarned);
        } else {
          console.warn('API returned null data');

        }
      } catch (error) {
        console.error('Error fetching checkout data:', error);
      }
    };
    fetchCheckoutData();
  }, [buyerId]);

  const handleCheckout = async (event) => {
    event.preventDefault();

    const response = await fetch(`http://localhost:3000/api/process`, {
      method: 'POST',
      credentials: 'include',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },

    });
    window.location.href = `http://localhost:3000/api/process`;


  };

  return (
    <div>
      <BuyerInfo buyerName={buyerName} loyaltyPoints={loyaltyPoints} />
      <Logout sessionLogout={sessionLogout} />

      <div className="container1">

        <h1>Checkout Form</h1>
        <p>Purchase Amount: ${purchaseAmount}</p>
        <p>Loyalty Points Earned: {loyaltyPointsEarned}</p>

        <form onSubmit={handleCheckout}>
          <input name="buyerId" type="hidden" value={buyerId} />
          <input name="purchaseAmount" type="hidden" value={purchaseAmount} />
          <button className="button-link" type="submit">
            Process Checkout
          </button>
        </form>

        <p>
          <a className="button-link" href="/api/cart/list">
            Back to Cart
          </a>
        </p>
      </div>
    </div>
  );
};

export default CheckoutForm;
