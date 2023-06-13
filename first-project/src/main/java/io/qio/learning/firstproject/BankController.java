package io.qio.learning.firstproject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BankController {
    List<Account> accountList = new ArrayList<>();
    @PostMapping("/account/create")
    public void createAccount(@RequestBody Account account){
        System.out.println(account);
        accountList.add(account);
    }
    @GetMapping("/account/all")
    public List<Account> getAllAccounts(){
        return accountList;
    }
    @GetMapping("/account/search/{accountNumber}")
    public ResponseEntity searchAccount(@PathVariable("accountNumber") int accountNumber)throws Exception{
       List <Account> filteredAccounts = accountList.stream().filter(a -> a.getAccountNumber()==accountNumber).collect(Collectors.toList());
        if(filteredAccounts.isEmpty()){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");

        }
        return ResponseEntity.ok(filteredAccounts.get(0));

    }
    @PutMapping("/account/withdraw")
    public ResponseEntity withdraw(@RequestBody AccountRequest request){
        List <Account> filteredAccounts = accountList.stream().filter(a -> a.getAccountNumber()==request.getAccountNumber()).collect(Collectors.toList());
        if(filteredAccounts.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");

        }
        Account account = filteredAccounts.get(0);
        account.setBalance(account.getBalance()- request.getAmount());
        return ResponseEntity.ok("withdraw successfull");

    }
    @PutMapping("/account/deposit")
    public ResponseEntity deposit(@RequestBody AccountRequest request){
        List <Account> filteredAccounts = accountList.stream().filter(a -> a.getAccountNumber()== request.getAccountNumber()).collect(Collectors.toList());
        if(filteredAccounts.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");

        }
        Account account = filteredAccounts.get(0);
        account.setBalance(account.getBalance()+request.getAmount());
        return ResponseEntity.ok("deposit successfull");

    }
    @DeleteMapping("/account/close/{accountNumber}")
    public ResponseEntity closeAccount(@PathVariable("accountNumber") int accountNumber){
        List <Account> filteredAccounts = accountList.stream().filter(a -> a.getAccountNumber()==accountNumber).collect(Collectors.toList());
        if(filteredAccounts.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");

        }
        Account account = filteredAccounts.get(0);
        accountList.remove(account);
        return ResponseEntity.ok("Account is closed");

    }
}
