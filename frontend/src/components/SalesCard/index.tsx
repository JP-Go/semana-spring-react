import React, { useState, useEffect } from 'react';
import NotificationButton from '../NotificationButton';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';

import './styles.css';
import axios from 'axios';
import { BASE_URL } from '../../utils/request';
import { Sale } from '../../models/sale';

function SalesCard () {
  const YEAR_BEFORE_TODAY_DATE = new Date(new Date().setDate(new Date().getDate() - 365));
  const TODAY_DATE = new Date();
  const [sales, setSales] = useState<Sale[]>([]);
  const [minDate, setminDate] = useState<Date>(YEAR_BEFORE_TODAY_DATE);
  const [maxDate, setmaxDate] = useState<Date>(TODAY_DATE);

  useEffect(() => {
    axios.get(`${BASE_URL}/sales`).then(
      (res) => {
        setSales(res.data.content);
      }
    ).catch(console.error);
  }, []);

  return <div className='dsmeta-card'>
            <h2 className='dsmeta-sales-title'>Vendas</h2>
            <div>
              <div className='dsmeta-form-control-container'>
                <DatePicker
                  selected={minDate}
                  onChange={(date:Date) => { setminDate(date); }}
                  className='dsmeta-form-control'
                  dateFormat='dd/MM/yyyy'/>
              </div>
              <div className='dsmeta-form-control-container'>
                <DatePicker
                  className='dsmeta-form-control'
                  selected={maxDate}
                  onChange={(date:Date) => { setmaxDate(date); }}
                  dateFormat='dd/MM/yyyy'/>
              </div>
            </div>

            <div>
              <table className='dsmeta-sales-table'>
                <thead>
                  <tr>
                    <th className='show992'>ID</th>
                    <th className='show576'>Data</th>
                    <th>Vendedor</th>
                    <th className='show992'>Visitas</th>
                    <th className='show992'>Vendas</th>
                    <th>Total</th>
                    <th>Notificar</th>
                  </tr>
                </thead>
                <tbody>
                  {sales.map((sale) =>
                    (<tr key={sale.id}>
                    <td className='show992'>#{sale.id}</td>
                    <td className='show576'>{new Date(sale.date).toLocaleDateString()}</td>
                    <td>{sale.sellerName}</td>
                    <td className='show992'>{sale.visited}</td>
                    <td className='show992'>{sale.deals}</td>
                    <td>R$ {sale.amount.toFixed(2)}</td>
                    <td>
                      <div className='dsmeta-red-btn-container'>
                        <NotificationButton/>
                      </div>
                    </td>
                  </tr>)
                  )}
                </tbody>

              </table>
            </div>

          </div>;
}

export default SalesCard;
