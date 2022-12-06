package shop.mtcoding.final5th.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.final5th.config.exception.CustomApiException;
import shop.mtcoding.final5th.domain.category.Category;
import shop.mtcoding.final5th.domain.category.CategoryRepository;
import shop.mtcoding.final5th.domain.user.User;
import shop.mtcoding.final5th.domain.user.UserRepository;
import shop.mtcoding.final5th.dto.CategoryReqDto.CategorySaveReqDto;
import shop.mtcoding.final5th.dto.CategoryReqDto.CategoryUpdateReqDto;
import shop.mtcoding.final5th.dto.CategoryRespDto.CategorySaveRespDto;
import shop.mtcoding.final5th.dto.CategoryRespDto.CategoryUpdateRespDto;

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

        @Transactional
        public CategoryUpdateRespDto updateCategory(Long userId, Long categoryId,
                        CategoryUpdateReqDto categoryUpdateReqDto) {
                User userPS = userRepository.findById(userId)
                                .orElseThrow(() -> new CustomApiException("해당 유저가 없습니다", HttpStatus.BAD_REQUEST));
                Category CategoryPS = categoryRepository.findById(categoryId)
                                .orElseThrow(() -> new CustomApiException("해당 카테고리가 없습니다", HttpStatus.BAD_REQUEST));
                Category category = categoryUpdateReqDto.toEntity();
                CategoryPS = categoryRepository.save(category);
                return new CategoryUpdateRespDto(CategoryPS);
        }

        @Transactional
        public void deleteByCategoryId(Long userId, Long categoryId) {
                User userPS = userRepository.findById(userId)
                                .orElseThrow(() -> new CustomApiException("해당 유저가 없습니다", HttpStatus.BAD_REQUEST));
                Category CategoryPS = categoryRepository.findById(categoryId)
                                .orElseThrow(() -> new CustomApiException("해당 카테고리 없습니다", HttpStatus.BAD_REQUEST));
                categoryRepository.deleteById(categoryId);
        }
}
