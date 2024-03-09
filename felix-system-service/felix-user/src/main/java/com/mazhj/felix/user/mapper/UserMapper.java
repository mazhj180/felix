package com.mazhj.felix.user.mapper;

import com.mazhj.felix.user.pojo.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * @author mazhj
 */
@Mapper
public interface UserMapper {

    /**
     * 标注 @Results注解,提供结果集映射
     * @return 不做实际处理
     */
    @Results(id = "baseResultMap",value = {
            @Result(column = "id",jdbcType = JdbcType.INTEGER,property = "id"),
            @Result(column = "user_id",jdbcType = JdbcType.VARCHAR,property = "userId"),
            @Result(column = "user_pwd",jdbcType = JdbcType.VARCHAR,property = "userPwd"),
            @Result(column = "nick_name",jdbcType = JdbcType.VARCHAR,property = "nickName"),
            @Result(column = "phone_number",jdbcType = JdbcType.VARCHAR,property = "phoneNumber"),
            @Result(column = "head_img_url",jdbcType = JdbcType.VARCHAR,property = "headImgUrl"),
            @Result(column = "level",jdbcType = JdbcType.VARCHAR,property = "level"),
            @Result(column = "create_time",jdbcType = JdbcType.TIMESTAMP,property = "createTime"),
            @Result(column = "update_time",jdbcType = JdbcType.TIMESTAMP,property = "updateTime")
    })
    @Select("select * from user")
    List<User> baseResultMap();

    /**
     * 插入用户信息
     * @param user 用户信息
     * @return 插入的元组数
     */
    @Insert("""
                insert into user (user_id, user_pwd, nick_name, phone_number, email)
                values (#{userId},#{userPwd,jdbcType=VARCHAR},#{nickName,jdbcType=VARCHAR}, #{phoneNumber,jdbcType=VARCHAR},#{email})
            """)
    void insert(User user);

    /**
     * 根据参数查询用户信息
     * @param userId 用户登录名
     * @return 用户信息
     */
    @ResultMap("baseResultMap")
    @Select("""
                select id,user_id,user_pwd,nick_name,phone_number,email,
                       head_img_url,level,create_time,update_time
                from user
                where user_id = #{userId}
            """)
    User selectByUserId(String userId);

    @ResultMap("baseResultMap")
    @Select("""
                <script>
                    select * from user
                    <where>
                        <if test = 'userId != null'>
                            user_id = #{userId}
                        </if>
                        <if test = 'userName != null'>
                            and nick_name = #{userName}
                        </if>
                        <if test = 'isWriter == true'>
                            and level = writer
                        </if>
                    </where>
                </script>
            """)
    List<User> selectUserList(String userId,String userName,String isWriter);

    @ResultMap("baseResultMap")
    @Update("""
                update user
                set state = #{state}
                where userId = #{userId}
            """)
    int updateState(User user);
}
