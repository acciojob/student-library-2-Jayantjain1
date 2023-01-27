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
        Author author = new Author();
        List<Book> bookList = new ArrayList<>();
        bookList = author.getBooksWritten();
        bookList.add(book);
        author.setBooksWritten(bookList);
        book.setAuthor(author);
        authorRepository.save(author);
        bookRepository.save(book);
    }

    public List<Book> getBooks(String genre, boolean available, String author){
        List<Book> books = null; //find the elements of the list by yourself
        if(genre != null && author != null){
            return bookRepository.findBooksByGenreAuthor(genre, author, available);
        }else if(genre != null){
            return bookRepository.findBooksByGenre(genre, available);
        }else if(author != null){
            return bookRepository.findBooksByAuthor(author, available);
        }else{
            return bookRepository.findByAvailability(available);
        }
//     If genre=”X”, availability = true, and author=null; we require the list of all books which are available and have genre “X”.
//     Note that these books can be written by any author.
//     ii) If genre=”Y”, availability = false, and author=”A”; we require the list of all books which are written by author “A”, have genre “Y”, and are currently unavailable.
//        Book book = new Book();
//        Author author1 = new Author();
//        if(book.getGenre().equals(genre)) {
//            if (book.isAvailable() && author == null) {
//                List<Book> bookList = author1.getBooksWritten();
//                for (Book book1 : bookList) {
//                    if (book1.getGenre().equals(genre) && book1.isAvailable()) {
//                        books.add(book1);
//                    }
//                }
//                return books;
//            } else if (!book.isAvailable() && book.getAuthor().equals(author)) {
//                List<Book> bookList = author1.getBooksWritten();
//                for (Book book1 : bookList) {
//                    if (book1.getAuthor().equals(author) && !book1.isAvailable() && book1.getGenre().equals(genre)) {
//                        books.add(book1);
//                    }
//                }
//                return books;
//            }
//        }
//        return books;
    }
}
