package com.arobld00.autentia.domain;

import java.math.BigDecimal;

public class Debt {

    private String fromDebtor;

    private String toBeneficiary;

    private BigDecimal amount;

    public Debt(String fromDebtor, String toBeneficiary, BigDecimal amount) {
        this.fromDebtor = fromDebtor;
        this.toBeneficiary = toBeneficiary;
        this.amount = amount;
    }

    public String getFromDebtor() {
        return fromDebtor;
    }

    public String getToBeneficiary() {
        return toBeneficiary;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return fromDebtor + " -> " + toBeneficiary + " (" + amount + "€)";
    }

}
