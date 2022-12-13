package shop.mtcoding.final5th.dto;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.final5th.domain.user.User;
import shop.mtcoding.final5th.dto.UserRespDto.UserListRespDto.UserDto;

public class UserRespDto {

    @Setter
    @Getter
    public static class JoinRespDto {
        private Long userId;
        private String userName;
        private String userEmail;
        private String userPhonenumber;
        private String userRealname;

        public JoinRespDto(User user) {
            this.userId = user.getUserId();
            this.userName = user.getUserName();
            this.userEmail = user.getUserEmail();
            this.userPhonenumber = user.getUserPhonenumber();
            this.userRealname = user.getUserRealname();
        }
    }

    @Setter
    @Getter
    public static class PasswordUpdateRespDto {
        private String userName;

        public PasswordUpdateRespDto(User user) {
            this.userName = user.getUserName();
        }
    }

    @Setter
    @Getter
    public static class UserRealnameRespDto {
        private String userRealname;

        public UserRealnameRespDto(User user) {
            this.userRealname = user.getUserRealname();
        }
    }

    @Setter
    @Getter
    public static class UserListRespDto {
        private List<UserDto> userDtos;

        public UserListRespDto(List<User> userList) {
            this.userDtos = userList.stream().map((user) -> new UserDto(user))
                    .collect(Collectors.toList());
        }

        @Setter
        @Getter
        public class UserDto {
            private Long userId;
            private String userName;
            private String userEmail;
            private String userPhonenumber;
            private String userRealname;
            private String userImgfile;
            private String userProfileIntro;
            private String userWebLink;
            private String userImageUrl;
            private String userImageType;
            private String userImageName;
            private String userImageUuid;
            private Timestamp userCreatedAt;
            private Timestamp userUpdatedAt;

            public UserDto(User user) {
                this.userId = user.getUserId();
                this.userName = user.getUserName();
                this.userEmail = user.getUserEmail();
                this.userPhonenumber = user.getUserPhonenumber();
                this.userRealname = user.getUserRealname();
                this.userImgfile = user.getUserImgfile();
                this.userProfileIntro = user.getUserProfileIntro();
                this.userWebLink = user.getUserWebLink();
                this.userImageUrl = user.getUserImageUrl();
                this.userImageType = user.getUserImageType();
                this.userImageName = user.getUserImageName();
                this.userImageUuid = user.getUserImageUuid();
                this.userCreatedAt = user.getUserCreatedAt();
                this.userUpdatedAt = user.getUserUpdatedAt();
            }
        }
    }
}
