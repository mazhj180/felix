package com.mazhj.common.quartz.trigger.impl;

import com.mazhj.common.quartz.trigger.TriggerProvider;
import org.quartz.Trigger;

/**
 * @author mazhj
 */
public class NonTriggerProvider implements TriggerProvider {
    @Override
    public Trigger[] declare() {
        return new Trigger[0];
    }
}
