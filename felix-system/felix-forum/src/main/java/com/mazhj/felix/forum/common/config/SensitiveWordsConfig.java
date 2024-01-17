package com.mazhj.felix.forum.common.config;

import com.mazhj.common.core.excel.PoiExcelDefinition;
import com.mazhj.common.core.exception.ExcelParseException;
import com.mazhj.felix.forum.common.config.properties.SensitiveWordsProperties;
import com.mazhj.felix.forum.websocket.container.SensitiveWordsTire;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author mazhj
 */
@Configuration
@EnableConfigurationProperties(value = SensitiveWordsProperties.class)
public class SensitiveWordsConfig {

    @Bean("sensitiveWordsTire")
    @ConditionalOnProperty(name = "sensitive-words.open",havingValue = "true")
    public SensitiveWordsTire sensitiveWordsTire(SensitiveWordsProperties properties) throws ExcelParseException, IOException {
        String path = properties.getWordsExcelPath();
        PoiExcelDefinition poiExcelDefinition = new PoiExcelDefinition(new File(path));
        Set<String> wordSet = Stream
                .concat(poiExcelDefinition.allValForStrToStream(), properties.getWords().stream())
                .collect(Collectors.toSet());
        return new SensitiveWordsTire(wordSet);
    }



}
