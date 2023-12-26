drop table if exists `user`;
create table `user`
(
    `id`           int unsigned auto_increment comment '数据库主键',
    `user_id`      varchar(32) not null comment '用户唯一id,也是用户登录账户名',
    `user_pwd`     varchar(32) not null comment '用户密码',
    `nick_name`    varchar(20) not null comment '用户昵称',
    `phone_number` char(11) comment '用户手机号',
    `email`        varchar(255) comment '用户邮箱',
    `head_img_url` varchar(255) comment '用户头像',
    `create_time`  timestamp   not null default current_timestamp comment '创建时间',
    `update_time`  timestamp   not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (`id`),
    unique key `idx_user_id` (`user_id`)
) engine = InnoDB
  default charset = utf8mb4
  collate = utf8mb4_unicode_ci
  row_format = dynamic comment '用户信息表';

drop table if exists `bookshelf`;
create table `bookshelf`
(
    `id`      int unsigned auto_increment comment '数据库主键',
    `user_id` varchar(32) not null comment '用户id',
    `book_id` varchar(32) not null comment '图书id',
    primary key (`id`),
    unique key `idx_user_book_id` (`user_id`, `book_id`)
) engine = InnoDB
  default charset = utf8mb4
  collate = utf8mb4_unicode_ci comment '用户书架信息表';

insert into user (user_id, user_pwd, nick_name, phone_number, email, head_img_url)
values ('mazhj180', '111111', '巨山超力霸', '1323896224', 'mazhj180@163.com', '/head_img.png');