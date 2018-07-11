package com.ming.plugin;

public interface ITableFieldCondition {

    /**
     * 判断<code>tableName</code>是不是需要添加过滤条件
     *
     * @param tableName
     * @return true需要添加，false不需要添加
     */
    boolean adjudge(String tableName);

    String getFieldName();

    long getFieldValue();
}
