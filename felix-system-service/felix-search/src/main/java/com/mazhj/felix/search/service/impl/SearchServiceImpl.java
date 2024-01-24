package com.mazhj.felix.search.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.mazhj.common.core.exception.SystemException;
import com.mazhj.felix.search.pojo.Book;
import com.mazhj.felix.search.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mazhj
 */
@Slf4j
@Service
public class SearchServiceImpl implements SearchService {

    private final ElasticsearchClient elasticsearchClient;

    public SearchServiceImpl(ElasticsearchClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    @Override
    public List<Book> searchBooksByKeyword(String keyword) {
        try {
            SearchRequest request = SearchRequest.of(s -> s
                    .index("book")
                    .query(q -> q.match(t -> t.field("keyword").query(keyword)))
            );

            SearchResponse<Book> response = elasticsearchClient.search(request,Book.class);

            List<Book> bookList = new ArrayList<>();
            response.hits().hits().forEach(hit -> bookList.add(hit.source()));
            return bookList;
        } catch (IOException e) {

            throw new SystemException(e);
        }
    }

    @Override
    public List<Book> searchBooksByName(String name) {

        try {
            SearchRequest request = SearchRequest.of(s -> s
                    .index("book")
                    .query(q -> q.match(t -> t.field("name").query(name)))
            );

            SearchResponse<Book> response = elasticsearchClient.search(request,Book.class);

            List<Book> bookList = new ArrayList<>();
            response.hits().hits().forEach(hit -> bookList.add(hit.source()));

            return bookList;
        } catch (IOException e) {

            throw new SystemException(e);
        }
    }
}
