
import React from 'react';
import './Styles.css';


const LogoutMessage = () => {
  return (
    <div className='logout-message'>
      <h2>Logout Successful</h2>
      <p>
        You have been logged out. Please{' '}
        <a className="button-link" href="/api/login">
          login
        </a>{' '}
        to continue.
      </p>
    </div>
  );
};

export default LogoutMessage;