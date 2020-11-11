package com.arobld00.autentia.rest.dtos;

import com.arobld00.autentia.domain.Payment;
import com.arobld00.autentia.rest.dtos.validations.BigDecimalPositive;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

public class PaymentDto {

    private String provider;

    @BigDecimalPositive
    private BigDecimal amount;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String concept;

    private Duration timeDifference;

    public PaymentDto() {
        // Empty for framework
    }

    public PaymentDto(BigDecimal amount, String provider) {
        this.amount = amount;
        this.provider = provider;
    }

    public PaymentDto(Payment payment) {
        this.provider = payment.getProvider();
        this.amount = payment.getAmount();
        this.concept = payment.getConcept();
        this.setRegistrationDate(this.between(payment.getRegistrationDate()));

    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public Duration getRegistrationDate() {
        return timeDifference;
    }

    public void setRegistrationDate(Duration timeDifference) {
        this.timeDifference = timeDifference;
    }

    private Duration between(LocalDateTime registrationDate) {
        return Duration.between(registrationDate, LocalDateTime.now());
    }

    public Payment toPayment() {
        Payment payment = new Payment();
        BeanUtils.copyProperties(this, payment);
        return payment;
    }

    @Override
    public String toString() {
        return "PaymentDto{" +
                "provider='" + provider + '\'' +
                ", amount=" + amount +
                ", concept='" + concept + '\'' +
                ", timeDifference=" + timeDifference +
                '}';
    }

}
