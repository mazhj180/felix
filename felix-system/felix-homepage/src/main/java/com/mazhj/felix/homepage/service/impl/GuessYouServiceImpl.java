package com.mazhj.felix.homepage.service.impl;

import com.mazhj.felix.homepage.pojo.model.Book;
import com.mazhj.felix.homepage.pojo.param.Reason;
import com.mazhj.felix.homepage.service.GuessYouService;

import java.util.List;

/**
 * @author mazhj
 */
public class GuessYouServiceImpl implements GuessYouService {
    @Override
    public List<Book> getPossibleLikes(String userId, Reason reason) {
        return null;
    }
}
