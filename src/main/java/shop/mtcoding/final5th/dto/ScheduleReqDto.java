package shop.mtcoding.final5th.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.final5th.domain.schedule.Schedule;

public class ScheduleReqDto {

    @Setter
    @Getter
    public static class ScheduleSaveReqDto {
        private Long userId;
        private String scheduleTitle;
        private Timestamp scheduleCreatedAt;
        private Timestamp scheduleStartAt;
        private Timestamp scheduleFinishAt;
        private String scheduleLocation;
        private String scheduleContent;
        private String scheduleNote;
        private String field;

        public Schedule toEntity() {
            return Schedule.builder()
                    .scheduleId(userId)
                    .scheduleTitle(scheduleTitle)
                    .scheduleCreatedAt(scheduleCreatedAt)
                    .scheduleStartAt(scheduleStartAt)
                    .scheduleFinishAt(scheduleFinishAt)
                    .scheduleLocation(scheduleLocation)
                    .scheduleContent(scheduleContent)
                    .scheduleNote(scheduleNote)
                    .field(field)
                    .build();
        }
    }

    @Setter
    @Getter
    public static class ScheduleUpdateReqDto {
        private String scheduleTitle;
        private Timestamp scheduleCreatedAt;
        private Timestamp scheduleStartAt;
        private Timestamp scheduleFinishAt;
        private String scheduleLocation;
        private String scheduleContent;
        private String scheduleNote;
        private String field;

        public Schedule toEntity() {
            Schedule schedule = Schedule.builder()
                    .scheduleTitle(scheduleTitle)
                    .scheduleCreatedAt(scheduleCreatedAt)
                    .scheduleStartAt(scheduleStartAt)
                    .scheduleFinishAt(scheduleFinishAt)
                    .scheduleLocation(scheduleLocation)
                    .scheduleContent(scheduleContent)
                    .scheduleNote(scheduleNote)
                    .field(field)
                    .build();
            return schedule;
        }
    }
}
