package com.mazhj.felix.control.mapper;

import com.mazhj.felix.control.pojo.model.DynamicComponents;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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




}
