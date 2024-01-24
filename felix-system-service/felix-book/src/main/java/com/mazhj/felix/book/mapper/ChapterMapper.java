package com.mazhj.felix.book.mapper;

import com.mazhj.felix.book.pojo.model.Chapter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author mazhj
 */
@Mapper
public interface ChapterMapper {
    /**
     * 根据章节编号查询章节信息
     * @param bookId 图书id
     * @param chapterCode 章节code
     * @return 章节信息
     */
    @Select(
            """
                select * from book_chapter
                where book_id = #{bookId} and chapter_code = #{chapterCode}
            """
    )
    Chapter selectByBookIdAndCode(String bookId,Integer chapterCode);

    /**
     * 根据图书id 查询所有章节的信息
     * @param bookId 图书id
     * @return 图书的所有章节
     */
    @Select(
            """
                select id,book_id,name,status,chapter_code
                from book_chapter
                where book_id = #{bookId}
            """
    )
    List<Chapter> selectChapterListByBookId(String bookId);
}
