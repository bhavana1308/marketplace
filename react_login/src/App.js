import React from 'react';
import { BrowserRouter } from 'react-router-dom'
import { Routes, Route, Link } from 'react-router-dom';
import AddBuyer from './AddBuyer';
import UpdateBuyer from './UpdateBuyer';
import BuyersList from './BuyersList';
import CartList from './CartList';
import ProductList from './ProductList';
import CheckoutForm from './CheckoutForm';
import PurchaseOrdersResult from './PurchaseOrdersResult';
import PurchaseOrdersList from './PurchaseOrdersList';
import LoginForm from './LoginForm';
import ProductsByCategory from './ProductsByCategory';
import Logout from './Logout';
import LogoutMessage from './LogoutMessage';
import OrderDetails from './OrderDetails';

function App() {
  return (

    <Routes>


      <Route path="api/buyers/add" element={<AddBuyer />} />
      <Route path="api/buyers/edit" element={<UpdateBuyer />} />
      <Route path="/api/buyers/list" element={<BuyersList />} />

      <Route path="/api/login" element={<LoginForm />} />
      <Route path="/login/loginError" />
      <Route path="/api/products/list" element={<ProductList />} />
      <Route path="/api/products/category/:categoryId" element={<ProductsByCategory />} />
      <Route path="/api/cart/list" element={<CartList />} />

      <Route path="api/form" element={<CheckoutForm />} />


      <Route path="/api/process" element={<PurchaseOrdersResult />} />
      <Route path="/api/purchase-orders/list" element={<PurchaseOrdersList />} />
      <Route path="/api/logout" element={<Logout />} />
      <Route path="/logout-message" element={<LogoutMessage />} />
      <Route path="/api/orderDetails/:orderNumber" element={<OrderDetails />} />


    </Routes>

  );
}

export default App;
