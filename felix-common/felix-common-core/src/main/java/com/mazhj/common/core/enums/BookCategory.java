package com.mazhj.common.core.enums;

/**
 * @author mazhj
 */
public enum BookCategory {

    FICTION("小说"),
    NON_FICTION("非小说"),
    SCIENCE_FICTION("科幻小说"),
    FANTASY("奇幻小说"),
    MYSTERY("悬疑小说"),
    BIOGRAPHY("传记"),
    AUTOBIOGRAPHY("自传"),
    HISTORICAL_FICTION("历史小说"),
    ROMANCE("爱情小说"),
    THRILLER("惊悚小说"),
    SELF_HELP("自助书"),
    COOKBOOKS("烹饪书"),
    CHILDREN_BOOKS("儿童书籍"),
    YOUNG_ADULT("青少年小说"),
    POETRY("诗歌"),
    TRAVEL("旅行"),
    ART("艺术"),
    RELIGION("宗教"),
    SCIENCE("科学"),
    HEALTH("健康"),
    BUSINESS("商业"),
    POLITICAL("政治"),
    TECHNOLOGY("技术"),
    EDUCATION("教育"),
    HUMOR("幽默"),
    SPORTS("运动"),
    MUSIC("音乐"),
    DRAMA("戏剧"),
    ADVENTURE("冒险"),
    PSYCHOLOGY("心理学"),
    PHILOSOPHY("哲学"),
    CLASSICS("经典"),
    GRAPHIC_NOVELS("图形小说"),
    HORROR("恐怖");

    private final String displayName;

    BookCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

