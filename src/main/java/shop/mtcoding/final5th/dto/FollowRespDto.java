package shop.mtcoding.final5th.dto;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.final5th.domain.follow.Follow;

public class FollowRespDto {

    @Setter
    @Getter
    public static class FollowListRespDto {
        private List<FollowDto> followDtos;

        public FollowListRespDto(List<Follow> followList) {
            this.followDtos = followList.stream().map((follow) -> new FollowDto(follow))
                    .collect(Collectors.toList());
        }

        @Setter
        @Getter
        public class FollowDto {
            private Long followId;
            private Long userId;
            private Long followingUserId;
            private Timestamp createdAt;
            private String userName;
            private String userRealname;
            private String userImgfile;
            private String userProfileIntro;
            private String userWebLink;
            private String userImageUrl;
            private String userImageType;
            private String userImageName;
            private String userImageUuid;

            public FollowDto(Follow follow) {
                this.followId = follow.getFollowId();
                this.userId = follow.getUserId();
                this.followingUserId = follow.getFollowingUserId();
                this.createdAt = follow.getCreatedAt();
            }
        }
    }
}
