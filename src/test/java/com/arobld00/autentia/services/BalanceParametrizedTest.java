package com.arobld00.autentia.services;

import java.math.RoundingMode;
import java.util.*;

import java.math.BigDecimal;

import java.util.Arrays;
import java.util.Collection;

import com.arobld00.autentia.domain.Balance;
import com.arobld00.autentia.domain.Debt;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.Matchers;

@RunWith(Parameterized.class)
public class BalanceParametrizedTest {

    public Balance OUTBalanceFlow;

    @Parameter(0) public HashMap<String, BigDecimal> flow;

    @Parameter(1) public List<Debt> expectedList;

    private static long startTime;

    private static long finishTime;

    private static long sum = 0;

    private static long items = 0;

    private static long max = Long.MIN_VALUE;

    private static long min = Long.MAX_VALUE;

    @Parameters(name = "{index}")
    public static Collection<Object[]> data() {
        HashMap<String, BigDecimal> map0 = new HashMap<>();
        map0.put("Francisco Buyo", new BigDecimal(59.15));
        map0.put("Alfonso Pérez", new BigDecimal(22.55));
        map0.put("Raúl González", new BigDecimal(-40.85));
        map0.put("José María Gutiérrez", new BigDecimal(-40.85));
        List<Debt> expectedList0 = new ArrayList<>();
        expectedList0.add(new Debt("Raúl González", "Francisco Buyo", new BigDecimal(40.85)));
        expectedList0.add(new Debt("José María Gutiérrez", "Alfonso Pérez", new BigDecimal(22.55)));
        expectedList0.add(new Debt("José María Gutiérrez", "Francisco Buyo", new BigDecimal(18.30)));

        HashMap<String, BigDecimal> map1 = new HashMap<>();
        map1.put("Alberto", new BigDecimal(12.0));
        map1.put("Maria", new BigDecimal(62.0));
        map1.put("Cristian", new BigDecimal(-28.0));
        map1.put("Diego", new BigDecimal(-38.0));
        map1.put("Sandra", new BigDecimal(-8.0));
        List<Debt> expectedList1 = new ArrayList<>();
        expectedList1.add(new Debt("Diego", "Maria", new BigDecimal(38.00)));
        expectedList1.add(new Debt("Cristian", "Maria", new BigDecimal(24.00)));
        expectedList1.add(new Debt("Sandra", "Alberto", new BigDecimal(8.00)));
        expectedList1.add(new Debt("Cristian", "Alberto", new BigDecimal(4.00)));

        return Arrays
                .asList(new Object[][] {
                        {map0, expectedList0},
                        {map1, expectedList1} });

    }

    @Before
    public void before(){
        startTime = System.currentTimeMillis();
        OUTBalanceFlow = new Balance();
        OUTBalanceFlow.calculateFlowDebt(flow);
    }

    @Test
    public void testCalculateFlow() {
        for(int i = 0; i < expectedList.size(); i++) {
            assertThat(
                    expectedList.get(i).getAmount().setScale(2, RoundingMode.HALF_EVEN),
                    Matchers.comparesEqualTo(OUTBalanceFlow.getDebtList().get(i).getAmount())
            );
        }
    }

    @After
    public void after() {
        finishTime = System.currentTimeMillis();
        long time = finishTime - startTime;
        items++;
        sum += time;
        if (time>max){
            max = time;
        }
        if (time<min){
            min = time;
        }
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("max: " + max);
        System.out.println("avg: " + (sum/items));
        System.out.println("min: " + min);
    }

}
