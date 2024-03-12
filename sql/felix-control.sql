drop table if exists dynamic_components;
create table dynamic_components
(
    id             int unsigned auto_increment comment '数据库组件'
        primary key,
    component_id   varchar(32)                         not null comment '组件id',
    component_name varchar(32)                         not null comment '组件名称',
    content        text                                not null comment '组件内容',
    create_time    timestamp default CURRENT_TIMESTAMP null comment '创建日期',
    update_time    timestamp default CURRENT_TIMESTAMP null comment '修改日期'
)
    comment '动态组件表' collate = utf8mb4_unicode_ci
                         row_format = DYNAMIC;

insert into dynamic_components (component_id, component_name, content)
VALUES
    ('creator-center-great-work','优秀作品','/greate-works/19a98e88fde961700fb8fa0d7615271c~tplv-noop.png'),
    ('creator-center-great-work','优秀作品','/greate-works/778462743ec05f5f4cf3efab625330cd.jpeg'),
    ('creator-center-great-work','优秀作品','/greate-works/7dbfd51fa2ffe7ba4e7388d917920ccd~tplv-noop (1).png'),
    ('creator-center-great-work','优秀作品','/greate-works/89e6cdaa370f2ca185762d00d7b22cdc.png'),
    ('creator-center-great-work','优秀作品','/greate-works/b971a46023631e2d41768fe98365d41b~tplv-noop.png'),
    ('creator-center-great-work','优秀作品','/greate-works/d7e87ebd3504cc1ea059296487f6c753.jpeg'),
    ('creator-center-great-work','优秀作品','/greate-works/1922b7d9fe214b01790cfa7f4ce482fa.jpeg'),
    ('creator-center-great-work','优秀作品','/greate-works/3a43868bd240290f19cb72daa06f25e0~tplv-resize_225_0.jpeg'),
    ('creator-center-great-work','优秀作品','/greate-works/ce57dd2860fe35275985a16aa2378711~tplv-resize_225_0.jpeg'),
    ('creator-center-great-work','优秀作品','/greate-works/e483c826fdf870dc289a31543b7ff334~tplv-resize_225_0.jpeg');