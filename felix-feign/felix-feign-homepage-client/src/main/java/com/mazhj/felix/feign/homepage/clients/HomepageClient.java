package com.mazhj.felix.feign.homepage.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author mazhj
 */
@FeignClient(name = "felix-homepage")
public interface HomepageClient {

    /**
     * 远程接口调用；增加热度
     * @param bookId 图书id
     * @param hot 热度
     */
    @PostMapping("/rank/feign/incr")
    void incrHot(String bookId,Double hot);


}
