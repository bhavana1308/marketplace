import React, { useState, useEffect } from 'react';
import './Styles.css';

const BuyersList = () => {
  const [buyers, setBuyers] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/buyers/list', {
          method: 'GET',
          headers: {
            'Content-Type': 'html/text',
          },
        });

        if (!response.ok) {
          throw new Error(`Server returned ${response.status} ${response.statusText}`);
        }

        const data = JSON.parse(await response.text());
        setBuyers(data);
      } catch (error) {
        console.error('Error fetching buyers:', error);
      }
    };

    fetchData();
  }, []);


  const handleDelete = async (buyerId) => {
    try {
      const response = await fetch(`http://localhost:8080/api/buyers/delete?buyerId=${buyerId}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ buyerId }),
      });

      if (response.ok) {
        console.log('Buyer deleted successfully');
      } else {
        console.error('Failed to delete buyer');
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  return (
    <div>
      <div>
        <h1>List of Buyers</h1>

        <table border="1">
          <thead>
            <tr>
              <th>Buyer ID</th>
              <th>Email</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Password</th>
              <th>Loyalty Points</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {buyers.map((buyer) => (
              <tr key={buyer.buyerId}>
                <td>{buyer.buyerId}</td>
                <td>{buyer.email}</td>
                <td>{buyer.firstName}</td>
                <td>{buyer.lastName}</td>
                <td>{buyer.password}</td>
                <td>{buyer.loyaltyPoints}</td>
                <td>
                  <form
                    onSubmit={(e) => {
                      e.preventDefault();
                      handleDelete(buyer.buyerId);
                    }}
                  >
                    <button type="submit">Delete</button>
                  </form>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <p>
        <a className="button-link" href="/api/buyers/add">
          Add Buyers
        </a>
      </p>
      <p>
        <a className="button-link" href="/api/buyers/edit">
          Edit Buyers
        </a>
      </p>
    </div>
  );
};

export default BuyersList;
