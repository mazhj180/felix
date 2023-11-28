package com.mazhj.felix.feign.search.clients;

import com.mazhj.common.pojo.dto.BookDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author mazhj
 */
@FeignClient(name = "felix-search")
public interface SearchClient {

    /**
     * 远程调用搜索引擎，根据关键词搜索
     * @param keyword 关键词
     * @return 图书列表
     */
    @GetMapping("/search/feign/get-books/keyword")
    List<BookDTO> searchBooksByKeyword(String keyword);

    /**
     * 远程调用搜索引擎，根据名称分词搜索
     * @param name 图书名称
     * @return 图书列表
     */
    @GetMapping("/search/feign/get-books/name")
    List<BookDTO> searchBooksByName(String name);

}
