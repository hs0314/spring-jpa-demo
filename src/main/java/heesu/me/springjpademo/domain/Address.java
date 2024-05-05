package heesu.me.springjpademo.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Embeddable // JPA 내장타입
@Getter
@AllArgsConstructor
public class Address {

    private String city;
    private String street;
    private String zipcode;

    // JPA 스펙상 엔티티나 임베디드 타입은 자바 기본생성자를 public/protected 로 설정해야함
    // JPA 구현 라이브러리가 객체 생성 시 리플렉션 등의 기술 사용할 수 있도록 지원해야함
    protected Address(){

    }
}
