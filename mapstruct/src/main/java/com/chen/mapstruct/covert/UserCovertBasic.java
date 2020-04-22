package com.chen.mapstruct.covert;

import com.chen.mapstruct.domain.UserVo1;
import com.chen.mapstruct.domain.UserVo2;
import com.chen.mapstruct.domain.UserVo3;
import com.chen.mapstruct.domain.UserVo4;
import com.chen.mapstruct.domain.UserVo5;
import com.chen.mapstruct.entity.User;
import com.chen.mapstruct.entity.UserEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 言少钱
 * @date 2020年04月22日 10:48
 * @Description:user实体类型普通映射转换接口
 * @Mapper注解解释：
 * 从 mybatis3.4.0 开始加入的 @Mapper 注解，目的就是为了不再写mapper映射文件。
 * @Mapper的componentModel属性：
 * 用于指定自动生成的接口实现类的组件类型，这个属性支持四个值：
 *      default: 这是默认的情况，mapstruct 不使用任何组件类型, 可以通过Mappers.getMapper(Class)方式获取自动生成的实例对象。
 *      cdi: the generated mapper is an application-scoped CDI bean and can be retrieved via @Inject
 *      spring: 生成的实现类上面会自动添加一个@Component注解，可以通过Spring的 @Autowired方式进行注入
 *      jsr330: 生成的实现类上会添加@javax.inject.Named 和@Singleton注解，可以通过 @Inject注解获取
 */
@Mapper(componentModel = "spring")
public interface UserCovertBasic {

    /**
     * 让客户端可以访问 Mapper 接口的实现
     */
    UserCovertBasic INSTANCE = Mappers.getMapper(UserCovertBasic.class);

    /**
     * 字段数量类型数量相同，利用工具BeanUtils也可以实现类似效果，domain实体类和entity类属性映射
     * entity类转换domain实体类
     * @param source
     * @return
     */
    UserVo1 toConvertVO1(User source);

    /**
     * domain类实体类转换entity类
     * @param userVO1
     * @return
     */
    User fromConvertEntity1(UserVo1 userVO1);

    /**
     * entity类集合转换domain实体类集合
     * @param source
     * @return
     */
    List<UserVo1> toConvertVOList1(List<User> source);

    /**
     * 字段数量类型相同,数量少：仅能让多的转换成少的，故没有fromConvertEntity2
     * @param source
     * @return
     */
    UserVo2 toConvertVO2(User source);

    /**
     * entity类集合转换domain实体类集合
     * @param source
     * @return
     */
    List<UserVo2> toConvertVOList2(List<User> source);

    /**
     * 字段类型不一致:
     * 以下的类型之间是mapstruct自动进行类型转换的:
     * 1、基本类型及其他们对应的包装类型,此时mapstruct会自动进行拆装箱,不需要人为的处理.
     * 2、基本类型的包装类型和string类型之间
     * 特殊类型需要自定义转换器,比如日期类型转换如下场景：
     *  这里是entity类转换为domain实体类，entity类中的createTime是String类型，domain实体类中的createTime是LocalDateTime类型
     * @param source
     * @return
     */
    @Mappings({
            @Mapping(target = "createTime", expression = "java(com.chen.mapstruct.util.DateFormatSecurity.strToDate(source.getCreateTime()))"),
    })
    UserVo3 toConvertVO3(User source);

    /**
     * domain实体类转换entity类
     * @param userVO3
     * @return
     */
    User fromConvertEntity3(UserVo3 userVO3);


    /**
     * 字段类型一致，名字不一致
     * mapstruct 通过读取我们配置的字段名对应关系，帮我们把它们赋值在了相对应的位置上
     * @param source
     * @return
     */
    @Mappings({
            @Mapping(source = "id", target = "userId"),
            @Mapping(source = "name", target = "userName")
    })
    UserVo4 toConvertVO4(User source);

    /**
     * domain实体类转换entity类
     * @param userVO4
     * @return
     */
    User fromConvertEntity4(UserVo4 userVO4);


    /**
     * 属性带枚举类型的entity转domain实体类
     * @param source
     * @return
     */
    @Mapping(source = "userTypeEnum", target = "type")
    UserVo5 toConvertVO5(UserEnum source);

    /**
     * domain实体类转换entity类
     * @param userVO5
     * @return
     */
    UserEnum fromConvertEntity5(UserVo5 userVO5);
}
