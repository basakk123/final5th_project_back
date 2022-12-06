package shop.mtcoding.final5th.dto;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.final5th.domain.category.Category;

public class CategoryRespDto {

    @Setter
    @Getter
    public static class CategorySaveRespDto {
        private Long scheduleId;
        private String categoryName;
        private Long userId;
        private String categoryColor;

        public CategorySaveRespDto(Category category) {
            this.scheduleId = category.getScheduleId();
            this.categoryName = category.getCategoryName();
            this.userId = category.getUserId();
            this.categoryColor = category.getCategoryColor();
        }
    }

    @Setter
    @Getter
    public static class CategoryUpdateRespDto {
        private String categoryName;
        private String categoryColor;

        public CategoryUpdateRespDto(Category category) {
            this.categoryName = category.getCategoryName();
            this.categoryColor = category.getCategoryColor();
        }
    }
}
