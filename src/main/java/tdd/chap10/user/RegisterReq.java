package main.java.tdd.chap10.user;

import java.time.LocalDateTime;

public class RegisterReq {
    private String id;
    private String password;
    private String name;
    private String email;
    private LocalDateTime regData;

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private RegisterReq data = new RegisterReq();

        public Builder id(String id){
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

        public RegisterReq build() {
            return data;
        }

    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getRegData() {
        return regData;
    }
}
