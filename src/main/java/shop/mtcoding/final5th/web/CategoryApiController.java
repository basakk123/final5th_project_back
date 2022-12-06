package shop.mtcoding.final5th.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.final5th.config.auth.LoginUser;
import shop.mtcoding.final5th.config.exception.CustomApiException;
import shop.mtcoding.final5th.dto.CategoryReqDto.CategorySaveReqDto;
import shop.mtcoding.final5th.dto.CategoryReqDto.CategoryUpdateReqDto;
import shop.mtcoding.final5th.dto.CategoryRespDto.CategorySaveRespDto;
import shop.mtcoding.final5th.dto.CategoryRespDto.CategoryUpdateRespDto;
import shop.mtcoding.final5th.dto.ResponseDto;
import shop.mtcoding.final5th.service.CategoryService;
import shop.mtcoding.final5th.service.UserService;

@RequiredArgsConstructor
@RequestMapping("/s/api")
@RestController
public class CategoryApiController {

    private final UserService userService;
    private final CategoryService categoryService;
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final HttpSession session;

    @PostMapping("/category")
    public ResponseEntity<?> saveCategory(@RequestBody CategorySaveReqDto categorySaveReqDto) {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser == null) {
            throw new CustomApiException("로그인을 진행해주세요", HttpStatus.FORBIDDEN);
        }
        CategorySaveRespDto categorySaveRespDto = categoryService.saveCategory(categorySaveReqDto);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "카테고리 작성 성공", categorySaveRespDto),
                HttpStatus.OK);
    }

    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<?> updateCategory(@PathVariable Long userId, @PathVariable Long categoryId,
            @RequestBody CategoryUpdateReqDto categoryUpdateReqDto) {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser.getUserId() != userId) {
            throw new CustomApiException("권한이 없습니다", HttpStatus.FORBIDDEN);
        }
        CategoryUpdateRespDto categoryUpdateRespDto = categoryService.updateCategory(userId, categoryId,
                categoryUpdateReqDto);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "카테고리 수정 성공", categoryUpdateRespDto),
                HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<?> deleteByCategoryId(@PathVariable Long userId, @PathVariable Long categoryId) {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser.getUserId() != userId) {
            throw new CustomApiException("권한이 없습니다", HttpStatus.FORBIDDEN);
        }
        categoryService.deleteByCategoryId(userId, categoryId);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "카테고리 삭제 성공", null),
                HttpStatus.OK);
    }
}
