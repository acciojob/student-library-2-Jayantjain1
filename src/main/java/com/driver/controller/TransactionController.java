package com.driver.controller;

import com.driver.repositories.TransactionRepository;
import com.driver.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Add required annotations
@RestController
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    //Add required annotations
    @PostMapping("issue-book")
    public ResponseEntity issueBook(@RequestParam("cardId") int cardId, @RequestParam("bookId") int bookId) throws Exception{
        try {
            transactionService.issueBook(cardId,bookId);
            return new ResponseEntity<>("transaction completed", HttpStatus.ACCEPTED);
        }catch (Exception e){
            throw new Exception("not possible");
        }
    }

    //Add required annotations
    @PostMapping("return-book")
    public ResponseEntity returnBook(@RequestParam("cardId") int cardId, @RequestParam("bookId") int bookId) throws Exception{
        try {
            transactionService.returnBook(cardId,bookId);
            return new ResponseEntity<>("transaction completed", HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            throw new Exception("not possible");
        }
    }
}
