import React, { useState, useEffect } from 'react';
import BuyerInfo from './BuyerInfo';
import Logout from './Logout';
import './Styles.css';


const CartList = ({ sessionLogout, buyerName, loyaltyPoints }) => {
  const sessionBuyerId = sessionStorage.getItem('buyerId');
  const buyerId = sessionBuyerId;
  const [cartItemsAndTotalPrice, setCartItemsAndTotalPrice] = useState([]);

  const fetchData = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/cart/list', {
        method: 'GET',
        credentials: 'include',
      });
      const data = await response.json();

      console.log("Received data:", data);

      setCartItemsAndTotalPrice(data || []);
    } catch (error) {
      console.error('Error fetching cart items:', error);
    }
  };

  useEffect(() => {
    if (buyerId) {
      fetchData();
    }
  }, [buyerId]);

  const handleDeleteCartItem = async (cartItemId) => {
    try {
      console.log(cartItemId);
      await fetch(`http://localhost:8080/api/cart/delete?cartItemId=${cartItemId}`, {
        method: 'POST',
        credentials: 'include',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        },

      });


      fetchData();
    } catch (error) {
      console.error('Error deleting cart item:', error);
    }
  };






  return (
    <div>
      <BuyerInfo buyerName={buyerName} loyaltyPoints={loyaltyPoints} />
      <Logout sessionLogout={sessionLogout} />

      <div className='container1'>

        <h1>Shopping Cart</h1>
        <table>
          <thead>
            <tr>
              <th>Product Name</th>
              <th>Price</th>
              <th>Quantity</th>
              <th>Total Price</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {cartItemsAndTotalPrice.slice(0, -1).map((cartItem, index) => (
              <tr key={index}>
                <td>{cartItem.productName}</td>
                <td>{`$${cartItem.price}`}</td>
                <td>{cartItem.quantity}</td>
                <td>{`$${cartItem.totalProductPrice}`}</td>
                <td>
                  <button
                    className="button-link"

                    onClick={() => handleDeleteCartItem(cartItem.cartId)}

                  >
                    Delete from Cart
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        <p>{cartItemsAndTotalPrice.length === 0 ? 'Your shopping cart is empty.' : null}</p>


        <p>
          Total Price: <span>{`$${cartItemsAndTotalPrice[cartItemsAndTotalPrice.length - 1] || 0}`}</span>
        </p>

        <p>
          <a className="button-link" href="/api/products/list">
            Go To ProductList
          </a>
        </p>

        <a className="button-link" href="/api/form">
          Go to Checkout Form
        </a>
      </div>
    </div>
  );
};

export default CartList;
