package heesu.me.springjpademo.service;

import heesu.me.springjpademo.domain.Member;
import heesu.me.springjpademo.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    //@Rollback(false) // @Transactional이 붙어서 스프링 테스트에서 DB commit이전에 롤백시키는것 방지
    public void 회원가입() throws Exception {

        Member member = new Member();
        member.setName("kim");

        Long savedId = memberService.join(member);

        // DB에 쿼리 전송
        em.flush();

        //JPA 같은 트랜잭션내 엔티티의 id값이 같으면 동일 영속성 컨텍스트에서 동일 객체로 관리
        assertEquals(member, memberRepository.findOne(savedId));

    }

    @Test
    public void 중복_회원_예외() throws Exception {
        Member member1 = new Member();
        Member member2 = new Member();

        member1.setName("kim");
        member2.setName("kim");

        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> {
            // 예외가 발생해야 하는 코드
            memberService.join(member2); //duplicated
        });
    }

}