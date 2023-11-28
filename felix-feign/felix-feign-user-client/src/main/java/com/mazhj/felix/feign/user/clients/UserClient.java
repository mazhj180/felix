package com.mazhj.felix.feign.user.clients;

import com.mazhj.common.pojo.dto.BookshelfDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author mazhj
 */
@FeignClient(name = "felix-user")
public interface UserClient {

    /**
     * 获取用户书架中的图书信息
     * @param userId 用户id
     * @return 图书列表
     */
    @GetMapping("/user/bookshelf/feign/get-bookshelf-books")
    List<BookshelfDTO> getBookshelf(String userId);
}
