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
     * 根据状态查询作品
     * @param status 状态
     * @return 作品信息
     */
    @Select("""
                select * from work
                where status = #{status}
            """)
    List<Work> selectWorksByStatus(String status);

    /**
     * 创建作品
     * @param work 作品信息
     */
    @Insert("""
                insert into work(author_id,author_name,work_id,work_name,work_url,introduction,keywords)
                values (#{authorId},#{authorName},#{workId},#{workName},#{workUrl},#{introduction},#{keywords})
            """)
    void insert(Work work);

}
