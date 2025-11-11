package net.javaguides.banking_app.service.impl;

import net.javaguides.banking_app.dto.AccountDto;
import net.javaguides.banking_app.entity.Account;
import net.javaguides.banking_app.mapper.AccountMapper;
import net.javaguides.banking_app.repository.AccountRepository;
import net.javaguides.banking_app.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//TO AUTOMATICALLY CREATE SPRING BEAN FOR THIS CLASS
@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account= AccountMapper.mapToAccount(accountDto);
        Account saveAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saveAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
       Account account= accountRepository
               .findById(id)
               .orElseThrow(() ->new RuntimeException("Account not found with this id"));
                return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {

        Account account=accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));

     double total  = account.getBalance() + amount;
        account.setBalance(total);
       Account savedAccount =accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);


    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account=accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));

        if(account.getBalance()<amount )
        {
            throw new RuntimeException("Insufficient balance");
        }
       double total = account.getBalance()-amount;
        account.setBalance(total);
        Account saved=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saved);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
      List<Account> accounts  =accountRepository.findAll();
      return accounts.stream().map((account)->AccountMapper.mapToAccountDto(account))
              .collect(Collectors.toList());

    }

    @Override
    public void deleteAccountById(Long id) {
        Account account=accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));

        accountRepository.deleteById(id);

    }

}
