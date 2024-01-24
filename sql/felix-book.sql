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

insert into book (book_id, author_id, author_name, book_name, score, keywords, img_url, introduction, isbn, word_count,
                  support_count)
VALUES ('shuihuzhuan', 'shinaian', '施耐庵', '水浒传', 99, '四大名著、一百单八将、林聪、打虎、武松', '/shuihu/img',
        '全书通过描写梁山好汉反抗欺压、水泊梁山壮大和受宋朝招安，以及受招安后为宋朝征战，最终消亡的宏大故事，艺术地反映了中国历史上宋江起义从发生、发展直至失败的全过程，深刻揭示了起义的社会根源，满腔热情地歌颂了起义英雄的反抗斗争和他们的社会理想，也具体揭示了起义失败的内在历史原因。',
        '978-7-5484-2759-9', 850000, 10000),
       ('qingyunian', 'maoni', '猫腻', '庆余年', 99, '穿越、范闲、南庆诗仙、大宗师、朝堂权谋', '/qingyunian/img',
        '讲述了一个有着神秘身世的少年范闲，自海边小城初出茅庐，历经家族、江湖、庙堂的种种考验、锤炼的故事 。',
        '978-7-0212-2317-8', 100000, 23211),
       ('honglou', 'caoxueqin', '曹雪芹', '红楼梦', 100, '古代爱情、大家族、石头记、四大名著之首', '/honglou/img',
        '《红楼梦》，是中国古代章回体长篇虚构小说，中国古典四大名著之首。其通行本共120回，一般认为前80回是清代作家曹雪芹所著，后40回作者为无名氏，由高鹗、程伟元整理。小说以贾、史、王、薛四大家族的兴衰为背景，以大荒山青埂峰下顽石幻化的通灵宝玉为视角，以贾宝玉与林黛玉、薛宝钗的爱情婚姻悲剧为主线，描绘了一些闺阁佳人的人生百态，展现了真正的人性美和悲剧美，是一部从各个角度展现中国古代社会百态的史诗性著作',
        '991-2-3241-4555-6', 100000, 23234),
       ('sanguo', 'luoguanzhong', '罗贯中', '三国', 100, '东汉末年、英雄、桃源三结义、关羽、乱世、四大名著', '/sanguo/img',
        '描写了从东汉末年到西晋初年之间近百年的历史风云，以描写战争为主，描述了东汉末年的群雄割据混战和魏、蜀、吴三国之间的政治和军事斗争，最终司马炎一统三国，建立晋朝的故事。反映了三国时代各类社会斗争与矛盾的转化，并概括了这一时代的历史巨变，塑造了一群叱咤风云的三国英雄人物。',
        '992-3-1256-5432-5', 100203, 46332),
       ('yingxiong', 'quantou', '拳头', '英雄', 80, '快意恩仇、江湖、古代', '/yingxiong/img',
        '讲述一个快意恩仇的江湖中，暗流涌动各方势力争权夺利、谁才是这个世界的英雄',
        '232-2-2324-2222-1', 923412, 221),
       ('xiyouji', 'wuchengen', '吴承恩', '西游记', 98, '唐僧、孙悟空、西天取经、四大名著', '/xiyouji/img',
        '《西游记》是中国古典四大名著之一，讲述了唐僧师徒四人西天取经的传奇故事，充满了神话色彩和深刻的寓意。',
        '978-3-16-148410-0', 500000, 15000),
       ('daomu', 'kennima', '南派三叔', '盗墓笔记', 95, '盗墓、历险、古墓、奇幻', '/daomu/img',
        '《盗墓笔记》是一部集合悬疑、冒险、奇幻元素的小说，讲述了几位年轻人探索古墓的惊险经历。',
        '978-0-14-303995-2', 300000, 18000),
       ('langya', 'haikan', '海宴', '琅琊榜', 96, '宫廷、权谋、江湖、智斗', '/langya/img',
        '《琅琊榜》是一部宫廷权谋小说，讲述了主角梅长苏在琅琊榜的帮助下，智斗朝堂、江湖的故事。',
        '978-0-670-03341-6', 350000, 25000),
       ('zhuixu', 'dongfang', '东方玉', '追虚', 93, '玄幻、修仙、奇幻', '/zhuixu/img',
        '《追虚》是一部玄幻小说，以修仙为主题，展现了主角在奇幻世界中的修炼历程和冒险。',
        '978-1-84195-862-1', 400000, 12000),
       ('wulinwaizhuan', 'zhengyong', '郑用', '武林外传', 90, '武林、江湖、侠客、喜剧', '/wulinwaizhuan/img',
        '《武林外传》是一部结合武侠和喜剧元素的小说，以轻松幽默的方式描绘了江湖中人的故事。',
        '978-1-59017-207-6', 280000, 9800);


