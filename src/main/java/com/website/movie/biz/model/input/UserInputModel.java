package com.website.movie.biz.model.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInputModel extends InputBaseDto {

    private int id;
    private String email;
    private String name;
    private String nickname;
    private String password;
    private String certified;

}
