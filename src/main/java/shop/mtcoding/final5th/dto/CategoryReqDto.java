package shop.mtcoding.final5th.dto;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.final5th.domain.category.Category;

public class CategoryReqDto {

    @Setter
    @Getter
    public static class CategorySaveReqDto {
        private Long scheduleId;
        private String categoryName;
        private Long userId;
        private String categoryColor;

        public Category toEntity() {
            Category category = Category.builder()
                    .scheduleId(scheduleId)
                    .categoryName(categoryName)
                    .userId(userId)
                    .categoryColor(categoryColor)
                    .build();
            return category;
        }
    }

    @Setter
    @Getter
    public static class CategoryUpdateReqDto {
        private String categoryName;
        private String categoryColor;

        public Category toEntity() {
            Category category = Category.builder()
                    .categoryName(categoryName)
                    .categoryColor(categoryColor)
                    .build();
            return category;
        }
    }
}
