package shop.mtcoding.final5th.dto;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.final5th.domain.category.Category;
import shop.mtcoding.final5th.domain.schedule.Schedule;

public class ScheduleRespDto {

    @Setter
    @Getter
    public static class ScheduleListRespDto {
        private List<ScheduleDto> scheduleDtos;
        private List<CategoryDto> categoryDtos;

        public ScheduleListRespDto(List<Schedule> scheduleList, List<Category> categoryList) {
            this.scheduleDtos = scheduleList.stream().map((schedule) -> new ScheduleDto(schedule))
                    .collect(Collectors.toList());
            this.categoryDtos = categoryList.stream().map((category) -> new CategoryDto(category))
                    .collect(Collectors.toList());
        }

        @Setter
        @Getter
        public class ScheduleDto {
            private Long scheduleId;
            private Long userId;
            private String scheduleTitle;
            private Timestamp scheduleCreatedAt;
            private Timestamp scheduleStartAt;
            private Timestamp scheduleFinishAt;
            private String scheduleLocation;
            private String scheduleContent;
            private String scheduleNote;
            private String field;

            public ScheduleDto(Schedule schedule) {
                this.scheduleId = schedule.getScheduleId();
                this.userId = schedule.getUserId();
                this.scheduleTitle = schedule.getScheduleTitle();
                this.scheduleCreatedAt = schedule.getScheduleCreatedAt();
                this.scheduleStartAt = schedule.getScheduleStartAt();
                this.scheduleFinishAt = schedule.getScheduleFinishAt();
                this.scheduleLocation = schedule.getScheduleLocation();
                this.scheduleContent = schedule.getScheduleContent();
                this.scheduleNote = schedule.getScheduleNote();
                this.field = schedule.getField();
            }
        }

        @Setter
        @Getter
        public class CategoryDto {
            private Long categoryId;
            private Long scheduleId;
            private String categoryName;
            private Long userId;
            private String categoryColor;

            public CategoryDto(Category category) {
                this.categoryId = category.getCategoryId();
                this.scheduleId = category.getScheduleId();
                this.categoryName = category.getCategoryName();
                this.userId = category.getUserId();
                this.categoryColor = category.getCategoryColor();
            }
        }

        @Setter
        @Getter
        public static class ScheduleDetailRespDto {
            private Long scheduleId;
            private Long userId;
            private String scheduleTitle;
            private Timestamp scheduleCreatedAt;
            private Timestamp scheduleStartAt;
            private Timestamp scheduleFinishAt;
            private String scheduleLocation;
            private String scheduleContent;
            private String scheduleNote;
            private String field;

            public ScheduleDetailRespDto(Schedule schedule) {
                this.scheduleId = schedule.getScheduleId();
                this.userId = schedule.getUserId();
                this.scheduleTitle = schedule.getScheduleTitle();
                this.scheduleCreatedAt = schedule.getScheduleCreatedAt();
                this.scheduleStartAt = schedule.getScheduleStartAt();
                this.scheduleFinishAt = schedule.getScheduleFinishAt();
                this.scheduleLocation = schedule.getScheduleLocation();
                this.scheduleContent = schedule.getScheduleContent();
                this.scheduleNote = schedule.getScheduleNote();
                this.field = schedule.getField();
            }
        }
    }

    @Setter
    @Getter
    public static class ScheduleSaveRespDto {
        private Long scheduleId;
        private Long userId;
        private String scheduleTitle;
        private Timestamp scheduleCreatedAt;
        private Timestamp scheduleStartAt;
        private Timestamp scheduleFinishAt;
        private String scheduleLocation;
        private String scheduleContent;
        private String scheduleNote;
        private String field;

        public ScheduleSaveRespDto(Schedule schedule) {
            this.scheduleId = schedule.getScheduleId();
            this.userId = schedule.getUserId();
            this.scheduleTitle = schedule.getScheduleTitle();
            this.scheduleCreatedAt = schedule.getScheduleCreatedAt();
            this.scheduleStartAt = schedule.getScheduleStartAt();
            this.scheduleFinishAt = schedule.getScheduleFinishAt();
            this.scheduleLocation = schedule.getScheduleLocation();
            this.scheduleContent = schedule.getScheduleContent();
            this.scheduleNote = schedule.getScheduleNote();
            this.field = schedule.getField();
        }
    }

    @Setter
    @Getter
    public static class ScheduleUpdateRespDto {
        private String scheduleTitle;
        private Timestamp scheduleCreatedAt;
        private Timestamp scheduleStartAt;
        private Timestamp scheduleFinishAt;
        private String scheduleLocation;
        private String scheduleContent;
        private String scheduleNote;
        private String field;

        public ScheduleUpdateRespDto(Schedule schedule) {
            this.scheduleTitle = schedule.getScheduleTitle();
            this.scheduleCreatedAt = schedule.getScheduleCreatedAt();
            this.scheduleStartAt = schedule.getScheduleStartAt();
            this.scheduleFinishAt = schedule.getScheduleFinishAt();
            this.scheduleLocation = schedule.getScheduleLocation();
            this.scheduleContent = schedule.getScheduleContent();
            this.scheduleNote = schedule.getScheduleNote();
            this.field = schedule.getField();
        }
    }
}
