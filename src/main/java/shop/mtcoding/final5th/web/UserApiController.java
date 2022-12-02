package shop.mtcoding.final5th.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.final5th.service.UserService;

@RequiredArgsConstructor
@RequestMapping("/s/api")
@RestController
public class UserApiController {

    private final UserService userService;
}
