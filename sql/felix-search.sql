create table if not exists felix_search.search_record
(
    id          int unsigned auto_increment comment '数据库自增id'
    primary key,
    record_id   varchar(32) not null comment '记录的唯一id',
    content     text        not null comment '搜索频率较高的搜索内容',
    create_date date        not null comment '记录日期',
    constraint idx_record_id
    unique (record_id)
    );

create index idx_create_date
    on felix_search.search_record (create_date);