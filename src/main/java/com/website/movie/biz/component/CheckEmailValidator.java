package com.website.movie.biz.component;

import com.website.movie.biz.dao.UserDao;
import com.website.movie.biz.dto.UserDto;
import com.website.movie.biz.validator.AbstractValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

// doValidate를 구현해 검증로직을 작성하고 bean으로 등록될 수 있도록 @Component 어노테이션을 사용했다.
@Component
@RequiredArgsConstructor
public class CheckEmailValidator extends AbstractValidator<UserDto> {

    private final UserDao userDao;

    @Override
    protected void doValidate(UserDto user, Errors errors) {
        if (userDao.emailCheck(user) != 0) { //중복되는 이메일이 있다면
            errors.rejectValue("email", "이메일 중복 오류", "이미 사용중인 이메일입니다.");
        }
    }
}