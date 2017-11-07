package com.hello.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
// 实现序列化接口，让JVM可以序列化实例
public class BaseEntity implements Serializable {

    // 统一使用Apache的commons-lang包中的方法重写toString方法
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
