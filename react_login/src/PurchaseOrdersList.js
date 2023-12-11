
import React, { useState ,useEffect} from 'react';
import './Styles.css';

const PurchaseOrdersList = () => {
  const [purchaseOrders, setPurchaseOrders] = useState([]);
  const sessionBuyerId = sessionStorage.getItem('buyerId');
  const buyerId = sessionBuyerId;

  useEffect(() => {
    const fetchPurchaseOrders = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/purchase-orders/list',{
          method: 'GET',
          credentials: 'include',

        });
        const data = await response.json();
        setPurchaseOrders(data);
        console.log(data);
      } catch (error) {
        console.error('Error fetching purchase orders:', error);
      }
    };

    fetchPurchaseOrders();
  }, [buyerId]);
 

    return (
      <div>
        <h1>Purchase Orders</h1>
        <table>
          <thead>
            <tr>
              <th>Order ID</th>
              <th>Order Number</th>
              <th>Buyer ID</th>
              <th>Buyer Name</th>
              <th>Date Ordered</th>

            </tr>
          </thead>
          <tbody>
            {purchaseOrders.map(order => (
              <tr key={order.orderId}>
                <td>{order.orderId}</td>
                <td><a href={`/api/orderDetails/${order.orderNumber}`}>{order.orderNumber}</a></td>

                <td>{order.buyer.buyerId}</td>
                <td>{order.buyer.firstName}</td>
                <td>{order.orderDate}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    );
            }    

export default PurchaseOrdersList;
