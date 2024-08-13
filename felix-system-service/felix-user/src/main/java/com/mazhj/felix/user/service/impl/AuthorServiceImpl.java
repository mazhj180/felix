package com.mazhj.felix.user.service.impl;

import com.mazhj.felix.user.mapper.AuthorMapper;
import com.mazhj.felix.user.mapper.UserMapper;
import com.mazhj.felix.user.pojo.model.Author;
import com.mazhj.felix.user.pojo.model.User;
import com.mazhj.felix.user.service.AuthorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author mazhj
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorMapper authorMapper;

    private final UserMapper userMapper;

    public AuthorServiceImpl(AuthorMapper authorMapper, UserMapper userMapper) {
        this.authorMapper = authorMapper;
        this.userMapper = userMapper;
    }

    @Override
    public Author getAuthor(String authorId) {
        return this.authorMapper.selectAuthorById(authorId);
    }

    @Transactional
    @Override
    public void addOneAuthor(Author author) {
        this.authorMapper.insert(author);
        this.userMapper.updateLevel(author.getAuthorId(),"writer");
    }
}
