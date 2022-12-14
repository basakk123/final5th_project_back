package shop.mtcoding.final5th.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.final5th.dto.CommentRespDto.CommentListRespDto;
import shop.mtcoding.final5th.dto.JoinedChatRespDto.JoinedChatListRespDto;
import shop.mtcoding.final5th.dto.NewsRespDto.NewsListRespDto;
import shop.mtcoding.final5th.dto.ScheduleRespDto.ScheduleListRespDto;
import shop.mtcoding.final5th.dto.TodoRespDto.TodoListRespDto;
import shop.mtcoding.final5th.dto.UserRespDto.UserRealnameRespDto;

@Setter
@Getter
public class AllRespDto {
    private UserRealnameRespDto userRealnameRespDto;
    private ScheduleListRespDto scheduleListRespDto;
    private TodoListRespDto todoListRespDto;
    private List<FollowListRespDto> followListRespDtos;
    private List<FollowerListRespDto> followerListRespDto;
    private JoinedChatListRespDto joinedChatListRespDto;
    private CommentListRespDto commentListRespDto;
    private NewsListRespDto newsListRespDto;

    public AllRespDto(UserRealnameRespDto userRealnameRespDto, ScheduleListRespDto scheduleListRespDto,
            TodoListRespDto todoListRespDto, List<FollowListRespDto> followListRespDtos,
            List<FollowerListRespDto> followerListRespDto, JoinedChatListRespDto joinedChatListRespDto,
            CommentListRespDto commentListRespDto, NewsListRespDto newsListRespDto) {
        this.userRealnameRespDto = userRealnameRespDto;
        this.scheduleListRespDto = scheduleListRespDto;
        this.todoListRespDto = todoListRespDto;
        this.followListRespDtos = followListRespDtos;
        this.followerListRespDto = followerListRespDto;
        this.joinedChatListRespDto = joinedChatListRespDto;
        this.commentListRespDto = commentListRespDto;
        this.newsListRespDto = newsListRespDto;
    }
}
