package net.javaguides.banking_app.controller;

import net.javaguides.banking_app.dto.AccountDto;
import net.javaguides.banking_app.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//convert this class as spring mvc rest controller class
@RestController
//
@RequestMapping("/api/accounts")
public class AccountController {

    //constructor dependency injection
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    //ADD ACCOUNT REST API
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto)
    //requestbody converts json to java object
    {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    //GET ACCOUNT REST API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id)
    {
    AccountDto accountDto=accountService.getAccountById(id);
    return ResponseEntity.ok(accountDto);

    }


    //DEPOSIT REST API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit( @PathVariable Long id, @RequestBody Map<String,Double> request)
    {
        Double amount = request.get("amount");
           AccountDto accountDto= accountService.deposit(id,amount);
           return  ResponseEntity.ok(accountDto);


    }

    //WITHDRAW REST API
    @PutMapping("/{id}/withdraw")
    public  ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String,Double> request)
    {
        Double amount = request.get("amount");
        AccountDto accountDto=accountService.withdraw(id,amount);
        return ResponseEntity.ok(accountDto);

    }


    //GET ALL ACCOUNT REST API
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accounts =accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }
    //DELETE ACCOUNT REST API
    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteAccountById(@PathVariable Long id)
    {
        accountService.deleteAccountById(id);
        return ResponseEntity.ok("Account deleted");
    }
}

