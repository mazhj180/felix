package com.mazhj.felix.book.mapper;

import com.mazhj.felix.book.pojo.model.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author mazhj
 */
@Mapper
public interface CategoryMapper {

    @Select(
            """
                select * from category where category_id = #{categoryId}
            """
    )
    Category selectOneByCategoryId(String categoryId);

    @Select(
            """
                select id,category_id,name from category
            """
    )
    List<Category> select();

}
