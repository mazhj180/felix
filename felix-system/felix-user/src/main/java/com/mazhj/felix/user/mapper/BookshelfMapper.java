package com.mazhj.felix.user.mapper;

import com.mazhj.felix.user.pojo.model.Bookshelf;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * @author mazhj
 */
@Mapper
public interface BookshelfMapper {

    /**
     * 标注 @Results注解,提供结果集映射
     * @return 不做实际处理
     */
    @Results(id = "baseResultMap",value = {
            @Result(column = "id",jdbcType = JdbcType.INTEGER,property = "id",id = true),
            @Result(column = "user_id",jdbcType = JdbcType.VARCHAR,property = "userId"),
            @Result(column = "book_id",jdbcType = JdbcType.VARCHAR,property = "bookId"),
            @Result(column = "create_time",jdbcType = JdbcType.TIMESTAMP,property = "createTime")
    })
    @Select("select * from user_bookshelf")
    List<Bookshelf> baseResultMap();

    /**
     * 根据参数删除用户书架中的图书
     * @param userId 用户id
     * @param bookId 图书id
     * @return 返回删除的元组个数
     */
    @Delete("""
                delete from bookshelf
                where user_id = #{userId} and book_id = #{bookId}
            """)
    int deleteByUserIdAndBookId(String userId,String bookId);

    /**
     * 为用户插入新的图书
     * @param bookshelf 用户书架信息
     * @return 返回插入的元组个数
     */
    @Insert("""
                insert into bookshelf (user_id,book_id)
                values (#{userId},#{bookId})
            """)
    int insert(Bookshelf bookshelf);

    /**
     * 根据参数查询用户书架中所有图书
     * @param userId 用户id
     * @return 用户的书架中所有的图书
     */
    @ResultMap("baseResultMap")
    @Select("""
                <script>
                    select id,user_id,book_id,create_time
                    from bookshelf
                    <where>
                        <if test='userId != null'>
                            and user_id = #{userId}
                        </if>
                    </where>
                    order by create_time DESC
                </script>
            """)
    List<Bookshelf> selectListByUserId(String userId);

    /**
     * 统计用户书架中图书的数量
     * @param userId 用户id
     * @param bookId 图书id
     * @return 用户书架中图书的数量
     */
    @Select("""
                select count(1)
                from bookshelf
                where user_id =#{userId} and book_id =#{bookId}
            """)
    int selectCountsByUserIdAndBookId(String userId,String bookId);

    /**
     * 更新书架中图书信息
     * @param bookshelf 书架图书信息
     * @return 跟新的元组数
     */
    @Update("""
                <script>
                    update bookshelf
                    <set>
                        <if test='bookId != null'>
                            book_id =#{bookId}
                        </if>
                    </set>
                    where user_id =#{userId} and book_id =#{bookId}
                </script>
            """)
    int updateByUserIdAndBookId(Bookshelf bookshelf);

}
