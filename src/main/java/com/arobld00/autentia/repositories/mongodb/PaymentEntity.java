package com.arobld00.autentia.repositories.mongodb;

import com.arobld00.autentia.domain.Payment;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document
public class PaymentEntity {

    @Id
    private String code;

    @DBRef(lazy = true)
    private FriendEntity provider;

    private BigDecimal amount;

    private String concept;

    private LocalDateTime registrationDate;

    public PaymentEntity() {
        this.registrationDate = LocalDateTime.now();
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getCode() {
        return code;
    }

    public FriendEntity getProvider() {
        return provider;
    }

    public void setProvider(FriendEntity provider) {
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

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Payment toPay() {
        Payment payment = new Payment();
        BeanUtils.copyProperties(this, payment);
        payment.setProvider(this.provider.getName());
        return payment;
    }

    @Override
    public int hashCode() {
        return this.code.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (code.equals(((PaymentEntity) obj).code));
    }

    @Override
    public String toString() {
        return "PayEntity{" +
                "provider=" + provider +
                ", amount=" + amount +
                ", concept='" + concept + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }

    public static class Builder {

        private PaymentEntity pay;

        private Builder() {
            this.pay = new PaymentEntity();
            this.pay.amount = BigDecimal.ONE;
            this.pay.concept = "";
        }

        public Builder provider(FriendEntity provider) {
            this.pay.provider = provider;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.pay.amount = amount;
            return this;
        }

        public Builder concept(String concept) {
            this.pay.concept = concept;
            return this;
        }

        public PaymentEntity build() {
            return this.pay;
        }

    }

}
