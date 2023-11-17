package com.mazhj.felix.feign.book.clients;

import com.mazhj.common.pojo.dto.BookDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

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

}
