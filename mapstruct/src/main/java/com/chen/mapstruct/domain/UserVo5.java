package com.chen.mapstruct.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 言少钱
 * @date 2020年04月22日 10:12
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVo5 {
    private Integer id;
    private String name;
    private String type;
}
