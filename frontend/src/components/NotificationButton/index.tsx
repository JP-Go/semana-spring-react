import axios from 'axios';
import React from 'react';

import icon from '../../assets/img/notification-icon.svg';
import { BASE_URL } from '../../utils/request';
import './styles.css';

type notificationButtonProps = {
  saleId: number
}

function handleClick (saleId:number) {
  axios.get(`${BASE_URL}/sales/${saleId}/notification`).then((_) => {
    console.log('Success');
  });
}

function NotificationButton ({ saleId }:notificationButtonProps) {
  return <div className='dsmeta-red-btn' onClick={() => handleClick(saleId)}>
    <img src={icon} alt='notificar'/>
  </div>;
}

export default NotificationButton;
