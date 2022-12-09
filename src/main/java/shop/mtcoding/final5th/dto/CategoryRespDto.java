package shop.mtcoding.final5th.dto;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.final5th.domain.category.Category;

public class CategoryRespDto {

    @Setter
    @Getter
    public static class CategorySaveRespDto {
        private Long categoryId;
        private String categoryColor;
        private String categoryName;
        private Long userId;

        public CategorySaveRespDto(Category category) {
            this.categoryId = category.getCategoryId();
            this.categoryColor = category.getCategoryColor();
            this.categoryName = category.getCategoryName();
            this.userId = category.getUserId();

        }
    }

    @Setter
    @Getter
    public static class CategoryListRespDto {
        private List<CategoryDto> categoryDtos;

        public CategoryListRespDto(List<Category> categoryList) {
            this.categoryDtos = categoryList.stream().map((category) -> new CategoryDto(category))
                    .collect(Collectors.toList());
        }

        @Setter
        @Getter
        public class CategoryDto {
            private Long categoryId;
            private String categoryColor;
            private String categoryName;
            private Long userId;

            public CategoryDto(Category category) {
                this.categoryId = category.getCategoryId();
                this.categoryColor = category.getCategoryColor();
                this.categoryName = category.getCategoryName();
                this.userId = category.getUserId();
            }
        }
    }

    @Setter
    @Getter
    public static class CategoryDetailRespDto {
        private Long categoryId;
        private String categoryColor;
        private String categoryName;
        private Long userId;

        public CategoryDetailRespDto(Category category) {
            this.categoryId = category.getCategoryId();
            this.categoryColor = category.getCategoryColor();
            this.categoryName = category.getCategoryName();
            this.userId = category.getUserId();
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
