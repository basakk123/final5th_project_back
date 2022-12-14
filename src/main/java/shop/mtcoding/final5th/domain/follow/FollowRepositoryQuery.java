package shop.mtcoding.final5th.domain.follow;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import shop.mtcoding.final5th.dto.FollowListRespDto;
import shop.mtcoding.final5th.dto.FollowerListRespDto;

@Repository
public class FollowRepositoryQuery {

    @Autowired
    private EntityManager em;

    public List<FollowListRespDto> findFollowListByFollowingUserId(Long followingUserId) {
        StringBuffer sb = new StringBuffer();
        sb.append(
                "select fo.follow_id, fo.user_id, fo.following_user_id, fo.created_at, us.user_name, us.user_realname, us.user_imgfile, us.user_profile_intro from follow fo inner join users us ON fo.user_id = us.user_id where following_user_id = :followingUserId");

        Query query = em.createNativeQuery(sb.toString())
                .setParameter("followingUserId", followingUserId);

        JpaResultMapper result = new JpaResultMapper();
        List<FollowListRespDto> followListRespDtos = result.list(query, FollowListRespDto.class);
        return followListRespDtos;
    }

    public List<FollowerListRespDto> findFollowerListByUserId(Long userId) {
        StringBuffer sb = new StringBuffer();
        sb.append(
                "select fo.follow_id, fo.user_id, fo.following_user_id, fo.created_at, us.user_name, us.user_realname, us.user_imgfile, us.user_profile_intro from follow fo inner join users us ON fo.following_user_id = us.user_id where fo.user_id = :userId");

        Query query = em.createNativeQuery(sb.toString())
                .setParameter("userId", userId);

        JpaResultMapper result = new JpaResultMapper();
        List<FollowerListRespDto> followerListRespDtos = result.list(query, FollowerListRespDto.class);
        return followerListRespDtos;
    }
}
