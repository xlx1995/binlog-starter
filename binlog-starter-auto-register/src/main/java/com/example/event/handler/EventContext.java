package com.example.event.handler;

import com.example.config.BinLogConfigProperties;
import com.example.config.MetaConfig;
import com.example.event.CustomEvent;
import com.github.shyiko.mysql.binlog.event.Event;
import lombok.Data;

import java.util.List;

/**
 * @Author : xlx
 * @Description :
 * @Date: 2022/7/12
 */
@Data
public class EventContext {

    // 消息体
    private Event event;
    // 数据源信息
    private BinLogConfigProperties.DataSourceConfig dataSourceConfig;
    // 自定义消息体
    private List<CustomEvent> customEventList;
    // 日志打印
    private Boolean logWarn;
    // 元数据信息
    private MetaConfig metaConfig;
    // 表id
    private Long tableId;
    // 到达服务器时间
    private Long arriveTime;


}
