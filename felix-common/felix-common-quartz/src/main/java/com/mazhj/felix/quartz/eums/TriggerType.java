package com.mazhj.felix.quartz.eums;

/**
 * quartz 触发器类型
 * @author mazhj
 */
public enum TriggerType {
    /** 日历规则触发器*/
    CALENDAR_INTERVAL,
    /** 每日时间间隔的任务触发器*/
    DAILY_TIME_INTERVAL,
    /** Cron表达式触发器*/
    CRON,
    /** 简单触发器*/
    SIMPLE,
}
