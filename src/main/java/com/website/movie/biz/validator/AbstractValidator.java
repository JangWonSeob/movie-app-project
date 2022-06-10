package com.website.movie.biz.validator;

import groovy.util.logging.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
public abstract class AbstractValidator<T> implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    //컴파일러에서 경고하지 않도록 하기 위해 사용
    @SuppressWarnings("unchecked")
    @Override
    public void validate(Object target, Errors errors) {
        try {
            doValidate((T) target, errors);
        } catch (RuntimeException e) {
            System.out.println("validate 에러"+e+"\n ----");
            throw e;
        }
    }

    protected abstract void doValidate(final T dto, final Errors errors);
}
