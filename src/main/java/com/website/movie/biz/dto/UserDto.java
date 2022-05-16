package com.website.movie.biz.dto;

import com.website.movie.biz.model.input.UserInputModel;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto {

    private int id;
    private String email;
    private String name;
    private String nickname;
    private String password;
    private String userType;
    private String certified;

    public static UserDto toDto(UserInputModel model) {

        return UserDto.builder()
                .id(model.getId())
                .email(model.getEmail())
                .name(model.getName())
                .nickname(model.getNickname())
                .password(model.getPassword())
                .password(model.getCertified())
                .build();
    }

}
