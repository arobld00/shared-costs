package com.arobld00.autentia.rest;

import com.arobld00.autentia.data_services.PaymentService;
import com.arobld00.autentia.domain.Payment;
import com.arobld00.autentia.rest.dtos.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

//@PreAuthorize("hasRole('USER')")
@RestController
@RequestMapping(PaymentResource.PAYMENTS)
public class PaymentResource {

    public static final String PAYMENTS = "/payments";

    private PaymentService paymentService;

    @Autowired
    public PaymentResource(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /* JSON:
        {"amount":249.99,"provider":"5f5783f42202a72beef978f6","concept":"Kindle Oasis"}
    */
    @PostMapping
    public PaymentDto createPayment(@Valid @RequestBody PaymentDto paymentDto) {
        Payment payment = this.paymentService.create(paymentDto.toPayment());
        return new PaymentDto(payment);
    }

    @GetMapping
    public List<PaymentDto> readAll() {
        return this.paymentService.readAll()
                .stream()
                .map(PaymentDto::new)
                .collect(Collectors.toList());
    }

}

