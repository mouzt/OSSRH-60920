package com.mzt.toolkit.beans;

import lombok.Builder;
import lombok.Data;

/**
 * @author muzhantong
 * create on 2020/4/29 3:27 下午
 */
@Data
@Builder
public class LogRecordOps {
    private String successLogTemplate;

    private String failLogTemplate;

    private String operator;

    private String operatorId;

    private String bizKey;
    private String bizNo;

    private String category;
}
