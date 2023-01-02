package com.example.reactiveprogrammingallpractice.services;

import com.example.reactiveprogrammingallpractice.domain.Book;
import com.example.reactiveprogrammingallpractice.exception.BookException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookServiceMockTest {

    @Mock
    private BookInfoService bookInfoService;

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private BookService bookService;


    @Test
    void getBooksMock(){
        Mockito.when(bookInfoService.getBooks())
                .thenCallRealMethod();
        Mockito.when(reviewService.getReviews(Mockito.anyLong()))
                .thenCallRealMethod();
        var books = bookService.getBooks();
        StepVerifier.create(books)
                .expectNextCount(3)
                .verifyComplete();

    }

    @Test
    void getBooksMockOnError(){
        Mockito.when(bookInfoService.getBooks())
                .thenCallRealMethod();
        Mockito.when(reviewService.getReviews(Mockito.anyLong()))
                .thenThrow(new IllegalStateException("exception using test"));
        var books = bookService.getBooks();
        StepVerifier.create(books)
                .expectError(BookException.class)
                .verify();

    }



    @Test
    void getBooksMockOnErrorRetry(){
        Mockito.when(bookInfoService.getBooks())
                .thenCallRealMethod();
        Mockito.when(reviewService.getReviews(Mockito.anyLong()))
                .thenThrow(new IllegalStateException("exception using test"));
        var books = bookService.getBooksRetry();
        StepVerifier.create(books)
                .expectError(BookException.class)
                .verify();

    }

    @Test
    void getBooksMockOnErrorRetrywhen(){
        Mockito.when(bookInfoService.getBooks())
                .thenCallRealMethod();
        Mockito.when(reviewService.getReviews(Mockito.anyLong()))
                .thenThrow(new IllegalStateException("exception using test"));
        var books = bookService.getBooksRetryWhen();
        StepVerifier.create(books)
                .expectError(BookException.class)
                .verify();
    }

}