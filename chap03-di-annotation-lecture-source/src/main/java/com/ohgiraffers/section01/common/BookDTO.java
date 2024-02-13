package com.ohgiraffers.section01.common;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class BookDTO {
    private int sequence;
    private int isbn;
    private String title;
    private String author;
    private String publisher;
    private Date createdDate;
}
