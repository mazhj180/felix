package com.mazhj.common.quartz.trigger;

import org.quartz.Trigger;

/**
 * @author mazhj
 */
public interface TriggerProvider {

    /**
     * 声明触发器
     * @return 触发器集合
     */
    Trigger[] declare();

}
