package com.example.pojo;

import lombok.*;
import org.springframework.context.annotation.Profile;

/**
 * Created by Administrator on 2018/8/27.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Profile("dev")
public class Code {
      String code;
      String name;
}
