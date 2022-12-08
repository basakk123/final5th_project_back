package shop.mtcoding.final5th.domain.category;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "category")
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(nullable = false, length = 50)
    private String categoryColor;

    @Column(nullable = false, length = 50)
    private String categoryName;

    @Column(nullable = false)
    private Long userId;

    @Builder
    public Category(Long categoryId, String categoryColor, String categoryName, Long userId) {
        this.categoryId = categoryId;
        this.categoryColor = categoryColor;
        this.categoryName = categoryName;
        this.userId = userId;
    }
}
