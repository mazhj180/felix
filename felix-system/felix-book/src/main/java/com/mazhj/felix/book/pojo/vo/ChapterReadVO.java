package com.mazhj.felix.book.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author mazhj
 */
@Data
@Accessors(chain = true)
public class ChapterReadVO {

    ChapterVO current;

    ChapterVO pre;

    ChapterVO next;
}
