package com.mazhj.felix.feign.book.clients;

import com.mazhj.common.pojo.dto.BookDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author mazhj
 */
@FeignClient(name = "felix-book")
public interface BookClient {

    /**
     * 远程接口调用；根据book_id获取信息
     * @param bookId 图书id
     * @return 图书dto
     */
    @GetMapping("/book/feign/get-book")
    BookDTO getBookByBookId(String bookId);

    /**
     * 远程接口调用；获取全部图书
     * @return 图书列表
     */
    @GetMapping("/book/feign/get-book-all")
    List<BookDTO> getBookList();

    /**
     * 批量获取图书信息
     * @param bookIds id集合
     * @return 图书信息
     */
    @GetMapping("/book/feign/get-book-batch")
    List<BookDTO> getBookBatch(String[] bookIds);

    /**
     * 按评分排名获取图书
     * @param limit 限制条数
     * @return 图书列表
     */
    @GetMapping("/book/feign/get-book-ss")
    List<BookDTO> getBookSortedScore(Integer limit);

}
