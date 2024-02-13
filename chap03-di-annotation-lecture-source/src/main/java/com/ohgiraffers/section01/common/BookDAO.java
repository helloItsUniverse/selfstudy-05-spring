package com.ohgiraffers.section01.common;

import java.util.List;

/* 필기.
 *  PSA(Portable Service Abstraction)
 *  다형성 -> 타입은닉 -> 유지보수와 확장성 때문.
 *  서비스 단에서는 모르게 함.
* */
public interface BookDAO {

    List<BookDTO> findAllBook();

    BookDTO searchBookBySequence(int sequence);
}
