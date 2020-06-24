package main.java.tdd.chap10.user;

import javafx.util.Builder;
import main.java.tdd.chap04.PayData;

import java.time.LocalDateTime;

public class User {
    private String id;
    private String password;
    private String name;
    private String email;
    private LocalDateTime regData;

    public User() {
    }

    public User(RegisterReq reqz) {
        this.id = reqz.getId();
        this.password = reqz.getPassword();
        this.email = reqz.getEmail();
    }

    public User(String id, String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private User data = new User();

        public static Builder id(String id){
            data.id = id;
            return this;
        }

        public Builder name(String name) {
            data.name = name;
            return this;
        }

        public Builder email(String email) {
            data.email = email;
            return this;
        }
        public Builder password(String password) {
            data.password = password;
            return this;
        }
        public Builder regData(LocalDateTime regData) {
            data.regData = regData;
            return this;
        }

        public User build() {
            return data;
        }


    }
}
