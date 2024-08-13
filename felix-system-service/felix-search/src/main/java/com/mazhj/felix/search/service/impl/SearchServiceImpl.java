package com.mazhj.felix.search.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.mazhj.common.core.exception.SystemException;
import com.mazhj.common.pojo.dto.BookDTO;
import com.mazhj.common.web.utils.PageUtil;
import com.mazhj.felix.feign.book.clients.BookClient;
import com.mazhj.felix.search.pojo.Book;
import com.mazhj.felix.search.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author mazhj
 */
@Slf4j
@Service
public class SearchServiceImpl implements SearchService {

    private final ElasticsearchClient elasticsearchClient;

    private final BookClient bookClient;


    public SearchServiceImpl(ElasticsearchClient elasticsearchClient, BookClient bookClient) {
        this.elasticsearchClient = elasticsearchClient;
        this.bookClient = bookClient;
    }


    @Override
    public List<BookDTO> search(final String key) {
        try {
            SearchRequest reqKeywords = new SearchRequest.Builder()
                    .index("book")
                    .query(q -> q
                            .match(m -> m
                                    .field("keywords")
                                    .query(key)
                            )
                    )
                    .build();
            SearchRequest reqBookName = new SearchRequest.Builder()
                    .index("book") // 替换为你的索引名
                    .query(q -> q
                            .match(m -> m
                                    .field("bookName")
                                    .query(key)
                            )
                    )
                    .build();

            Stream<Hit<Book>> respKeywords = this.elasticsearchClient
                            .search(reqKeywords,Book.class).hits().hits()
                            .stream();

            Stream<Hit<Book>> respBookName = this.elasticsearchClient
                            .search(reqBookName,Book.class).hits().hits()
                            .stream();

            String[] bookIds = Stream.concat(respKeywords, respBookName)
                    .map(Hit::source)
                    .filter(Objects::nonNull)
                    .map(Book::getBookId)
                    .distinct()
                    .toArray(String[]::new);
            return this.bookClient.getBookBatch(bookIds);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BookDTO> searchBooksByKeyword(final String keyword) {
        try {
//            SearchRequest request = SearchRequest.of(s -> s
//                    .index("book")
//                    .query(q -> q.match(t -> t.field("keywords").query(keyword)))
//            );
            int fromNum = PageUtil.getFromNum();
            int pageSize = PageUtil.getPageSize();

            Query.Builder queryBuilder = new Query.Builder();
            if (keyword == null || keyword.isEmpty()) {
                queryBuilder.matchAll(m -> m);
            }else {
                queryBuilder.match(m -> m
                        .field("keywords")
                        .query(keyword)
                );
            }

            SearchRequest request = new SearchRequest.Builder()
                    .index("book")
                    .query(queryBuilder.build())
                    .from(fromNum)
                    .size(pageSize)
                    .build();


            SearchResponse<Book> response = elasticsearchClient.search(request,Book.class);

            return getBookInfo(response);
        } catch (IOException e) {

            throw new SystemException(e);
        }
    }

    @Override
    public List<BookDTO> searchBooksByName(final String name) {

        try {
            int fromNum = PageUtil.getFromNum();
            int pageSize = PageUtil.getPageSize();

            Query.Builder queryBuilder = new Query.Builder();
            if (name == null || name.isEmpty()) {
                queryBuilder.matchAll(m -> m);
            }else {
                queryBuilder.match(m -> m
                        .field("bookName")
                        .query(name)
                );
            }

            SearchRequest request = new SearchRequest.Builder()
                    .index("book")
                    .query(queryBuilder.build())
                    .from(fromNum)
                    .size(pageSize)
                    .build();

            SearchResponse<Book> response = elasticsearchClient.search(request,Book.class);

            return getBookInfo(response);
        } catch (IOException e) {

            throw new SystemException(e);
        }
    }

    private List<BookDTO> getBookInfo(SearchResponse<Book> response){

        if (response.hits().hits().isEmpty()) {
            return new ArrayList<>();
        }

        return this.bookClient.getBookBatch(
                response.hits().hits()
                .stream()
                .map(Hit::source)
                .filter(Objects::nonNull)
                .map(Book::getBookId)
                .toArray(String[]::new)
        );
    }
}
