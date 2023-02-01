package com.driver.services;

import com.driver.models.Author;
import com.driver.models.Book;
import com.driver.repositories.AuthorRepository;
import com.driver.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    public void createBook(Book book){
        int authorId = book.getAuthor().getId();
        Author author = authorRepository.findById(authorId).get();
        List<Book> bookList = author.getBooksWritten();
        bookList.add(book);
        author.setBooksWritten(bookList);

        book.setAuthor(author);
        authorRepository.save(author);
    }

    public List<Book> getBooks(String genre, boolean available, String author){
        List<Book> books;
        if(genre != null && author != null){
            books = bookRepository.findBooksByGenreAuthor(genre, author, available);
        }else if(genre != null){
            books = bookRepository.findBooksByGenre(genre, available);
        }else if(author != null){
            books = bookRepository.findBooksByAuthor(author, available);
        }else{
            books = bookRepository.findByAvailability(available);
        }
        return books;
    }
}
