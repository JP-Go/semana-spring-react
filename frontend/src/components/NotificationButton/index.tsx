import React from 'react';

import icon from '../../assets/img/notification-icon.svg';
import styles from './styles.module.css';

function NotificationButton () {
  return <div className={styles.btn}>
    <img src={icon} alt='notificar'/>
  </div>;
}

export default NotificationButton;
