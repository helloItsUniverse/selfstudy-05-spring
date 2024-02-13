package com.ohgiraffers.section01.autowired.subsection01.field;

import com.ohgiraffers.section01.common.BookDAO;
import com.ohgiraffers.section01.common.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    /* 설명.
     *  아래의 세가지는 ComponentScan 범위 안에 어노테이션들이 달렸을 때 유효하다.
     *  1. @Service 에 의해서 BookService 타입의 bookService bean 이 관리된다.
     *  2. BookDAOImpl 에 있는 @Repository 에 의해서 bookDAOImpl bean 이 관리된다. (BookDAO 타입이기도 하다)
     *  3. @Autowired 에 의해서 BookDAO 타입의 bean 이 BookService 의 필드인 bookDAO 필드에 주입된다.(대입된다.)
     *
     * 설명.
     *  결국 어노테이션들이 달려있는 걸 spring 이 인지해야 함.
    * */

    /* 필기.
     *  필드 주입의 경우 final 을 붙일 수 없다.
    * */
    @Autowired
    private BookDAO bookDAO;        // ComponentScan 범위 안에 BookDAO 타입의 bean 이 대입됨

    public BookService() {
    }

    /* 설명. 도서 목록 전체 조회 */
    public List<BookDTO> findAllBook() {
        return bookDAO.findAllBook();
    }

    /* 설명. 도서 번호로 도서 조회 */
    public BookDTO searchBookBySequence(int sequence) {
        return bookDAO.searchBookBySequence(sequence);
    }
}
