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
            // 인코딩 되어 있다면 false반환
            boolean isEncode = new BCryptPasswordEncoder().upgradeEncoding(parameter.getPassword());
            System.out.println("isEncode == "+isEncode);
            if (parameter.getPassword() != null && isEncode)
            {
                String rawPassword = parameter.getPassword();
                String encodedPassword = new BCryptPasswordEncoder().encode(rawPassword);
                parameter.setPassword(encodedPassword);
            }
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
    // Security 기본 메서드 로그인 과정을 처리해줌
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDto user = userDao.selectByEmail(email);
        if(user==null) {
            throw new UsernameNotFoundException(email);
        }
        user.setAuthorities(getAuthorities(email));
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

    @Override
    public int emailCheck(UserDto user) {
        int result = userDao.emailCheck(user);
        return result;
    }

    @Override
    public int nicknameCheck(UserDto user) {
        int result = userDao.nicknameCheck(user);
        return result;
    }

    @Override
    public int updatePassword(UserDto user) {
        
        // 클라이언트에게 받은 user정보에서 입력받은 비밀번호(raw상태)를 rawPassword에 저장
        String rawPassword = user.getPassword();
        // 함수로 암호화하여 변수에 저장
        String encodedPassword = new BCryptPasswordEncoder().encode(rawPassword);
        // 암호화된 비번을 저장
        user.setPassword(encodedPassword);
        int result = userDao.updatePassword(user);
        System.out.println("updatePassword result == "+result);

        return result;
    }

    @Override
    public int updateUser(UserDto user) {
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        int result = userDao.updateByEmail(user);

        System.out.println("updateUser result"+result);
        return result;
    }


}
