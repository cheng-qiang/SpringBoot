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
public class UserVo4 {
    /**实体类该属性名是id*/
    private String userId;
    /**实体类该属性名是name*/
    private String userName;
    private String createTime;
    private String updateTime;
}
