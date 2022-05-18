package com.website.movie.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTypeDto extends BaseDto{

    private int id;
    private String user_type;
    private String email;

}
