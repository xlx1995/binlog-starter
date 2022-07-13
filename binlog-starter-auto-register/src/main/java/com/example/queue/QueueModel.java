package com.example.queue;

import com.example.annotation.RowListenerModel;
import com.example.event.CustomEvent;
import lombok.Data;

/**
 * @Author : xlx
 * @Description :
 * @Date: 2022/7/13
 */
@Data
public class QueueModel {

    private RowListenerModel rowListenerModel;
    private CustomEvent customEvent;

}
