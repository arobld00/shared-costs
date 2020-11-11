package com.arobld00.autentia.data_services.impl;

import com.arobld00.autentia.data_services.BalanceService;
import com.arobld00.autentia.domain.Balance;
import com.arobld00.autentia.domain.Debt;
import com.arobld00.autentia.domain.exceptions.BalanceCalculationException;
import com.arobld00.autentia.rest.dtos.BalanceInputDto;
import com.arobld00.autentia.rest.dtos.DebtOutputDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service("balanceService")
public class BalanceServiceImpl implements BalanceService {

    public List<DebtOutputDto> calculateDebt(BalanceInputDto balanceDto) {
        List<DebtOutputDto> debtList = new ArrayList<>();
        Balance balance = new Balance();

        try {
            balance.calculateFlowDebt(balanceDto.getFlow());
            for (Debt debt : balance.getDebtList()) {
                debtList.add(new DebtOutputDto(debt));
            }
        } catch (BalanceCalculationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Balance calculation" , e);
        }

        return debtList;
    }

}
