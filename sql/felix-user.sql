drop table if exists `user`;
create table user
(
    id           int unsigned auto_increment comment '数据库主键'
        primary key,
    user_id      varchar(32)                           not null comment '用户唯一id,也是用户登录账户名',
    user_pwd     varchar(32)                           not null comment '用户密码',
    nick_name    varchar(20)                           not null comment '用户昵称',
    phone_number char(11)                              null comment '用户手机号',
    email        varchar(255)                          null comment '用户邮箱',
    head_img_url varchar(255)                          null comment '用户头像',
    level        varchar(32) default 'reader'          null comment '账户等级',
    create_time  timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time  timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_ban       tinyint(1)  default 0                 null comment '是否被禁用',
    constraint idx_user_id
        unique (user_id)
)   engine = InnoDB
    default charset = utf8mb4
    collate = utf8mb4_unicode_ci
    comment '用户信息表' row_format = DYNAMIC;
insert into user (user_id, user_pwd, nick_name, phone_number, email, head_img_url, level)
values ('mazhj180', '96e79218965eb72c92a549dd5a330112', '巨山超力霸', '1323896224', 'mazhj180@163.com', '/head_img.png',
        'administrator'),
       ('zhangwuji', '96e79218965eb72c92a549dd5a330112', '明教教主', '13390277321', 'zhangwuji@163.com',
        '/head_img.png', 'writer'),
       ('zhaomin', '96e79218965eb72c92a549dd5a330112', '大都一枝花', '13390277322', 'zhaomin@163.com', '/head_img.png',
        'reader');

drop table if exists `bookshelf`;
create table `bookshelf`
(
    `id`      int unsigned auto_increment comment '数据库主键',
    `user_id` varchar(32) not null comment '用户id',
    `book_id` varchar(32) not null comment '图书id',
    `create_time` timestamp default current_timestamp comment '创建时间'
    primary key (`id`),
    unique key `idx_user_book_id` (`user_id`, `book_id`)
) engine = InnoDB
  default charset = utf8mb4
  collate = utf8mb4_unicode_ci comment '用户书架信息表';
insert into bookshelf (user_id, book_id)
values ('zhaomin', 'honglou'),
       ('zhaomin', 'daomu');

drop table if exists `author`;
create table `author`
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

drop table if exists `work`
create table work
(
    `author_id`    varchar(32)                            not null comment '作者id',
    `author_name`  varchar(32)                            not null comment '作者名称',
    `work_id`      varchar(32)                            not null comment '作品id'
        primary key,
    `work_name`    varchar(256)                           not null comment '作品名称',
    `tag`          varchar(32)                            not null comment '作品标签分类',
    `work_img`     varchar(256)                           null comment '作品封面',
    `work_url`     varchar(256)                           not null comment '存放路径',
    `introduction` text collate utf8mb3_unicode_ci        null comment '作品描述',
    `status`       varchar(32) collate utf8mb3_unicode_ci null comment '作品状态 : 未审核、审核成功、退回',
    `create_time`  timestamp default CURRENT_TIMESTAMP    null comment '创建时间'
)
    comment '作品信息表' row_format = DYNAMIC;