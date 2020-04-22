package com.chen.mapstruct.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author 言少钱
 * @date 2020年04月22日 10:12
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVo3 {
    private Integer id;
    private String name;
    /**实体类该属性是String*/
    private LocalDateTime createTime;
    /**实体类该属性是LocalDateTime*/
    private String updateTime;
}
