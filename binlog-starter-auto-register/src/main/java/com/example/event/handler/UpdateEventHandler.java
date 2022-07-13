package com.example.event.handler;

import com.example.config.BinLogConfig;
import com.example.config.MetaConfig;
import com.example.config.OperateTypeEnum;
import com.example.event.CustomEvent;
import com.example.event.FieldValue;
import com.example.event.UpdateData;
import com.example.util.EventUtil;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author : xlx
 * @Description :
 * @Date: 2022/7/12
 */
@Component
public class UpdateEventHandler extends AbstractEventHandler {

    @Override
    public void convertData(EventContext context) {
        UpdateRowsEventData updateRowsEventData = context.getEvent().getData();
        MetaConfig metaInfo = context.getMetaConfig();
        if (Objects.nonNull(metaInfo)) {
            List<CustomEvent> customEventList = new ArrayList<>();
            context.setCustomEventList(customEventList);
            List<Map.Entry<Serializable[], Serializable[]>> rows = updateRowsEventData.getRows();
            for (Map.Entry<Serializable[], Serializable[]> row : rows) {
                CustomEvent customEvent = new CustomEvent();
                Serializable[] before = row.getKey();
                Serializable[] after = row.getValue();
                UpdateData updateData = new UpdateData();
                updateData.setAfter(after);
                updateData.setBefore(before);
                customEvent.setDsName(context.getDataSourceConfig().getDsName());
                customEvent.setTableName(metaInfo.getTableName());
                customEvent.setOperation(OperateTypeEnum.UPDATE.getCode());
                List<FieldValue> fieldValues = EventUtil.convertUpdateField(updateData, metaInfo.getDsName(), metaInfo.getTableName(), metaInfo);
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
        UpdateRowsEventData rowsEventData = context.getEvent().getData();
        return rowsEventData.getTableId();
    }

}
