package main.java.tdd.chap10.user;

import org.springframework.jdbc.core.JdbcTemplate;

/*
* 상황 설정과 동일하게 결과 검증을 위한 보조클래스....
* 유지보수를 유리하게 한다.
* */
public class UserGivenHelper{
    private JdbcTemplate jdbcTemplate;

    public UserGivenHelper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void givenUser(String id, String pw, String email){
        jdbcTemplate.update(
                "insert into user values (?,?,?)" +
                        "on duplicate key update password=?, email=?",
                id,pw,email,pw,email
        );
    }


}
