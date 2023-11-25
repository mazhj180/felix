package com.mazhj.felix.book.service;

import com.mazhj.felix.book.pojo.model.Chapter;
import com.mazhj.felix.book.pojo.vo.ChapterReadVO;
import com.mazhj.felix.book.pojo.vo.ChaptersVO;

import java.util.List;

/**
 * @author mazhj
 */
public interface ChapterService {

    /**
     * 根据图书id获取章节列表
     * @param bookId 图书id
     * @return 章节列表
     */
    List<ChaptersVO> getChapterListByBookId(String bookId);

    /**
     * 根据图书id和章节id拿到章节的内容
     * @param bookId 图书id
     * @param chapterCode 章节code
     * @return 图书章节的内容
     */
    Chapter getChapterContentByIdAndCode(String bookId,Integer chapterCode);

    /**
     * 获取章节内容
     * @param bookId 图书id
     * @param chapterCode 章节code
     * @return 章节视图
     */
    ChapterReadVO read(String bookId,Integer chapterCode);
}
