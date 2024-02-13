package com.ohgiraffers.section01.autowired.subsection02.constructor;

import com.ohgiraffers.section01.common.BookDAO;
import com.ohgiraffers.section01.common.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookServiceConstructor")
public class BookService {

    /* 설명.
     *  BookDAO 타입의 비어있는 객체에 생성자를 통해 주입받는다.
     *  (@Autowired 를 생성자에 작성하고 주입받을 타입의 bean 을 매개변수로 작성한다.)(기본생성자 X)
     *
     * 설명.
     *  생성자 주입의 이점
     *  1. 필드에 final 키워드를 사용할 수 있다.
     *  2. 순환참조를 스프링 시작(컨테이너 생성 시)과 동시에 확인하고 에러를 발생시켜준다.
     *  3. 중간에 값이 오염되지 않음을 확신을 가지고 쓸 수 있다.(불변성)
     *  4. 테스트 코드 작성 시 테스트 코드에 @Autowired 또는 주입받는 대상의 setter 등을 추가하지 않고 순수 자바 코드로 테스트 가능하다.
    * */

    /* 필기.
     *  필드 주입의 경우 final 을 붙일 수 없다.
     *  생성자 주입의 경우 circular reference 가 생길 때 에러를 띄워준다. (필드 주입의 경우 에러 못띄워줌)
    * */
    private final BookDAO bookDAO;        // ComponentScan 범위 안에 BookDAO 타입의 bean 이 대입됨

    @Autowired
    public BookService(BookDAO bookDAO) {        // ComponentScan 범위 안에 BookDAO 타입의 bean 이 대입됨
        this.bookDAO = bookDAO;
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
