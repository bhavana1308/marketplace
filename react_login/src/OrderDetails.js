import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import BuyerInfo from './BuyerInfo';
import Logout from './Logout';

const OrderDetails = ({ sessionLogout, buyerName, loyaltyPoints }) => {
  const sessionBuyerId = sessionStorage.getItem('buyerId');
  const buyerId = sessionBuyerId;
  const [orderDetails, setOrderDetails] = useState(null);
  const { orderNumber } = useParams();

  useEffect(() => {
    const fetchOrderDetails = async () => {
      try {
        const response = await fetch(`http://localhost:8080/api/orderDetails/${orderNumber}`);
        const data = await response.json();
        setOrderDetails(data);
      } catch (error) {
        console.error('Error fetching order details:', error);
      }
    };

    fetchOrderDetails();
  }, [buyerId, orderNumber]);

  return (
    <div>
      <Logout sessionLogout={sessionLogout} />
      <BuyerInfo buyerName={buyerName} loyaltyPoints={loyaltyPoints} />
      <div className='container1'>
        <h1>Order Details</h1>
  
        {orderDetails ? (
          <>
            <p>Order Number: {orderDetails.orderNumber}</p>
  
            {orderDetails.orderItems && orderDetails.orderItems.length > 0 ? (
              <>
                <h2>Order Items</h2>
                <table>
                  <thead>
                    <tr>
                      <th>Product Name</th>
                      <th>Price</th>
                      <th>Quantity</th>
                      <th>Total Product Price</th>
                    </tr>
                  </thead>
                  <tbody>
                    {orderDetails.orderItems.map((item, index) => (
                      <tr key={index}>
                        <td>{item.productName}</td>
                        <td>{item.price}</td>
                        <td>{item.quantity}</td>
                        <td>{item.totalProductPrice}</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
  
                <p>Total Order Price: {orderDetails.totalOrderPrice}</p>
              </>
            ) : (
              <p>No order items available.</p>
            )}
          </>
        ) : (
          <p>No order details available.</p>
        )}
        <a className="button-link" href="/api/products/list">Go To ProductList</a>
      </div>
      
    </div>
  );
        };
        export default OrderDetails;  