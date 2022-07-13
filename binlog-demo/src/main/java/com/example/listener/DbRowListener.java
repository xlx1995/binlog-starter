package com.example.listener;

import com.alibaba.fastjson.JSON;
import com.example.annotation.RowListener;
import com.example.event.CustomEvent;
import org.springframework.stereotype.Component;

@Component
public class DbRowListener {

    @RowListener(tableNames = {"card"},dsName = "test")
    public void execute(String event){
        CustomEvent customEvent = JSON.parseObject(event, CustomEvent.class);
        System.out.println(JSON.toJSONString(customEvent));
    }



}
