package com.mazhj.common.pojo.enums;

import lombok.Getter;

/**
 * @author mazhj
 */
@Getter
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
    HORROR("恐怖"),
    CRIME("犯罪"),
    WESTERN("西部"),
    URBAN_FANTASY("都市奇幻"),
    CYBERPUNK("赛博朋克"),
    DYSTOPIAN("反乌托邦"),
    STEAMPUNK("蒸汽朋克"),
    SPACE_OPERA("太空歌剧"),
    HARD_SCIENCE_FICTION("硬科幻"),
    MILITARY_SCIENCE_FICTION("军事科幻"),
    ALTERNATE_HISTORY("另类历史"),
    ESPIONAGE("间谍"),
    LEGAL("法律"),
    MEDICAL("医学"),
    PSYCHOLOGICAL_THRILLER("心理惊悚"),
    TRUE_CRIME("真实犯罪"),
    WAR("战争"),
    SATIRE("讽刺"),
    FOLKLORE("民间传说"),
    MYTHOLOGY("神话"),
    FAIRY_TALES("童话"),
    MAGICAL_REALISM("魔幻现实主义"),
    POST_APOCALYPTIC("末日后"),
    SUPERHERO("超级英雄"),
    SURREALISM("超现实主义"),
    GOTHIC("哥特"),
    VAMPIRE("吸血鬼"),
    ZOMBIE("僵尸"),
    WEREWOLF("狼人"),
    TIME_TRAVEL("时间旅行"),
    PARANORMAL("超自然"),
    ECOLOGY("生态学"),
    ANTHROPOLOGY("人类学"),
    ASTROLOGY("占星术"),
    ASTRONOMY("天文学"),
    QUANTUM_PHYSICS("量子物理"),
    ROBOTICS("机器人技术"),
    LINGUISTICS("语言学"),
    ARCHAEOLOGY("考古学"),
    GENETICS("遗传学"),
    NEUROSCIENCE("神经科学"),
    PHILANTHROPY("慈善学"),
    URBAN_PLANNING("城市规划"),
    OCEANOGRAPHY("海洋学"),
    ENVIRONMENTAL_SCIENCE("环境科学"),
    METEOROLOGY("气象学"),
    GEOLOGY("地质学"),
    SUSTAINABLE_DEVELOPMENT("可持续发展"),
    FORESTRY("林业"),
    HORTICULTURE("园艺"),
    AGRICULTURE("农业");

    private final String displayName;

    BookCategory(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

