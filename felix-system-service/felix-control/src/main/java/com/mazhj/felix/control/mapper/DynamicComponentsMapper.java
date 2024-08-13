package com.mazhj.felix.control.mapper;

import com.mazhj.felix.control.pojo.model.DynamicComponents;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author mazhj
 */
@Mapper
public interface DynamicComponentsMapper {

    @Select("""
                select * from dynamic_components
                where component_id = #{componentId}
                order by create_time desc
            """)
    List<DynamicComponents> selectComponents(String componentId);

    @Delete("""
                delete from dynamic_components
                where id = #{id}
            """)
    void delComponent(Long id);

    @Delete("""
                <script>
                    delete from dynamic_components
                    where id in
                    <foreach collection="list" item="id" open="(" close=")" separator=",">
                		#{id}
                    </foreach>
                </script>
            """)
    void delComponents(List<Long> ids);

    @Insert("""
                insert into dynamic_components (component_id, component_name, content)
                values (#{componentId},#{componentName},#{content})
            """)
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void addComponent(DynamicComponents dynamicComponents);
}