package com.mazhj.felix.book.service.impl;

import com.mazhj.common.core.exception.BusinessException;
import com.mazhj.common.core.utils.Convert;
import com.mazhj.common.redis.keys.KeyBuilder;
import com.mazhj.common.redis.service.RedisService;
import com.mazhj.felix.book.mapper.ChapterMapper;
import com.mazhj.felix.book.pojo.model.Book;
import com.mazhj.felix.book.pojo.model.Chapter;
import com.mazhj.felix.book.pojo.vo.ChapterReadVO;
import com.mazhj.felix.book.pojo.vo.ChaptersVO;
import com.mazhj.felix.book.service.BookService;
import com.mazhj.felix.book.service.ChapterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ChapterServiceImpl implements ChapterService {

    private final ChapterMapper chapterMapper;

    private final BookService bookService;

    private final RedisService redisService;

    public ChapterServiceImpl(
            ChapterMapper chapterMapper,
            BookService bookService,
            RedisService redisService
    ) {
        this.chapterMapper = chapterMapper;
        this.bookService = bookService;
        this.redisService = redisService;
    }

    @Override
    public List<ChaptersVO> getChapterListByBookId(String bookId) {

        Book book = this.bookService.getBookInfoByBookId(bookId);
        if (book == null){
            throw new BusinessException("图书不存在");
        }
        String key = KeyBuilder.Book.getChaptersKey(bookId);
        List<ChaptersVO> chaptersVOList = this.redisService.get(key);

        if (!chaptersVOList.isEmpty()){
            return chaptersVOList;
        }

        List<Chapter> chapters = this.chapterMapper.selectChapterListByBookId(bookId);
        if (chapters.isEmpty()){
            throw new BusinessException("该图书章节信息不存在");
        }
        for (Chapter chapter : chapters) {
            chaptersVOList.add(Convert.to(chapter,ChaptersVO.class));
        }
        this.redisService.set(key,chaptersVOList,3600);

        return chaptersVOList;
    }

    @Override
    public Chapter getChapterContextByIdAndCode(String bookId, Integer chapterCode) {

        Book book = this.bookService.getBookInfoByBookId(bookId);
        if (book == null){
            throw new BusinessException("图书不存在");
        }
        String key = KeyBuilder.Book.getChapterKey(bookId);
        Map<String, Chapter> hashEntries = this.redisService.getHashEntries(key);
        Chapter chapter = hashEntries.get(chapterCode.toString());
        if (chapter != null){
            return chapter;
        }
        chapter = this.chapterMapper.selectByBookIdAndCode(bookId,chapterCode);
        if (chapter == null){
            throw new BusinessException("该章节不存在");
        }
        this.redisService.setHashVal(key,chapterCode.toString(),chapter);
        return chapter;
    }
    //todo 阅读章节
    @Override
    public ChapterReadVO read(String bookId, Integer chapterCode) {
        Book book = this.bookService.getBookInfoByBookId(bookId);
        if (book == null){
            throw new BusinessException("图书不存在");
        }
        String key = KeyBuilder.Book.getChapterNodeKey(bookId);
        String field = chapterCode.toString();

        return null;
    }
}
