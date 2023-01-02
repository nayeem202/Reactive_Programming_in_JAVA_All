package com.example.reactiveprogrammingallpractice.domain;


import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookInfo {
    private long bookId;
    private String title;
    private String author;
    private String ISBN;


}
