package com.mazhj.common.core.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mazhj
 */
public class Convert {

    public static <T,S> List<T> to(List<S> sources,Class<T> targetClass) {
        try {
            List<T> output  = new ArrayList<>();
            for (S source : sources) {
                T target = targetClass.getDeclaredConstructor().newInstance();
                BeanUtils.copyProperties(source,target);
                output.add(target);
            }

            return output;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T,S> T to(S source,Class<T> targetClass)  {
        try {
            T target = targetClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source,target);
            return target;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
