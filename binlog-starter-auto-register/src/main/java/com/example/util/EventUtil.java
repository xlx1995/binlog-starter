package com.example.util;

import com.example.config.BinLogConfig;
import com.example.config.ColumnConfig;
import com.example.config.MetaConfig;
import com.example.event.CommonData;
import com.example.event.FieldValue;
import com.example.event.UpdateData;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author : xlx
 * @Description :
 * @Date: 2022/7/12
 */
public class EventUtil {


    public static List<FieldValue> convertUpdateField(UpdateData updateData, String dsName, String tableName,MetaConfig metaInfo){
        List<FieldValue> fieldValues = new ArrayList<>();
        Object[] after = updateData.getAfter();
        Object[] before = updateData.getBefore();
        for (int i = 0; i < before.length; i++) {
            int index = i;
            FieldValue fieldValue = new FieldValue();
            ColumnConfig config = metaInfo.getColumnConfigs().stream().filter(columnConfig -> columnConfig.getIndex() == index)
                    .findFirst().orElse(null);
            if(Objects.nonNull(config)){
                fieldValue.setFieldName(config.getColumn());
                fieldValue.setBeforeValue(before[i]);
                fieldValue.setAfterValue(after[i]);
                fieldValues.add(fieldValue);
            }
        }
        return fieldValues;
    }

    public static List<FieldValue> convertCommonField(CommonData commonData, String dsName, String tableName,MetaConfig metaInfo){
        List<FieldValue> fieldValues = new ArrayList<>();
        Object[] data = commonData.getData();
        for (int i = 0; i < data.length; i++) {
            int index = i;
            FieldValue fieldValue = new FieldValue();
            ColumnConfig config = metaInfo.getColumnConfigs().stream().filter(columnConfig -> columnConfig.getIndex() == index)
                    .findFirst().orElse(null);
            if(Objects.nonNull(config)){
                fieldValue.setFieldName(config.getColumn());
                fieldValue.setAfterValue(data[i]);
                fieldValues.add(fieldValue);
            }
        }
        return fieldValues;
    }


}
