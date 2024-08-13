package com.mazhj.felix.book.mapper;

import com.mazhj.felix.book.common.handler.BookCategoryTypeHandler;
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
            @Result(column = "word_count",jdbcType = JdbcType.INTEGER,property = "wordCount"),
            @Result(column = "support_count",jdbcType = JdbcType.INTEGER,property = "supportCount"),
            @Result(column = "create_time",jdbcType = JdbcType.TIMESTAMP,property = "createTime"),
            @Result(column = "update_time",jdbcType = JdbcType.TIMESTAMP,property = "updateTime")
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

    @ResultMap("baseResultMap")
    @Select(
            """
                <script>
                    select * from book
                    where book_id in
                    <foreach item="id" collection="array" open="(" separator="," close=")">
                        #{id}
                    </foreach>
                </script>
            """
    )
    List<Book> selectBatchByBookId(String[] bookIds);

    /**
     * 根据score排名获取图书
     * @param limit 限制条数
     * @return 图书列表
     */
    @ResultMap("baseResultMap")
    @Select(
            """
                select * from book
                order by score DESC
                limit #{limit}
            """
    )
    List<Book> selectBookSortedScore(Integer limit);

    /**
     * 根据创建时间获取图书
     * @param limit 限制条数
     * @return 图书列表
     */
    @ResultMap("baseResultMap")
    @Select("""
                select * from book
                order by create_time DESC
                limit #{limit}
            """)
    List<Book> selectBookSortedTime(Integer limit);

    /**
     * 根据support排名获取图书
     * @param limit 限制条数
     * @return 图书列表
     */
    @ResultMap("baseResultMap")
    @Select(
            """
                select * from book
                order by support_count DESC
                limit #{limit}
            """
    )
    List<Book> selectBookSortedSupport(Integer limit);

    @ResultMap("baseResultMap")
    @Select("""
                <script>
                    select * from book
                    <where>
                        <if test = 'bookName != null and bookName != ""'>
                            book_name = #{bookName}
                        </if>
                    </where>
                </script>
            """)
    List<Book> selectBookByName(String bookName);

    int insertBatch(List<Book> books);

    @Delete("""
                delete from book
                where book_id = #{bookId}
            """)
    void delBook(String bookId);

    @Update("""
                update book
                set score = #{score}
                where book_id = #{bookId}
            """)
    void updateScore(String bookId,Integer score);

    @Update("""
                update book
                set support_count = #{supports}
                where book_id = #{bookId}
            """)
    void updateSupport(String bookId, Integer supports);

    /**
     * 获取图书的分类
     * @param bookId 图书id
     * @return 图书分类信息
     */
    @Results(id = "bookCategoryMap",value = {
            @Result(property = "id", column = "id", javaType = Long.class),
            @Result(property = "bookId",column = "book_id",javaType = String.class),
            @Result(property = "category", column = "category_id", javaType = com.mazhj.common.pojo.enums.BookCategory.class,typeHandler = BookCategoryTypeHandler.class)
    })
    @Select(
            """
                select id,book_id,category_id from book_category_relation where book_id = #{bookId}
            """
    )
    List<BookCategory> selectCategoriesByBookId(String bookId);

    /**
     * 根据分类获取图书
     * @param category 分类id
     * @return 图书id信息
     */
    @ResultMap("bookCategoryMap")
    @Select(
            """
                select id,book_id,category_id from book_category_relation where category_id = #{category}
            """
    )
    List<BookCategory> selectBookByCategory(String category);
}
