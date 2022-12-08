package shop.mtcoding.final5th.config.dummy;

import shop.mtcoding.final5th.domain.user.User;

public abstract class DummyEntity {

    protected User newUser(String userName) {
        User user = User.builder()
                .userName(userName)
                .userEmail(userName + "@nate.com")
                .userPhonenumber("01012345678")
                .userPassword("1234")
                .userRealname("그린")
                .build();
        return user;
    }
}
