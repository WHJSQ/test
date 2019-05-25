package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Administrator on 2018/8/28.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @NotBlank(message = "姓名不为空")
    private  String userName;
    @Length(max = 10,message = "长度不超过10")
    private  String passWord;

    private  String id;
    private  String name;
}
