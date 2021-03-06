package com.pismo.payments.controllers;

import javax.validation.Valid;

import com.pismo.payments.dtos.AccountDTO;
import com.pismo.payments.services.ICreateAccountService;
import com.pismo.payments.services.IGetAccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Api(value = "Api para gerenciar contas de transação", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, tags = {"Conta"})
public class AccountController {

    ICreateAccountService createAccountService;
    IGetAccountService getAccountService;

    @RequestMapping(value = "/accounts", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cria uma conta", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDTO> create(@Valid @RequestBody AccountDTO account) {
        var response = createAccountService.execute(account);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/accounts/{accountId}", method = RequestMethod.GET)
    @ApiOperation(value = "Busca uma conta")
    public ResponseEntity<AccountDTO> read(@Valid @PathVariable Long accountId) {
        var response = getAccountService.execute(accountId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
