package shop.mtcoding.final5th.dto;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.final5th.domain.category.Category;

public class CategoryReqDto {

    @Setter
    @Getter
    public static class CategorySaveReqDto {
        private String categoryColor;
        private String categoryName;
        private Long userId;

        public Category toEntity() {
            Category category = Category.builder()
                    .categoryColor(categoryColor)
                    .categoryName(categoryName)
                    .userId(userId)
                    .build();
            return category;
        }
    }

    @Setter
    @Getter
    public static class CategoryUpdateReqDto {
        private String categoryColor;
        private String categoryName;
        private Long userId;

        public Category toEntity() {
            Category category = Category.builder()
                    .categoryColor(categoryColor)
                    .categoryName(categoryName)
                    .userId(userId)
                    .build();
            return category;
        }
    }
}
