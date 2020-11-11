package com.arobld00.autentia.rest.dtos;

import java.math.BigDecimal;
import java.util.HashMap;

public class BalanceInputDto {

    private HashMap<String, BigDecimal> flow;

    public BalanceInputDto() {
        // Empty for framework
    }

    public BalanceInputDto(HashMap<String, BigDecimal> flow) {
        this.flow = flow;
    }

    public HashMap<String, BigDecimal> getFlow() {
        return flow;
    }

    @Override
    public String toString() {
        return "BalanceDto{" +
                "flow=" + flow +
                '}';
    }

}
