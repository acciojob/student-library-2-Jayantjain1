package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.BookRepository;
import com.driver.repositories.CardRepository;
import com.driver.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    BookRepository bookRepository5;

    @Autowired
    CardRepository cardRepository5;

    @Autowired
    TransactionRepository transactionRepository5;

    @Value("${books.max_allowed}")
    public int max_allowed_books;

    @Value("${books.max_allowed_days}")
    public int getMax_allowed_days;

    @Value("${books.fine.per_day}")
    public int fine_per_day;

    public String issueBook(int cardId, int bookId) throws Exception {
        //check whether bookId and cardId already exist
        //conditions required for successful transaction of issue book:
        //1. book is present and available
        // If it fails: throw new Exception("Book is either unavailable or not present");
        //2. card is present and activated
        // If it fails: throw new Exception("Card is invalid");
        //3. number of books issued against the card is strictly less than max_allowed_books
        // If it fails: throw new Exception("Book limit has reached for this card");
        //If the transaction is successful, save the transaction to the list of transactions and return the id
        //Note that the error message should match exactly in all cases
        Book book = bookRepository5.findById(bookId).get();
        Card card = cardRepository5.findById(cardId).get();
        Transaction transaction = new Transaction();
        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setIssueOperation(true);
        transaction.setTransactionDate(new Date());

        if(book == null && book.isAvailable() == false){
            transactionRepository5.save(transaction);
            throw new Exception("Book is either unavailable or not present");
        }
        if(card == null && card.getCardStatus().equals(CardStatus.DEACTIVATED)){
            transactionRepository5.save(transaction);
            throw new Exception("Card is invalid");
        }
        if(card.getBooks().size() >= max_allowed_books) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            throw new Exception("Book limit has reached for this card");
        }
        transaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);
        book.setAvailable(false);

        List<Book> bookList = card.getBooks();
        bookList.add(book);
        card.setBooks(bookList);

        book.setCard(card);
        return Integer.toString(transaction.getId());
//        return null; //return transactionId instead
    }

    public Transaction returnBook(int cardId, int bookId) throws Exception{

        List<Transaction> transactions = transactionRepository5.find(cardId, bookId, TransactionStatus.SUCCESSFUL, true);
        Transaction transaction = transactions.get(transactions.size() - 1);
        Book book = transaction.getBook();
        book.setAvailable(true);

        //for the given transaction calculate the fine amount considering the book has been returned exactly when this function is called
        //make the book available for other users
        //make a new transaction for return book which contains the fine amount as well

        Book book1 = bookRepository5.findById(bookId).get();
        Card card = book1.getCard();
        List<Book> bookList = card.getBooks();
        Book tempBook = null;
        for(Book book2: bookList){
            if(book2.getId() == bookId){
                tempBook = book2;
                break;
            }
        }
        bookList.remove(tempBook);
        card.setBooks(bookList);
        book1.setCard(card);
        Transaction transaction1 = (Transaction) tempBook.getTransactions();
        return transaction1;

//        Transaction returnBookTransaction  = null;
//        return returnBookTransaction; //return the transaction after updating all details
    }
}