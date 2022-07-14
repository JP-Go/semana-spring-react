package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SaleService {

  @Autowired private SaleRepository repository;

  public Page<Sale> findSales(String minDate, String maxDate, Pageable pageable) {

    LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
    LocalDate aYearBeforeToday = today.minusYears(1);

    LocalDate min = minDate.isEmpty() ? aYearBeforeToday : LocalDate.parse(minDate);
    LocalDate max = maxDate.isEmpty() ? today : LocalDate.parse(maxDate);

    return repository.findSales(min, max, pageable);
  }
}
