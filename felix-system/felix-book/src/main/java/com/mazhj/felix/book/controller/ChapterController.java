package com.mazhj.felix.book.controller;

import com.mazhj.common.web.controller.BaseController;
import com.mazhj.common.web.response.AjaxResult;
import com.mazhj.felix.book.common.event.ClickEvent;
import com.mazhj.felix.book.pojo.vo.ChapterReadVO;
import com.mazhj.felix.book.pojo.vo.ChaptersVO;
import com.mazhj.felix.book.service.ChapterService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mazhj
 */
@RestController
@RequestMapping("/chapter")
public class ChapterController extends BaseController {
    private final ChapterService chapterService;

    private final ApplicationEventPublisher publisher;

    public ChapterController(
            ChapterService chapterService,
            ApplicationEventPublisher publisher
            ) {
        this.chapterService = chapterService;
        this.publisher = publisher;
    }

    @GetMapping("/list")
    public AjaxResult getChapterList(String bookId) {
        List<ChaptersVO> chapters = this.chapterService.getChapterListByBookId(bookId);
        this.publisher.publishEvent(new ClickEvent(this,bookId, ClickEvent.ClickType.LOOK_THROUGH));
        return success(chapters);
    }

    @GetMapping("/read")
    public AjaxResult read(String bookId,Integer chapterCode){
        ChapterReadVO readVO = this.chapterService.read(bookId, chapterCode);
        this.publisher.publishEvent(new ClickEvent(this,bookId, ClickEvent.ClickType.READ));
        return success(readVO);
    }
}
