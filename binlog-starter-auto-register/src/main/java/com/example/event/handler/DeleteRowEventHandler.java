package com.example.event.handler;

import com.example.config.MetaConfig;
import com.example.config.OperateTypeEnum;
import com.example.event.CommonData;
import com.example.event.CustomEvent;
import com.example.event.FieldValue;
import com.example.util.EventUtil;
import com.github.shyiko.mysql.binlog.event.DeleteRowsEventData;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author : xlx
 * @Description :
 * @Date: 2022/7/12
 */
@Component
public class DeleteRowEventHandler extends AbstractEventHandler {
    @Override
    public void convertData(EventContext context) {
        DeleteRowsEventData rowsEventData = context.getEvent().getData();
        MetaConfig metaInfo = context.getMetaConfig();
        if (Objects.nonNull(metaInfo)) {
            List<CustomEvent> customEventList = new ArrayList<>();
            context.setCustomEventList(customEventList);
            List<Serializable[]> rows = rowsEventData.getRows();
            for (Serializable[] row : rows) {
                CustomEvent customEvent = new CustomEvent();
                CommonData commonData = new CommonData();
                commonData.setData(row);
                customEvent.setDsName(context.getDataSourceConfig().getDsName());
                customEvent.setTableName(metaInfo.getTableName());
                customEvent.setOperation(OperateTypeEnum.DELETE.getCode());
                List<FieldValue> fieldValues = EventUtil.convertCommonField(commonData, metaInfo.getDsName(), metaInfo.getTableName(), metaInfo);
                if (CollectionUtils.isEmpty(fieldValues)) {
                    continue;
                }
                customEvent.setFieldValueList(fieldValues);
                customEventList.add(customEvent);
            }
        }
    }

    @Override
    public Long getTableId(EventContext context) {
        DeleteRowsEventData rowsEventData = context.getEvent().getData();
        return rowsEventData.getTableId();
    }
}
