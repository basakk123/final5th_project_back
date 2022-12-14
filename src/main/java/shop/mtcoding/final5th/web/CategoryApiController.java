package shop.mtcoding.final5th.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.final5th.config.annotation.AuthorizationCheck;
import shop.mtcoding.final5th.config.auth.LoginUser;
import shop.mtcoding.final5th.config.exception.CustomApiException;
import shop.mtcoding.final5th.dto.CategoryReqDto.CategorySaveReqDto;
import shop.mtcoding.final5th.dto.CategoryReqDto.CategoryUpdateReqDto;
import shop.mtcoding.final5th.dto.CategoryRespDto.CategoryDetailRespDto;
import shop.mtcoding.final5th.dto.CategoryRespDto.CategoryListRespDto;
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

    @PostMapping("/user/{userId}/category")
    public ResponseEntity<?> saveCategory(@PathVariable Long userId,
            @RequestBody CategorySaveReqDto categorySaveReqDto) {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser.getUserId() == null) {
            throw new CustomApiException("???????????? ??????????????????", HttpStatus.FORBIDDEN);
        }
        categorySaveReqDto.setUserId(userId);
        CategorySaveRespDto categorySaveRespDto = categoryService.saveCategory(categorySaveReqDto);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.CREATED, "???????????? ?????? ??????", categorySaveRespDto),
                HttpStatus.CREATED);
    }

    @AuthorizationCheck
    @GetMapping("/user/{userId}/category")
    public ResponseEntity<?> findCategoryListByUserId(@PathVariable Long userId) {
        CategoryListRespDto categoryListRespDto = categoryService.findCategoryListByUserId(userId);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "???????????? ????????? ?????? ??????", categoryListRespDto),
                HttpStatus.OK);
    }

    @AuthorizationCheck
    @GetMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<?> findCategoryDetail(@PathVariable Long userId, @PathVariable Long categoryId) {
        CategoryDetailRespDto categoryDetailRespDto = categoryService.findCategoryDetail(userId, categoryId);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "???????????? ???????????? ??????", categoryDetailRespDto),
                HttpStatus.OK);
    }

    @AuthorizationCheck
    @PutMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<?> updateCategory(@PathVariable Long userId, @PathVariable Long categoryId,
            @RequestBody CategoryUpdateReqDto categoryUpdateReqDto) {
        CategoryUpdateRespDto categoryUpdateRespDto = categoryService.updateCategory(userId, categoryId,
                categoryUpdateReqDto);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.CREATED, "???????????? ?????? ??????", categoryUpdateRespDto),
                HttpStatus.CREATED);
    }

    @AuthorizationCheck
    @DeleteMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<?> deleteByCategoryId(@PathVariable Long userId, @PathVariable Long categoryId) {
        categoryService.deleteByCategoryId(userId, categoryId);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "???????????? ?????? ??????", null),
                HttpStatus.OK);
    }
}
