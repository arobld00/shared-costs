package com.arobld00.autentia.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Balance {

    private List<Debt> debtList;

    public Balance() {
        debtList = new ArrayList<Debt>();
    }

    public void calculateFlowDebt(HashMap<String, BigDecimal> flow) {
        // TODO assert flow != null
        BigDecimal maxDebt = Collections.max(flow.values());
        BigDecimal minDebt = Collections.min(flow.values());
        if (maxDebt != minDebt) {
            String recipient = getKeyFromValue(flow, maxDebt).toString();
            String sender = getKeyFromValue(flow, minDebt).toString();
            BigDecimal result = maxDebt.add(minDebt);
            result = round(result, 1);
            if (result.compareTo(BigDecimal.ZERO) >= 0) {
                debtList.add(
                        new Debt(sender, recipient, round(minDebt, 2).abs())
                );
                flow.remove(recipient);
                flow.remove(sender);
                flow.put(recipient, result);
                flow.put(sender, BigDecimal.ZERO);
            } else {
                debtList.add(
                        new Debt(sender, recipient, round(maxDebt, 2).abs())
                );
                flow.remove(recipient);
                flow.remove(sender);
                flow.put(recipient, BigDecimal.ZERO);
                flow.put(sender, result);
            }
            calculateFlowDebt(flow);
        }
    }

    private static Object getKeyFromValue(HashMap map, BigDecimal value) {
        for (Object o : map.keySet()) {
            if (map.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }

    private static BigDecimal round(BigDecimal value, int places) {
        if (places < 0)
            throw new IllegalArgumentException();
        return value.setScale(places, RoundingMode.HALF_UP);
    }

    public List<Debt> getDebtList() {
        return debtList;
    }

}
