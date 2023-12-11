import React, { useState ,useEffect} from 'react';
import BuyerInfo from './BuyerInfo';
import Logout from './Logout';
import './Styles.css';
import OrderDetails from './OrderDetails';

const PurchaseOrdersResult = ({sessionLogout, buyerName, loyaltyPoints }) => {
  const buyerId = sessionStorage.getItem('buyerId');
  const [purchaseOrderData, setPurchaseOrderData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(`http://localhost:8080/api/process`, {
          method: 'GET',
          credentials: 'include',
        });

        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const result = await response.json();
        setPurchaseOrderData(result);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    if (buyerId) {
      fetchData();
    }
  }, [buyerId]);

  return (
    <div>
    <BuyerInfo buyerName={buyerName} loyaltyPoints={loyaltyPoints} />
 <Logout sessionLogout={sessionLogout} />
  
 <div className="container1">

      <h1>Thanks for Placing Your Order!</h1>
      
      <p>Your Loyalty Points: {purchaseOrderData.loyaltyPoints}</p>

      <p>Your order has been successfully placed. </p>
      <p>Your Order Number is: <a href={`/api/orderDetails/${purchaseOrderData.orderNumber}`}>{purchaseOrderData.orderNumber}
      </a></p>
      {/* <OrderDetails orderNumber={purchaseOrderData.orderNumber} /> */}

      <p><a className="button-link" href="/api/products/list">Go back to Product List</a></p>
      <a className="button-link" href="/api/buyers/list">View Buyers</a>
      <p><a className="button-link" href="/api/purchase-orders/list">View Purchase Orders</a></p>
    </div>
    </div>
  );
};

export default PurchaseOrdersResult;
