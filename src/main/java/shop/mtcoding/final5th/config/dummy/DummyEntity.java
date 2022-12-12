package shop.mtcoding.final5th.config.dummy;

import shop.mtcoding.final5th.domain.user.User;

public abstract class DummyEntity {

    protected void newUser() {
        User user = User.builder()
                .userName(userName)
                .userEmail(userName + "@nate.com")
                .userPhonenumber(userPhonenumber)
                .userPassword("1234")
                .userRealname("그린")
                .build();
        return user;
    }
}
