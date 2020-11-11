package com.arobld00.autentia.data_services;

import com.arobld00.autentia.rest.dtos.BalanceInputDto;
import com.arobld00.autentia.rest.dtos.DebtOutputDto;

import java.util.List;

public interface BalanceService {

    List<DebtOutputDto> calculateDebt(BalanceInputDto balanceDto);

}
