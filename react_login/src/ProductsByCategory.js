import React, { useState, useEffect } from 'react';
import './Styles.css';
import BuyerInfo from './BuyerInfo';
import Logout from './Logout';
import { useParams } from 'react-router-dom';




const ProductsByCategory = ({ sessionLogout, buyerName, loyaltyPoints}) => {
    const [categoryInfo, setCategoryInfo] = useState({ categoryName: '', products: [] });
    const sessionBuyerId = sessionStorage.getItem('buyerId');
    const buyerId = sessionBuyerId;
    console.log(buyerId);
    const { categoryId } = useParams();

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch(`http://localhost:8080/api/products/category/${categoryId}`, {
                    method: 'GET',
                    credentials: 'include',
                    
                });
                const data = await response.json();

                console.log(data);
    
                setCategoryInfo({
                    categoryName: data.categoryName,
                    products: data.products,
                });
            } catch (error) {
                console.error('Error fetching products in category:', error);
            }
        };
    
       
        if (categoryId && buyerId !== undefined) {
            fetchData();
        }
    }, [categoryId, buyerId]);
    
    return (
        <div>
            <Logout sessionLogout={sessionLogout} />
            <BuyerInfo buyerName={buyerName} loyaltyPoints={loyaltyPoints} />
            <h1>Products in Category {categoryInfo.categoryName}</h1>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Rating</th>
                        <th>ReviewCount</th>
                        <th>Description</th>
                        <th>Quantity Available</th>
                        <th>Image</th>
                    </tr>
                </thead>
                <tbody>
                    {categoryInfo.products.map((product) => (
                        <tr key={product.productId}>
                            <td>{product.productId}</td>
                            <td>{product.productName}</td>
                            <td>{product.price}</td>
                            <td>{product.rating}</td>
                            <td>{product.reviewCount}</td>
                            <td>{product.description}</td>
                            <td>{product.quantityAvailable}</td>
                            <td><img alt="Product Image" className="product-image" src={product.imageUrl}></img> </td>

                        </tr>
                    ))}
                </tbody>
            </table>
            <p><a className="button-link" href="/api/products/list">Go To ProductList</a></p>
        </div>
    );
};

export default ProductsByCategory;


