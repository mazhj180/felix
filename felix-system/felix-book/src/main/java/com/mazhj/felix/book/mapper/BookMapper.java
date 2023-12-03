package com.mazhj.felix.book.mapper;

import com.mazhj.felix.book.common.BookCategoryTypeHandler;
import com.mazhj.felix.book.pojo.model.Book;
import com.mazhj.felix.book.pojo.model.BookCategory;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * @author mazhj
 */
@Mapper
public interface BookMapper {

    @Results(id = "baseResultMap",value = {
            @Result(id = true,column = "id",jdbcType = JdbcType.INTEGER,property = "id"),
            @Result(column = "book_id",jdbcType = JdbcType.VARCHAR,property = "bookId"),
            @Result(column = "author_id",jdbcType = JdbcType.VARCHAR,property = "authorId"),
            @Result(column = "author_name",jdbcType = JdbcType.VARCHAR,property = "authorName"),
            @Result(column = "book_name",jdbcType = JdbcType.VARCHAR,property = "bookName"),
            @Result(column = "score",jdbcType = JdbcType.TINYINT,property = "score"),
            @Result(column = "keywords",jdbcType = JdbcType.VARCHAR,property = "keywords"),
            @Result(column = "img_url",jdbcType = JdbcType.VARCHAR,property = "imgUrl"),
            @Result(column = "introduction",jdbcType = JdbcType.VARCHAR,property = "introduction"),
            @Result(column = "isbn",jdbcType = JdbcType.VARCHAR,property = "isbn"),
            @Result(column = "word_count",jdbcType = JdbcType.INTEGER,property = "wordCount")
    })
    @Select(
            """
                select * from book
            """
    )
    List<Book> select();

    /**
     * 通过book_id检索图书信息
     * @param bookId 图书id
     * @return 图书model
     */
    @ResultMap("baseResultMap")
    @Select(
            """
                select * from book 
                where book_id = #{bookId}
            """
    )
    Book selectOneByBookId(String bookId);

    /**
     * 根据book_name检索图书信息
     * @param bookName 图书name
     * @return 图书model
     */
    @ResultMap("baseResultMap")
    @Select(
            """
                select * from book 
                where book_name = #{bookName}
            """
    )
    List<Book> selectManyByBookName(String bookName);

    /**
     * 获取图书的分类
     * @param bookId 图书id
     * @return 图书分类信息
     */
    @Results({
            @Result(property = "id", column = "id", javaType = Long.class),
            @Result(property = "bookId",column = "book_id",javaType = String.class),
            @Result(property = "category", column = "categoryId", typeHandler = BookCategoryTypeHandler.class)
    })
    @Select(
            """
                select * from book_category_relation where book_id = #{bookId}
            """
    )
    List<BookCategory> selectCategoriesByBookId(String bookId);

}
