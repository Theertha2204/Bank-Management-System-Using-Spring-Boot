package net.javaguides.banking_app.mapper;

import net.javaguides.banking_app.dto.AccountDto;
import net.javaguides.banking_app.entity.Account;

public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto)
            //convert AccountDto into Account
    {
        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()


        );
        return account;
    }

    public  static AccountDto mapToAccountDto(Account account)
//    convert Account into AccountDto
    {
        AccountDto accountDto=new AccountDto
                (
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
               );
        return accountDto;
    }
}
