package com.mazhj.felix.book.service.impl;

import com.mazhj.common.core.exception.BusinessException;
import com.mazhj.common.core.utils.Convert;
import com.mazhj.common.redis.keys.KeyBuilder;
import com.mazhj.common.redis.service.RedisService;
import com.mazhj.felix.book.mapper.ChapterMapper;
import com.mazhj.felix.book.pojo.model.Book;
import com.mazhj.felix.book.pojo.model.Chapter;
import com.mazhj.felix.book.pojo.vo.ChapterReadVO;
import com.mazhj.felix.book.pojo.vo.ChapterVO;
import com.mazhj.felix.book.pojo.vo.ChaptersVO;
import com.mazhj.felix.book.service.BookService;
import com.mazhj.felix.book.service.ChapterService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    public Chapter getChapterContentByIdAndCode(String bookId, Integer chapterCode) {

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

    @Override
    public ChapterReadVO read(String bookId, Integer chapterCode) {
        Book book = this.bookService.getBookInfoByBookId(bookId);
        if (book == null){
            throw new BusinessException("图书不存在");
        }
        String key = KeyBuilder.Book.getChapterNodeKey(bookId);
        String field = chapterCode.toString();
        ChapterNode node = this.redisService.getHashVal(key, field);
        if (node == null){
            List<Chapter> chapters = this.chapterMapper.selectChapterListByBookId(bookId);
            Map<String,ChapterNode> nodeMap = new HashMap<>();
            ChapterNode pre = null;
            for (Chapter chapter : chapters) {
                ChapterNode current = new ChapterNode(chapter.getBookId(), chapter.getChapterCode());
                if (pre != null){
                    current.setPre(pre);
                    pre.setNext(current);
                }
                nodeMap.put(current.chapterCode.toString(),current);
                pre = current;
            }
            this.redisService.setHashEntry(bookId,nodeMap);
            this.redisService.expire(bookId,3600, TimeUnit.SECONDS);
            node = nodeMap.get(field);
        }
        Chapter chapter = getChapterContentByIdAndCode(bookId, node.chapterCode);
        ChapterVO chapterVO = Convert.to(chapter, ChapterVO.class);
        ChapterVO pre = null;
        ChapterVO next = null;
        if (node.pre != null){
            pre = new ChapterVO()
                    .setBookId(node.pre.bookId)
                    .setChapterCode(node.pre.chapterCode);
        }
        if (node.next != null){
            next = new ChapterVO()
                    .setBookId(node.next.bookId)
                    .setChapterCode(node.next.chapterCode);
        }
        return new ChapterReadVO()
                .setCurrent(chapterVO)
                .setPre(pre)
                .setNext(next);
    }

    private static final class ChapterNode{

        private final String bookId;
        private final Integer chapterCode;
        private ChapterNode pre;
        private ChapterNode next;
        public ChapterNode(String bookId, Integer chapterCode) {
            this.bookId = bookId;
            this.chapterCode = chapterCode;
        }

        public void setPre(ChapterNode pre) {
            this.pre = pre;
        }

        public void setNext(ChapterNode next) {
            this.next = next;
        }
    }
}
