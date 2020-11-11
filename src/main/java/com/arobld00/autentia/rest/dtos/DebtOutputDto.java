package com.arobld00.autentia.rest.dtos;

import com.arobld00.autentia.domain.Debt;

import java.math.BigDecimal;

public class DebtOutputDto {

    private String fromDebtor;

    private String toBeneficiary;

    private BigDecimal amount;

    public DebtOutputDto() {
        // Empty for framework
    }

    public DebtOutputDto(Debt debt) {
        this.fromDebtor = debt.getFromDebtor();
        this.toBeneficiary = debt.getToBeneficiary();
        this.amount = debt.getAmount();
    }

    public String getFromDebtor() {
        return fromDebtor;
    }

    public void setFromDebtor(String fromDebtor) {
        this.fromDebtor = fromDebtor;
    }

    public String getToBeneficiary() {
        return toBeneficiary;
    }

    public void setToBeneficiary(String toBeneficiary) {
        this.toBeneficiary = toBeneficiary;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "DebtDto{" +
                "fromDebtor='" + fromDebtor + '\'' +
                ", toBeneficiary='" + toBeneficiary + '\'' +
                ", amount=" + amount +
                '}';
    }

}
