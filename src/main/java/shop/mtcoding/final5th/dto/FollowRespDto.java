package shop.mtcoding.final5th.dto;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.final5th.domain.follow.Follow;
import shop.mtcoding.final5th.domain.user.User;

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

            public FollowDto(Follow follow) {
                this.followId = follow.getFollowId();
                this.userId = follow.getUserId();
                this.followingUserId = follow.getFollowingUserId();
                this.createdAt = follow.getCreatedAt();
            }
        }
    }
}
