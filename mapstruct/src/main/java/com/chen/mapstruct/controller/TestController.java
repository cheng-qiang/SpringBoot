package com.chen.mapstruct.controller;

import com.chen.mapstruct.covert.UserCovertBasic;
import com.chen.mapstruct.domain.UserVo1;
import com.chen.mapstruct.domain.UserVo2;
import com.chen.mapstruct.domain.UserVo3;
import com.chen.mapstruct.domain.UserVo4;
import com.chen.mapstruct.domain.UserVo5;
import com.chen.mapstruct.entity.User;
import com.chen.mapstruct.entity.UserEnum;
import com.chen.mapstruct.enums.UserTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 言少钱
 * @date 2020年04月22日 11:10
 * @Description:
 */
@RestController
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    @GetMapping("convert")
    public Object convertEntity() {
        // 构建测试需要用到的数据
        List<User> userList = new ArrayList<>();
        User user = User.builder().id(1).name("王昭君")
                .createTime("2020-04-22 10:18:24").updateTime(LocalDateTime.now())
                .build();
        userList.add(user);
        User user2 = User.builder().id(1).name("貂蝉")
                .createTime("2020-04-22 10:18:24").updateTime(LocalDateTime.now())
                .build();
        userList.add(user2);

        // 返回对象
        List<Object> objectList = new ArrayList<>();
        objectList.addAll(userList);

        /**
         * 把user转化为userVo1:
         * 特点如下：
         * userVo1和user属性个数相同,属性类型保持一致的情况
         */
        UserVo1 userVO1 = UserCovertBasic.INSTANCE.toConvertVO1(user);
        objectList.add("userVO1:" + UserCovertBasic.INSTANCE.toConvertVO1(user));
        objectList.add("userVO1转换回实体类user:" + UserCovertBasic.INSTANCE.fromConvertEntity1(userVO1));

        logger.info(objectList.toString());
        // 输出转换结果
        objectList.add("userVO2:" + " | " + UserCovertBasic.INSTANCE.toConvertVO2(user));
        // 使用BeanUtils
        UserVo2 userVO22 = new UserVo2();
        BeanUtils.copyProperties(user, userVO22);
        objectList.add("userVO22:" + " | " + userVO22);

        logger.info(objectList.toString());

        //转换集合对象
        objectList.add("userVO1List:" + " | " + UserCovertBasic.INSTANCE.toConvertVOList1(userList));
        objectList.add("userVO2List:" + " | " + UserCovertBasic.INSTANCE.toConvertVOList2(userList));

        logger.info(objectList.toString());

        UserVo3 userVO3 = UserCovertBasic.INSTANCE.toConvertVO3(user);
        objectList.add("userVO3：" + " | " + userVO3);
        objectList.add("userVO3转换回实体类user：" + " | " + UserCovertBasic.INSTANCE.fromConvertEntity3(userVO3));

        logger.info(objectList.toString());

        UserVo4 userVO4 = UserCovertBasic.INSTANCE.toConvertVO4(user);
        objectList.add("userVO4：" + " | " + userVO4);
        objectList.add("userVO4转换回实体类user：" + " | " + UserCovertBasic.INSTANCE.fromConvertEntity4(userVO4));

        logger.info(objectList.toString());

        return objectList;
    }

    @GetMapping("convert2")
    public Object convertEntity2() {
        // 返回对象
        List<Object> objectList = new ArrayList<>();
        // 构建测试需要用到的数据
        UserEnum userEnum = UserEnum.builder().id(1).name("淼淼之森").userTypeEnum(UserTypeEnum.Java).build();
        objectList.add(userEnum);

        UserVo5 userVO5 = UserCovertBasic.INSTANCE.toConvertVO5(userEnum);
        objectList.add("userVO5：" + " | " + userVO5);
        objectList.add("userVO5转换回实体类user：" + " | " + UserCovertBasic.INSTANCE.fromConvertEntity5(userVO5));

        return objectList;
    }
}
