package test.java.chap10;
import main.java.tdd.chap07.UserRegister;
import main.java.tdd.chap10.user.DuplIdException;
import main.java.tdd.chap10.user.UserGivenHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
/*
* 통합 테스트를 위한 상황 설정 클래스를 사용하기 : UserGivenHelper
* */
public class UseSubClass_Test {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private UserRegister register;

    private UserGivenHelper given;

    @BeforeEach
    void setUp(){
        given = new UserGivenHelper(jdbcTemplate);
    }

    @DisplayName("id 중복 검증")
    @Test
    void dupId(){
        given.givenUser("cbk","pw","cbk@cbk.com");

        //실행, 결과 확인
        assertThrows(DuplIdException.class, ()->{
            register.register("cbk","strongpw","email@email.com");
        });

    }

    @DisplayName("email 주소가 변경되었는지 검증")
    @Test
    void changeEmail(){
        SqlRowSet rs = jdbcTemplate.queryForRowSet("select * from user where id=?","id");
        rs.next();
        assertEquals("email@email.com",rs.getString("email"));
    }



}

