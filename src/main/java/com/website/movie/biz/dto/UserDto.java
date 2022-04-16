package com.website.movie.biz.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto {

    private int id;
    private String email;
    private String name;
    private String password;
    private String userType;
}
