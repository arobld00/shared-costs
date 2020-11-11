package com.arobld00.autentia.repositories.mongodb;

import com.arobld00.autentia.domain.Payment;
import com.arobld00.autentia.domain.exceptions.NotFoundException;
import com.arobld00.autentia.repositories.PaymentPersistence;
import com.arobld00.autentia.rest.dtos.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository("payPersistence")
public class PaymentPersistenceMongodb implements PaymentPersistence {

    private PaymentRepository paymentRepository;

    private FriendRepository friendRepository;

    @Autowired
    public PaymentPersistenceMongodb(PaymentRepository paymentRepository,
                                     FriendRepository friendRepository) {
        this.paymentRepository = paymentRepository;
        this.friendRepository = friendRepository;
    }

    @Override
    public List<Payment> readAll() {
        return this.paymentRepository
                .findAll().stream()
                .map(PaymentEntity::toPay)
                .collect(Collectors.toList());
    }

    @Override
    public Payment create(Payment in) {
        FriendEntity provider = null;
        if (in.getProvider() != null) {
            provider = this.friendRepository.findByName(in.getProvider()).orElseThrow(
                    () -> new NotFoundException("Friend (" + in.getProvider() + ")")
            );
        }

        String concept = (in.getConcept() == null) ? "Pago" : in.getConcept();
        PaymentEntity payment = PaymentEntity.builder().amount(in.getAmount()).provider(provider).
                concept(concept).build();
        return this.paymentRepository.save(payment).toPay();
    }

}
