package com.arobld00.autentia.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {

    private String code;

    private String provider;

    private BigDecimal amount;

    private String concept;

    private LocalDateTime registrationDate;

    public Payment() {
        // Empty for framework
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public int hashCode() {
        return this.code.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (code.equals(((Payment) obj).code));
    }

    @Override
    public String toString() {
        return "Pay{" +
                "code='" + code + '\'' +
                ", provider=" + provider +
                ", amount=" + amount +
                ", concept='" + concept + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }

}
