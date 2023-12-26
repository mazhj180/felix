drop table if exists `book`;
create table `book`
(
    `id`            int unsigned auto_increment comment '数据库主键',
    `book_id`       varchar(32)  not null comment '图书id',
    `author_id`     varchar(32)  not null comment '作者id',
    `author_name`   varchar(32)  not null comment '作者名称',
    `book_name`     varchar(32)  not null comment '图书名称',
    `score`         tinyint      not null check ( `score` >= 0 and `score` <= 100 ) comment '图书评分 0～100',
    `keywords`      text         not null comment '搜索关键词',
    `img_url`       varchar(255) not null comment '封面图片',
    `introduction`  text         not null comment '图书简介',
    `isbn`          varchar(255) comment '图书isbn',
    `word_count`    int comment '字数',
    `support_count` int comment '收藏或点赞数',
    primary key (`id`),
    unique key `idx_book_id` (`book_id`),
    unique key `idx_isbn` (`isbn`)
)engine =InnoDB default charset =utf8mb4 collate =utf8mb4_unicode_ci row_format =dynamic comment '图书信息表';


drop table if exists `chapter`;
create table `chapter`
(
    `id`           int unsigned auto_increment comment '数据库主键',
    `book_id`      varchar(32)                     not null comment '图书id',
    `chapter_code` int                             not null comment '章节编码',
    `name`         varchar(255)                    not null comment '章节名称',
    `content`      text collate utf8mb4_unicode_ci not null comment '章节内容',
    `status`       tinyint                         not null default 0 check ( `status` = 0 or `status` = 1) comment '状态, 0:正常, 1:锁章',
    `create_time`  timestamp                       not null default current_timestamp comment '创建时间',
    `update_time`  timestamp                       not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (`id`),
    unique key idx_book_id (`book_id`),
    unique key idx_book_chapter (`book_id`,`chapter_code`)
)engine =InnoDB default charset =utf8mb4 collate =utf8mb4_unicode_ci row_format =dynamic comment '图书章节信息表';

drop table if exists `author`;
create table author
(
    `id`            int unsigned auto_increment comment '数据库主键',
    `author_id`     varchar(32)                     not null comment '作者id',
    `identity_card` varchar(32)                     not null comment '身份证号',
    `real_name`     varchar(32)                     not null comment '真实名字',
    `pseudonym`     varchar(32)                     not null comment '笔名',
    `nationality`   varchar(255)                    not null comment '国籍',
    `birthday`      date comment '出生日',
    `master_works`  varchar(255) comment '代表作',
    `introduction`  text collate utf8mb4_unicode_ci not null comment '作者简介',
    primary key (`id`),
    unique key `idx_author_id` (`author_id`)
)engine =InnoDB default charset =utf8mb4 collate =utf8mb4_unicode_ci row_format =dynamic comment '作者信息表';

drop table if exists `category`;
create table `category`
(
    `id`          int unsigned auto_increment comment '数据库主键',
    `category_id` varchar(64) not null comment '类别id',
    `name`        varchar(32) not null comment '类别名称',
    primary key (`id`),
    unique key idx_category_id (`category_id`)
)engine = InnoDB default charset =utf8mb4 comment '图书类别表';

drop table if exists `book_category_relation`;
create table `book_category_relation`
(
    `id`          int unsigned auto_increment comment '数据库主键',
    `book_id`     varchar(32) not null comment '图书id',
    `category_id` varchar(64) not null comment '类别id',
    primary key (`id`),
    unique key `idx_book_category_id` (`book_id`,`category_id`)
)engine = InnoDB default charset =utf8mb4 comment '图书类别映射关系表';

INSERT INTO category (category_id, name) VALUES ('fiction', '小说');
INSERT INTO category (category_id, name) VALUES ('non_fiction', '非小说');
INSERT INTO category (category_id, name) VALUES ('science_fiction', '科幻小说');
INSERT INTO category (category_id, name) VALUES ('fantasy', '奇幻小说');
INSERT INTO category (category_id, name) VALUES ('mystery', '悬疑小说');
INSERT INTO category (category_id, name) VALUES ('biography', '传记');
INSERT INTO category (category_id, name) VALUES ('autobiography', '自传');
INSERT INTO category (category_id, name) VALUES ('historical_fiction', '历史小说');
INSERT INTO category (category_id, name) VALUES ('romance', '爱情小说');
INSERT INTO category (category_id, name) VALUES ('thriller', '惊悚小说');
INSERT INTO category (category_id, name) VALUES ('self_help', '自助书');
INSERT INTO category (category_id, name) VALUES ('cookbooks', '烹饪书');
INSERT INTO category (category_id, name) VALUES ('children_books', '儿童书籍');
INSERT INTO category (category_id, name) VALUES ('young_adult', '青少年小说');
INSERT INTO category (category_id, name) VALUES ('poetry', '诗歌');
INSERT INTO category (category_id, name) VALUES ('travel', '旅行');
INSERT INTO category (category_id, name) VALUES ('art', '艺术');
INSERT INTO category (category_id, name) VALUES ('religion', '宗教');
INSERT INTO category (category_id, name) VALUES ('science', '科学');
INSERT INTO category (category_id, name) VALUES ('health', '健康');
INSERT INTO category (category_id, name) VALUES ('business', '商业');
INSERT INTO category (category_id, name) VALUES ('political', '政治');
INSERT INTO category (category_id, name) VALUES ('technology', '技术');
INSERT INTO category (category_id, name) VALUES ('education', '教育');
INSERT INTO category (category_id, name) VALUES ('humor', '幽默');
INSERT INTO category (category_id, name) VALUES ('sports', '运动');
INSERT INTO category (category_id, name) VALUES ('music', '音乐');
INSERT INTO category (category_id, name) VALUES ('drama', '戏剧');
INSERT INTO category (category_id, name) VALUES ('adventure', '冒险');
INSERT INTO category (category_id, name) VALUES ('psychology', '心理学');
INSERT INTO category (category_id, name) VALUES ('philosophy', '哲学');
INSERT INTO category (category_id, name) VALUES ('classics', '经典');
INSERT INTO category (category_id, name) VALUES ('graphic_novels', '图形小说');
INSERT INTO category (category_id, name) VALUES ('horror', '恐怖');
