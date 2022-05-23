package com.website.movie.biz.service.impl;

import com.website.movie.biz.dao.AuthorityDao;
import com.website.movie.biz.dao.UserDao;
import com.website.movie.biz.dto.UserDto;
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

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDao userDao;
    private final AuthorityDao authorityDao;
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
    public UserDto get(UserDto user) {
        UserDto result = userDao.selectOne(user);

        return result;
    }

    //유저
    @Override
    public void createUser(UserDto user) {
        System.out.println("\nUser 유저 변환전  \n"+user);
        String rawPassword = user.getPassword();
        String encodedPassword = new BCryptPasswordEncoder().encode(rawPassword);
        user.setPassword(encodedPassword);
        userDao.insert(user);
        authorityDao.insertAuthority(user);
    }
    // Security 기본 메서드 나중에 로그인할때 입력 받는 값을 인자로 받아서 로그인했을때 과정을 처리해야함
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("\n loadUserByUsername실행됨 \n");
        UserDto user = userDao.selectByEmail(email);
        System.out.println("\n setAuthorities하기전 \n" + user);
        if(user==null) {
            System.out.println("\n user==null \n");
            throw new UsernameNotFoundException(email);
        }
        user.setAuthorities(getAuthorities(email));
        System.out.println("\n user \n" + user);
        return user;
    }

    public Collection<GrantedAuthority> getAuthorities(String email) {
        System.out.println("\n getAuthorities로 넘어온 email 값 : " + email);
        List<String> string_authorities = authorityDao.selectByEmail(email);
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

}
