import React, { useState, useEffect } from 'react';
//import { useAppContext } from './AppContext';

import Logout from './Logout';
import './Styles.css';
import BuyerInfo from './BuyerInfo';






const ProductList = ({ sessionLogout, buyerName, loyaltyPoints }) => {
  const [products, setProducts] = useState([]);
  const [productQuantities, setProductQuantities] = useState({});
  //const { buyerInfo } = useAppContext();
  const sessionBuyerId = sessionStorage.getItem('buyerId');
  const buyerId = sessionBuyerId;
  const [quantityToAdd, setQuantityToAdd] = useState(1);
  

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(`http://localhost:8080/api/products/list`, {
          method: 'GET',
          credentials: 'include',
        });

        if (!response.ok) {
          console.error('Error fetching product list. Status:', response.status);
          return;
        }

        const data = await response.json();
        console.log('Data from backend:', data);
        setProducts(data);
      } catch (error) {
        console.error('Error fetching product list:', error);
      }
    };

    if (buyerId) {
      console.log('Buyer ID:', buyerId);
      

      fetchData();
    } else {
      console.warn('Buyer ID is not available yet. Waiting for buyerId...');
    }
  }, [buyerId]);

  const handleAddToCart = async (productId, quantity) => {
    
      const url = `http://localhost:8080/api/cart/add?productId=${productId}&quantity=${quantity}`;
  
      const response = await fetch(url, {
        method: 'POST',
        credentials: 'include',
        headers: {
          'Content-Type': 'application/json',
        },
      });
  
      if (!response.ok) {
        console.error('Error adding product to cart. Status:', response.status);
        return;
      }
     
      
      window.location.href = 'http://localhost:3000/api/cart/list'; 
      
    }
  
  return (
    <div>
      <Logout sessionLogout={sessionLogout} />
      <BuyerInfo buyerName={buyerName} loyaltyPoints={loyaltyPoints} />
      <div className="container">
      <h1>Product List</h1>
      <div className="product-list">
        {products.map((product) => (
          <div key={product.productId} className="product-item">
            <img alt="Product Image" className="product-image" src={product.imageUrl} />
            <h2>{product.productName}</h2>
            <p>{`Price: $${product.price}`}</p>
            <p>{`Rating: ${product.rating}`}</p>
            <p>{`Review Count: ${product.reviewCount}`}</p>
            <p>{product.description}</p>
            <p>{`Quantity Available: ${product.quantityAvailable}`}</p>

            <form
              key={product.productId}
              onSubmit={(e) => {
                e.preventDefault();
                handleAddToCart(product.productId, productQuantities[product.productId] || 1);
              }}
            >
              <input
                min="1"
                name="quantity"
                type="number"
                value={productQuantities[product.productId] || 1}
                onChange={(e) => setProductQuantities({
                  ...productQuantities,
                  [product.productId]: e.target.value
                })}
                max={product.quantityAvailable}
              />
              <button className="button-link" type="submit">
                Add to Cart
              </button>
            </form>
          </div>
        ))}
      </div>

      <p>
        <a className="button-link" href="/api/products/category/1">
          List By Category Shirts
        </a>
      </p>
      <p>
        <a className="button-link" href="/api/products/category/2">
          List By Category Dresses
        </a>
      </p>
      <p>
      <a className="button-link" href="/api/products/category/3">
          List By Category Electronics
        </a>
      </p>
      </div>
      </div>
  );
};

export default ProductList;