drop table if exists `chapter`;
create table `chapter`
(
    `id`           int unsigned auto_increment comment '数据库主键',
    `book_id`      varchar(32)                     not null comment '图书id',
    `chapter_code` int                             not null comment '章节编码',
    `name`         varchar(255)                    not null comment '章节名称',
    `content_path` varchar(255) collate utf8mb4_unicode_ci not null comment '章节内容路径',
    `status`       tinyint                         not null default 0 check ( `status` = 0 or `status` = 1) comment '状态, 0:正常, 1:锁章',
    `create_time`  timestamp                       not null default current_timestamp comment '创建时间',
    `update_time`  timestamp                       not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (`id`),
    unique key idx_book_id (`book_id`),
    unique key idx_book_chapter (`book_id`,`chapter_code`)
)engine =InnoDB default charset =utf8mb4 collate =utf8mb4_unicode_ci row_format =dynamic comment '图书章节信息表';
insert into chapter (book_id, chapter_code, name, content)
values ('shuihuzhuan',1,'张天师祈禳瘟疫 洪太尉误走妖魔','/shuihuzhuan/1'),
       ('shuihuzhuan',2,'王教头私走延安府 九纹龙大闹史家村','/shuihuzhuan/2');

drop table if exists `author`;
create table author
(
    `id`            int unsigned auto_increment comment '数据库主键',
    `author_id`     varchar(32) collate utf8mb3_unicode_ci        not null comment '作者id',
    `identity_card` varchar(32) collate utf8mb3_unicode_ci unique not null comment '身份证号',
    `real_name`     varchar(32) collate utf8mb3_unicode_ci        not null comment '真实名字',
    `pseudonym`     varchar(32) collate utf8mb3_unicode_ci        not null comment '笔名',
    `nationality`   varchar(255) collate utf8mb3_unicode_ci       not null comment '国籍',
    `birthday`      date comment '出生日',
    `master_works`  text collate utf8mb3_unicode_ci comment '代表作',
    `introduction`  text collate utf8mb4_unicode_ci               not null comment '作者简介',
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

INSERT INTO category (category_id, name)
VALUES ('fiction', '小说');
INSERT INTO category (category_id, name)
VALUES ('non_fiction', '非小说');
INSERT INTO category (category_id, name)
VALUES ('science_fiction', '科幻小说');
INSERT INTO category (category_id, name)
VALUES ('fantasy', '奇幻小说');
INSERT INTO category (category_id, name)
VALUES ('mystery', '悬疑小说');
INSERT INTO category (category_id, name)
VALUES ('biography', '传记');
INSERT INTO category (category_id, name)
VALUES ('autobiography', '自传');
INSERT INTO category (category_id, name)
VALUES ('historical_fiction', '历史小说');
INSERT INTO category (category_id, name)
VALUES ('romance', '爱情小说');
INSERT INTO category (category_id, name)
VALUES ('thriller', '惊悚小说');
INSERT INTO category (category_id, name)
VALUES ('self_help', '自助书');
INSERT INTO category (category_id, name)
VALUES ('cookbooks', '烹饪书');
INSERT INTO category (category_id, name)
VALUES ('children_books', '儿童书籍');
INSERT INTO category (category_id, name)
VALUES ('young_adult', '青少年小说');
INSERT INTO category (category_id, name)
VALUES ('poetry', '诗歌');
INSERT INTO category (category_id, name)
VALUES ('travel', '旅行');
INSERT INTO category (category_id, name)
VALUES ('art', '艺术');
INSERT INTO category (category_id, name)
VALUES ('religion', '宗教');
INSERT INTO category (category_id, name)
VALUES ('science', '科学');
INSERT INTO category (category_id, name)
VALUES ('health', '健康');
INSERT INTO category (category_id, name)
VALUES ('business', '商业');
INSERT INTO category (category_id, name)
VALUES ('political', '政治');
INSERT INTO category (category_id, name)
VALUES ('technology', '技术');
INSERT INTO category (category_id, name)
VALUES ('education', '教育');
INSERT INTO category (category_id, name)
VALUES ('humor', '幽默');
INSERT INTO category (category_id, name)
VALUES ('sports', '运动');
INSERT INTO category (category_id, name)
VALUES ('music', '音乐');
INSERT INTO category (category_id, name)
VALUES ('drama', '戏剧');
INSERT INTO category (category_id, name)
VALUES ('adventure', '冒险');
INSERT INTO category (category_id, name)
VALUES ('psychology', '心理学');
INSERT INTO category (category_id, name)
VALUES ('philosophy', '哲学');
INSERT INTO category (category_id, name)
VALUES ('classics', '经典');
INSERT INTO category (category_id, name)
VALUES ('graphic_novels', '图形小说');
INSERT INTO category (category_id, name)
VALUES ('horror', '恐怖');
INSERT INTO category (category_id, name)
VALUES ('crime', '犯罪');
INSERT INTO category (category_id, name)
VALUES ('western', '西部');
INSERT INTO category (category_id, name)
VALUES ('urban_fantasy', '都市奇幻');
INSERT INTO category (category_id, name)
VALUES ('cyberpunk', '赛博朋克');
INSERT INTO category (category_id, name)
VALUES ('dystopian', '反乌托邦');
INSERT INTO category (category_id, name)
VALUES ('steampunk', '蒸汽朋克');
INSERT INTO category (category_id, name)
VALUES ('space_opera', '太空歌剧');
INSERT INTO category (category_id, name)
VALUES ('hard_science_fiction', '硬科幻');
INSERT INTO category (category_id, name)
VALUES ('military_science_fiction', '军事科幻');
INSERT INTO category (category_id, name)
VALUES ('alternate_history', '另类历史');
INSERT INTO category (category_id, name)
VALUES ('espionage', '间谍');
INSERT INTO category (category_id, name)
VALUES ('legal', '法律');
INSERT INTO category (category_id, name)
VALUES ('medical', '医学');
INSERT INTO category (category_id, name)
VALUES ('psychological_thriller', '心理惊悚');
INSERT INTO category (category_id, name)
VALUES ('true_crime', '真实犯罪');
INSERT INTO category (category_id, name)
VALUES ('war', '战争');
INSERT INTO category (category_id, name)
VALUES ('satire', '讽刺');
INSERT INTO category (category_id, name)
VALUES ('folklore', '民间传说');
INSERT INTO category (category_id, name)
VALUES ('mythology', '神话');
INSERT INTO category (category_id, name)
VALUES ('fairy_tales', '童话');
INSERT INTO category (category_id, name)
VALUES ('magical_realism', '魔幻现实主义');
INSERT INTO category (category_id, name)
VALUES ('post_apocalyptic', '末日后');
INSERT INTO category (category_id, name)
VALUES ('superhero', '超级英雄');
INSERT INTO category (category_id, name)
VALUES ('surrealism', '超现实主义');
INSERT INTO category (category_id, name)
VALUES ('gothic', '哥特');
INSERT INTO category (category_id, name)
VALUES ('vampire', '吸血鬼');
INSERT INTO category (category_id, name)
VALUES ('zombie', '僵尸');
INSERT INTO category (category_id, name)
VALUES ('werewolf', '狼人');
INSERT INTO category (category_id, name)
VALUES ('time_travel', '时间旅行');
INSERT INTO category (category_id, name)
VALUES ('paranormal', '超自然');
INSERT INTO category (category_id, name)
VALUES ('ecology', '生态学');
INSERT INTO category (category_id, name)
VALUES ('anthropology', '人类学');
INSERT INTO category (category_id, name)
VALUES ('astrology', '占星术');
INSERT INTO category (category_id, name)
VALUES ('astronomy', '天文学');
INSERT INTO category (category_id, name)
VALUES ('quantum_physics', '量子物理');
INSERT INTO category (category_id, name)
VALUES ('robotics', '机器人技术');
INSERT INTO category (category_id, name)
VALUES ('linguistics', '语言学');
INSERT INTO category (category_id, name)
VALUES ('archaeology', '考古学');
INSERT INTO category (category_id, name)
VALUES ('genetics', '遗传学');
INSERT INTO category (category_id, name)
VALUES ('neuroscience', '神经科学');
INSERT INTO category (category_id, name)
VALUES ('philanthropy', '慈善学');
INSERT INTO category (category_id, name)
VALUES ('urban_planning', '城市规划');
INSERT INTO category (category_id, name)
VALUES ('oceanography', '海洋学');
INSERT INTO category (category_id, name)
VALUES ('environmental_science', '环境科学');
INSERT INTO category (category_id, name)
VALUES ('meteorology', '气象学');
INSERT INTO category (category_id, name)
VALUES ('geology', '地质学');
INSERT INTO category (category_id, name)
VALUES ('sustainable_development', '可持续发展');
INSERT INTO category (category_id, name)
VALUES ('forestry', '林业');
INSERT INTO category (category_id, name)
VALUES ('horticulture', '园艺');
INSERT INTO category (category_id, name)
VALUES ('agriculture', '农业');

drop table if exists `book_category_relation`;
create table `book_category_relation`
(
    `id`          int unsigned auto_increment comment '数据库主键',
    `book_id`     varchar(32) not null comment '图书id',
    `category_id` varchar(64) not null comment '类别id',
    primary key (`id`),
    unique key `idx_book_category_id` (`book_id`,`category_id`)
)engine = InnoDB default charset =utf8mb4 comment '图书类别映射关系表';

INSERT INTO book_category_relation (book_id, category_id)
VALUES ('shuihuzhuan', 'fiction'),
       ('shuihuzhuan', 'classics'),
       ('shuihuzhuan', 'historical_fiction'),
       ('qingyunian', 'fiction'),
       ('qingyunian', 'historical_fiction'),
       ('qingyunian', 'adventure'),
       ('honglou', 'fiction'),
       ('honglou', 'classics'),
       ('honglou', 'romance'),
       ('honglou', 'drama'),
       ('sanguo', 'fiction'),
       ('sanguo', 'classics'),
       ('sanguo', 'historical_fiction'),
       ('sanguo', 'military_science_fiction'),
       ('yingxiong', 'fiction'),
       ('yingxiong', 'adventure'),
       ('xiyouji', 'fiction'),
       ('xiyouji', 'classics'),
       ('xiyouji', 'fantasy'),
       ('xiyouji', 'adventure'),
       ('daomu', 'fiction'),
       ('daomu', 'mystery'),
       ('daomu', 'adventure'),
       ('langya', 'fiction'),
       ('langya', 'historical_fiction'),
       ('langya', 'drama'),
       ('zhuixu', 'fiction'),
       ('zhuixu', 'fantasy'),
       ('wulinwaizhuan', 'fiction'),
       ('wulinwaizhuan', 'adventure'),
       ('wulinwaizhuan', 'humor');
