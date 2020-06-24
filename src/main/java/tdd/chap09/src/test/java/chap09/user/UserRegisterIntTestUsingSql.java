package chap09.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*
* @Sql을 이용하여 초기화 코드를 사용하지 않고, 동일한 data를 세팅하는 방식은 편리하다.
* BUT,
* 해당 sql 쿼리 파일을 조금만 변경해도 많은 테스트가 깨질 수 있고,
* 테스트가 깨지면 관련된 모든 쿼리 파일을 같이 봐야 한다는 단점이 있다.
*
* SO,
* 통합 테스트 코드에서는 초기화 데이터를 2가지로 나눠 생각해야 한다.
* 1. 모든 테스트가 공유하는 데이터 : 코드값 등.
* 2. 각각의 테스트 메서드 내에서만 필요한 데이터 : id 중복 테스트를 위한 기초data
* */
@SpringBootTest
@Sql("classpath:init-data.sql")// 해당 sql을 테스트 실행전 먼저 실행할 수 있게 함.
public class UserRegisterIntTestUsingSql {
    @Autowired
    private UserRegister register;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void 동일ID가_이미_존재하면_익셉션() {
        // 실행, 결과 확인
        assertThrows(DupIdException.class,
                () -> register.register("cbk", "strongpw", "email@email.com")
        );
    }

    @Test
    void 존재하지_않으면_저장함() {
        // 실행
        register.register("cbk2", "strongpw", "email@email.com");
        // 결과 확인
        SqlRowSet rs = jdbcTemplate.queryForRowSet("select * from user where id = ?", "cbk2");
        rs.next();
        assertEquals("email@email.com", rs.getString("email"));
    }
}
