package com.mazhj.felix.user.mapper;

import com.mazhj.felix.user.pojo.model.Author;
import org.apache.ibatis.annotations.*;

/**
 * @author mazhj
 */
@Mapper
public interface AuthorMapper {

    @Results(id = "baseResultMap",value = {
            @Result(property = "id", column = "id"),
            @Result(property = "authorId", column = "author_id"),
            @Result(property = "identityCard", column = "identity_card"),
            @Result(property = "realName", column = "real_name"),
            @Result(property = "pseudonym", column = "pseudonym"),
            @Result(property = "nationality", column = "nationality"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "masterWorks", column = "master_works"),
            @Result(property = "introduction", column = "introduction")
    })
    @Select(" select * from author ")
    void baseResultMap();

    /**
     * 查询作者信息
     * @param authorId 作者id
     * @return 作者信息
     */
    @ResultMap("baseResultMap")
    @Select("""
                select * from author
                where author_id = #{authorId}
            """)
    Author selectAuthorById(String authorId);

    /**
     * 插入作者信息
     * @param author 作者信息
     * @return 操作行数
     */
    @Insert("""
                insert into author ()
            """)
    int insert(Author author);

}
