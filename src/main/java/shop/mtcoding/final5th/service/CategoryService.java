package shop.mtcoding.final5th.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.final5th.domain.category.Category;
import shop.mtcoding.final5th.domain.category.CategoryRepository;
import shop.mtcoding.final5th.domain.user.UserRepository;
import shop.mtcoding.final5th.dto.CategoryReqDto.CategorySaveReqDto;
import shop.mtcoding.final5th.dto.CategoryRespDto.CategorySaveRespDto;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CategoryService {

        private final UserRepository userRepository;
        private final CategoryRepository categoryRepository;
        private final Logger log = LoggerFactory.getLogger(getClass());

        @Transactional
        public CategorySaveRespDto saveCategory(CategorySaveReqDto categorySaveReqDto) {
                Category CategoryPS = categoryRepository.save(categorySaveReqDto.toEntity());
                return new CategorySaveRespDto(CategoryPS);
        }
}
