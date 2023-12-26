drop table if exists `topic`;
create table `topic`
(
    `topic_id` int unsigned auto_increment not null comment '话题id',
    `topic_name` varchar (255) not null comment '话题名称',
    `remark_count` int unsigned not null comment '评论条数',
    `img_uri` varchar (255) not null comment '封面图片uri',
    `creator` varchar (32) not null comment '创建者',
    `create_time` timestamp default current_timestamp comment '创建时间',
    primary key (`topic_id`)
) engine = InnoDB
  default charset = utf8mb4 comment '话题信息表';


drop table if exists `topic_remark`;
create table `topic_remark`
(
    `remark_id` int unsigned auto_increment not null comment '评论id',
    `root_remark_Id` int unsigned default null comment '根评论的id (值为null则是根评论)',
    `reply_remark_Id` int unsigned default null comment '回复的评论id (值为null则是根评论)',
    `topic_id` int unsigned not null comment '所属话题id',
    `user_id` varchar (32) collate utf8mb4_unicode_ci not null comment '用户id',
    `nick_name` varchar (20) collate utf8mb4_unicode_ci not null comment '用户评论时昵称',
    `head_img` varchar (255) collate utf8mb4_unicode_ci default null comment '用户头像',
    `content` text collate utf8mb4_unicode_ci comment '评论内容',
    `healthy_content` text collate utf8mb4_unicode_ci comment '脱敏内容',
    `support_count` int unsigned default 0 comment '点赞数',
    `create_time` timestamp default current_timestamp comment '创建时间',
    primary key (`remark_id`)
) engine = InnoDB
  default charset = utf8mb4 comment '话题评论信息表';
