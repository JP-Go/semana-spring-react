package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

  @Value("${twilio.sid}")
  private String twilioSid;

  @Value("${twilio.key}")
  private String twilioKey;

  @Value("${twilio.phone.from}")
  private String twilioPhoneFrom;

  @Value("${twilio.phone.to}")
  private String twilioPhoneTo;

  @Autowired private SaleRepository saleRepository;

  public void sendSms(Long saleId) {

    Sale sale = saleRepository.findById(saleId).get();
    String formattedDate = sale.getDate().getMonthValue() + "/" + sale.getDate().getYear();
    String amountSold = String.format("%.2f", sale.getAmount());

    String msg =
        "O Vendedor "
            + sale.getSellerName()
            + "foi destaque em "
            + formattedDate
            + "vendendo um total de R$ "
            + amountSold;

    Twilio.init(twilioSid, twilioKey);

    PhoneNumber to = new PhoneNumber(twilioPhoneTo);
    PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

    Message message = Message.creator(to, from, msg).create();

    System.out.println(
        "[TWILIO] Message was sent in "
            + message.getDateSent().format(DateTimeFormatter.ISO_DATE_TIME));
  }
}
