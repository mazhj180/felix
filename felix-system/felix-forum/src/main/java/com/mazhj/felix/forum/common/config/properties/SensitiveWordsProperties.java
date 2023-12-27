package com.mazhj.felix.forum.common.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mazhj
 */
@Data
@ConfigurationProperties(prefix = "sensitive-words")
public class SensitiveWordsProperties {

    private Boolean open = false;

    private List<String> words = new ArrayList<>();

    private String wordsExcelPath;

}
