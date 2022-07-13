package com.example.config;

import lombok.Data;

import java.util.List;

/**
 * @Author : xlx
 * @Description :
 * @Date: 2022/7/11
 */
@Data
public class MetaConfig {

    private List<ColumnConfig> columnConfigs;
    private String dsName;
    private String tableName;

}
