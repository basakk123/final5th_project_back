package shop.mtcoding.final5th.domain.follow;

import java.util.List;

import javax.persistence.EntityManager;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import shop.mtcoding.final5th.config.dummy.DummyEntity;
import shop.mtcoding.final5th.domain.user.User;
import shop.mtcoding.final5th.domain.user.UserRepository;
import shop.mtcoding.final5th.dto.FollowListRespDto;

@Import(FollowRepositoryQuery.class)
@ActiveProfiles("test")
@DataJpaTest
public class FollowRepositoryQueryTest extends DummyEntity {

    @Autowired
    private EntityManager em;

    @Autowired
    private FollowRepositoryQuery followRepositoryQuery;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRepository followRepository;

    @BeforeEach
    public void setUp() {
        User green = userRepository.save(newUser("green", "01012345678", "그린"));
        User orange = userRepository.save(newUser("orange", "01012341234", "오렌지"));
        User yellow = userRepository.save(newUser("yellow", "01012345578", "노랑"));
        Follow greenFollow1 = followRepository.save(newFollow(1L, 2L));
        Follow greenFollow2 = followRepository.save(newFollow(1L, 3L));
        this.em
                .createNativeQuery("ALTER TABLE users ALTER COLUMN `user_id` RESTART WITH 1")
                .executeUpdate();
    }

    @Test
    public void findFollowListByFollowingUserId_test() {
        // given
        Long followingUserId = 1L;

        // when
        List<FollowListRespDto> followListRespDtos = followRepositoryQuery.findFollowListByFollowingUserId(1L);
        System.out.println("테스트 :  followListRespDto.getFollowId() : " + followListRespDtos.get(0).getUserId());
        System.out.println("테스트 : followListRespDto.getUserName() : " + followListRespDtos.get(0).getUserName());
        System.out
                .println("테스트 : followListRespDto.getUserRealname() : " + followListRespDtos.get(0).getUserRealname());

        // then
        Assertions.assertThat(followListRespDtos.get(0));
    }
}
