package shop.mtcoding.final5th.dto;

import java.math.BigInteger;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FollowerListRespDto {
    private BigInteger followId;
    private BigInteger userId;
    private BigInteger followingUserId;
    private Timestamp createdAt;
    private String userName;
    private String userRealname;
    private String userImgfile;
    private String userProfileIntro;
}
