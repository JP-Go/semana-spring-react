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
    String sellerName = sale.getSellerName();
    String formattedDate = sale.getDate().format(DateTimeFormatter.ofPattern("MM/yyyy"));
    String amountSold = String.format("%.2f", sale.getAmount());

    String msg =
        String.format(
            "O Vendedor %s foi destaque em %s com um total de R$ %s",
            sellerName, formattedDate, amountSold);
    Twilio.init(twilioSid, twilioKey);

    PhoneNumber to = new PhoneNumber(twilioPhoneTo);
    PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

    Message message = Message.creator(to, from, msg).create();

    System.out.println(message.getSid());
  }
}
