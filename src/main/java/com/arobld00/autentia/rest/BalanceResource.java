package com.arobld00.autentia.rest;

import com.arobld00.autentia.data_services.BalanceService;
import com.arobld00.autentia.rest.dtos.BalanceInputDto;
import com.arobld00.autentia.rest.dtos.DebtOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@PreAuthorize("hasRole('USER')")
@RestController
@RequestMapping(BalanceResource.BALANCE)
public class BalanceResource {

    public static final String BALANCE = "/balance";

    private BalanceService balanceService;

    @Autowired
    public BalanceResource(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    /* JSON:
        { "flow": {"Francisco Buyo":"59.15","Alfonso Pérez":"22.55","Raúl González":"-40.85","José María Gutiérrez":"-40.85"} }
     */
    @PostMapping
    public List<DebtOutputDto> calculateDebt(@RequestBody BalanceInputDto balanceDto) {
        return this.balanceService.calculateDebt(balanceDto);
    }

}
