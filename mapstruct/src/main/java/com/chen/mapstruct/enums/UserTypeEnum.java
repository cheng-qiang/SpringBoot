package com.chen.mapstruct.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 言少钱
 * @date 2020年04月22日 10:08
 * @Description:
 */
@Getter
@AllArgsConstructor
public enum UserTypeEnum {
    Java("001", "Java开发工程师"),
    DB("002", "数据库管理员"),
    LINUX("003", "Linux运维员");
    private String value;
    private String title;
}
