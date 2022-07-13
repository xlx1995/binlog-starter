package com.example.annotation;

import lombok.Data;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : xlx
 * @Description :
 * @Date: 2022/7/12
 */
@Data
public class RowListenerModel {

    private String className;
    private String dsName;
    private List<String> tableNames;
    private String methodName;
    private Class<?> clazz;


}
