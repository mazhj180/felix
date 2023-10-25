package com.mazhj.felix.quartz.eums;

/**
 * quartz 触发器 失火策略
 * @author mazhj
 */
public enum MisfireInstruction {
    /** 失火后立即执行一次 然后按原周期和原时间起点执行*/
    MISFIRE_FIRE_AND_PROCEED,
    /** 失火后立即执行一次 然后按当时的时间为起点按周期执行*/
    MISFIRE_IGNORE_MISFIRES,
    /** 失火后不做处理,然后按原周期和原时间起点执行*/
    MISFIRE_DO_NOTHING
}
