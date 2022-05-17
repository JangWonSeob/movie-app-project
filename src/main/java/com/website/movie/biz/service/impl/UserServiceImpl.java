package com.website.movie.biz.service.impl;

import com.website.movie.biz.dao.UserDao;
import com.website.movie.biz.dto.BoardDto;
import com.website.movie.biz.dto.UserDto;
import com.website.movie.biz.model.input.BoardInputModel;
import com.website.movie.biz.model.input.UserInputModel;
import com.website.movie.biz.model.search.BoardSearchModel;
import com.website.movie.biz.model.search.CommentSearchModel;
import com.website.movie.biz.model.search.UserSearchModel;
import com.website.movie.biz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDao userDao;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public boolean set(UserDto parameter) {
        int affected;

        if(parameter.getId() > 0){
            affected = userDao.update(parameter);
        }  else {
            affected = userDao.insert(parameter);
        }

        if (affected < 1) {
            return false;
        }
        return true;
    }

    @Override
    public UserDto get(UserDto model) {
        UserDto result = userDao.selectOne(model);

        return result;
    }

    @Override
    public List<UserDto> gets(UserDto model) {
        return userDao.selectList(model);
    }

    @Override
    public void insert(UserDto user) {
        String rawPassword = user.getPassword();
        String encodedPassword = new BCryptPasswordEncoder().encode(rawPassword);
        user.setPassword(encodedPassword);
        userDao.insert(user);
        userDao.insertUsertype(user);
    }

    // Security 기본 메서드 나중에 로그인할때 입력 받는 값을 인자로 받아서 로그인했을때 과정을 처리해야함
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        int id;
//        UserDto user = userDao.selectUser(email);
//        if(user==null) {
//            throw new UsernameNotFoundException(email);
//        }
//        user.setAuthorities(getUsertype(email));
//        return user;
//    }

    @Override
    public Collection<GrantedAuthority> getUsertype(int id) {
        List<String> string_authorities = userDao.selectUsertype(id);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (String authority : string_authorities) {
            authorities.add(new SimpleGrantedAuthority(authority));
        }
        return authorities;
    }



    @Override
    public boolean delete(UserDto parameter) {

        if(parameter.getId() < 1) {
            return false;
        }

        int affected = userDao.delete(parameter);

        if (affected < 1) {
            return false;
        }

        return true;
    }
    @Override
    public PasswordEncoder passwordEncoder() {
        return this.passwordEncoder;
    }


    @Override
    public UserDto email_certified_check(UserDto user) {
        return userDao.email_certified_check(user);
    }

    @Override
    public void email_certified_update(UserDto user) {
        userDao.email_certified_update(user);
    }

    //스프링 Security 기본제공 메소드
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
