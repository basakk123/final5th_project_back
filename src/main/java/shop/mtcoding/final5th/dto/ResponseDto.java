package shop.mtcoding.final5th.dto;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// http code = 200(get, delete, put), 201(post)
@RequiredArgsConstructor
@Getter
public class ResponseDto<T> {
    private final HttpStatus httpStatus;
    private final String msg;
    private final T data;
}
