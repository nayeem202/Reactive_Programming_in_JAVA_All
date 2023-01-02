package com.example.reactiveprogrammingallpractice.services;

import com.example.reactiveprogrammingallpractice.domain.Book;
import com.example.reactiveprogrammingallpractice.domain.BookInfo;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
@RequiredArgsConstructor
public class BookInfoService {
    public Flux<BookInfo> getBooks(){
       var books = List.of(
               new BookInfo(1, "Book One", "Author one", "256536"),
               new BookInfo(2, "Book Two", "Author Two", "4364536"),
               new BookInfo(1, "Book Three", "Author Three", "434322"));
       return Flux.fromIterable(books);
    }


    public Mono<BookInfo> getBookById(long bookId){
        var book =  new BookInfo(bookId, "Book One", "Author One", "647364");
        return Mono.just(book);
    }


}
