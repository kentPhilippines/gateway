package com.pay.gateway.config.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>策略模式</p>
 * @author K
 *
 */
@Service
public class FactoryForStrategy {
    @Autowired
    Map<String, PayService> strategys = new ConcurrentHashMap<>(3);
    public PayService getStrategy(String component) throws Exception{
    	PayService strategy = strategys.get(component);
        if(strategy == null) {
            throw new RuntimeException("没有这个实现类，参数配置错误");
        }
        return strategy;
    }
}