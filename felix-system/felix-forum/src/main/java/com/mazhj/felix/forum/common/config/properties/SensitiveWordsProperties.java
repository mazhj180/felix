package com.mazhj.felix.forum.common.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "sensitive-words")
public class SensitiveWordsProperties {

    private Boolean open = false;

    private List<String> words = new ArrayList<>();

    private String wordsExcelPath;

}
