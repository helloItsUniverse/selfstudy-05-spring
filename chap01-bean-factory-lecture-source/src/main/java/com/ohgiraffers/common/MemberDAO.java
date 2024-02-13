package com.ohgiraffers.common;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


/* 설명.
 *  DAO 클래스는 Data Access Object 를 줄인 말로
 *  Repository 계층과 마찬가지로 java application 과 Database 를 연동시켜주기 위한 계층의 클래스로 활용된다.
 * */

@Repository     // 이건 @Component 에 좀 더 추가적인 기능이 있는 것
/* 설명. @Repository 는 DB와 연동하기 위해 사용되는 클래스에 추가하는 어노테이션으로 bean 으로 관리될 수 있게 어노테이션을 추가한다. */

/* 설명. 클래스에 계층의 의미가 없이 (추가적인 기능 없이) 단순히 bean 으로 관리될 클래스는 @Component 를 붙일 수 있다. */
//@Component

public class MemberDAO {

    private final Map<Integer, MemberDTO> memberMap;

    public MemberDAO() {
        memberMap = new HashMap<>();

        memberMap.put(1, new MemberDTO(1, "user01", "pass01", "홍길동"));
        memberMap.put(2, new MemberDTO(2, "user02", "pass02", "유관순"));
    }

    /* 설명. 회원 조회용 메소드 */
    public MemberDTO selectMember(int sequence) {
        return memberMap.get(sequence);
    }

    /* 설명. 회원 가입용 메소드 */
    public int insertMember(MemberDTO newMember) {
        int before = memberMap.size();

        memberMap.put(newMember.getSequence(), newMember);

        int after = memberMap.size();

        return after - before;
    }
}