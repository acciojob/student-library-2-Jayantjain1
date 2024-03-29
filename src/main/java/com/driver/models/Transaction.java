package com.driver.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String transactionId = UUID.randomUUID().toString(); // externalId

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("books")
    private Card card;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("transactions")
    private Book book;

    private int fineAmount;

    @Column(columnDefinition = "TINYINT(1)")
    private boolean isIssueOperation;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;

    @CreationTimestamp
    private Date transactionDate;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(int fineAmount) {
        this.fineAmount = fineAmount;
    }

    public boolean isIssueOperation() {
        return isIssueOperation;
    }

    public void setIssueOperation(boolean issueOperation) {
        isIssueOperation = issueOperation;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import org.hibernate.annotations.CreationTimestamp;
//
//import javax.persistence.*;
//import java.util.Date;
//import java.util.UUID;
//
//@Entity
//public class Transaction {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    private String transactionId = UUID.randomUUID().toString(); // externalId
//
//    @ManyToOne
//    @JoinColumn
//    @JsonIgnoreProperties("books")
//    private Card card;
//
//    public void setCard(Card card) {
//        this.card = card;
//    }
//
//    public void setBook(Book book) {
//        this.book = book;
//    }
//
//    public void setFineAmount(int fineAmount) {
//        this.fineAmount = fineAmount;
//    }
//
//    public void setIssueOperation(boolean issueOperation) {
//        isIssueOperation = issueOperation;
//    }
//
//    public void setTransactionStatus(TransactionStatus transactionStatus) {
//        this.transactionStatus = transactionStatus;
//    }
//
//    public Card getCard() {
//        return card;
//    }
//
//    public Book getBook() {
//        return book;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public int getFineAmount() {
//        return fineAmount;
//    }
//
//    public boolean isIssueOperation() {
//        return isIssueOperation;
//    }
//
//    public TransactionStatus getTransactionStatus() {
//        return transactionStatus;
//    }
//
//    @ManyToOne
//    @JoinColumn
//    @JsonIgnoreProperties("transactions")
//    private Book book;
//
//    private int fineAmount;
//
//    @Column(columnDefinition = "TINYINT(1)")
//    private boolean isIssueOperation;
//
//    @Enumerated(value = EnumType.STRING)
//    private TransactionStatus transactionStatus;
//
//    @CreationTimestamp
//    private Date transactionDate;
//
//    public Date getTransactionDate() {
//        return transactionDate;
//    }
//
//    public void setTransactionDate(Date transactionDate) {
//        this.transactionDate = transactionDate;
//    }
//}

