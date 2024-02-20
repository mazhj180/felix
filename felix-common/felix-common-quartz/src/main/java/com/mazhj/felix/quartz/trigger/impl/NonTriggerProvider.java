package com.mazhj.felix.quartz.trigger.impl;

import com.mazhj.felix.quartz.trigger.TriggerProvider;
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
