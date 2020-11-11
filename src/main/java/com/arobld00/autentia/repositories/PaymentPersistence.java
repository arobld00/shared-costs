package com.arobld00.autentia.repositories;

import com.arobld00.autentia.domain.Payment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentPersistence {

    List<Payment> readAll();

    Payment create(Payment payment);

}
