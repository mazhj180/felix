package com.mazhj.felix.user.service.impl;

import com.mazhj.felix.user.mapper.AuthorMapper;
import com.mazhj.felix.user.pojo.model.Author;
import com.mazhj.felix.user.service.AuthorService;
import org.springframework.stereotype.Service;

/**
 * @author mazhj
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorMapper authorMapper) {
        this.authorMapper = authorMapper;
    }

    @Override
    public Author getAuthor(String authorId) {
        return this.authorMapper.selectAuthorById(authorId);
    }

    @Override
    public void addOneAuthor(Author author) {
        this.authorMapper.insert(author);
    }
}
