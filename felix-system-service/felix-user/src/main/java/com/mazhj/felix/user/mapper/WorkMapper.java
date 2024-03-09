package com.mazhj.felix.user.mapper;

import com.mazhj.felix.user.pojo.model.Work;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author mazhj
 */
@Mapper
public interface WorkMapper {

    /**
     * 根据作者查询作品
     * @param authorId 作者id
     * @return 作品信息
     */
    @Select("""
                select  * from  work
                where author_id = #{authorId}
            """)
    List<Work> selectWorksByAuthorId(String authorId);

    /**
     * 创建作品
     * @param work 作品信息
     */
    @Insert("""
                insert into work()
                values ()
            """)
    void insert(Work work);

}
