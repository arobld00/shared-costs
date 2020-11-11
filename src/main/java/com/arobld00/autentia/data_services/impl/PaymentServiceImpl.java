package com.arobld00.autentia.data_services.impl;

import com.arobld00.autentia.data_services.PaymentService;
import com.arobld00.autentia.domain.Payment;
import com.arobld00.autentia.repositories.PaymentPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {

    private PaymentPersistence paymentPersistence;

    @Autowired
    public PaymentServiceImpl(PaymentPersistence paymentPersistence) {
        this.paymentPersistence = paymentPersistence;
    }

    @Override
    public Payment create(Payment payment) {
        return this.paymentPersistence.create(payment);
    }

    @Override
    public List<Payment> readAll() {
        return this.paymentPersistence.readAll();
    }

}
