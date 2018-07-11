package com.ming.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.util.Properties;

@Intercepts(@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}))
public class MultiTenantPlugin implements Interceptor {

    public static final String Dialect_Prefix = "/*dialect*/";
    private static final String FIELD_DBID = "FDBID";

    ITableFieldCondition conditionDecision;


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
        // 分离代理对象链(由于目标类可能被多个插件拦截，从而形成多次代理，通过下面的两次循环
        // 可以分离出最原始的的目标类)
        while (metaStatementHandler.hasGetter("h")) {
            Object object = metaStatementHandler.getValue("h");
            metaStatementHandler = SystemMetaObject.forObject(object);
        }
        // 分离最后一个代理对象的目标类
        while (metaStatementHandler.hasGetter("target")) {
            Object object = metaStatementHandler.getValue("target");
            metaStatementHandler = SystemMetaObject.forObject(object);
        }
        BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
        String originalSql = boundSql.getSql();
        if (originalSql.startsWith(Dialect_Prefix)) {
            while (originalSql.startsWith(Dialect_Prefix)) {
                originalSql = originalSql.substring(Dialect_Prefix.length());
            }
            return invocation.proceed();
        }
        MultiTenantVisitor visitor = new MultiTenantVisitor(conditionDecision);
        metaStatementHandler.setValue("delegate.boundSql.sql", visitor.doTranslate(boundSql.getSql()));
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

        conditionDecision = new ITableFieldCondition() {
            @Override
            public boolean adjudge(String tableName) {
                return true;
            }

            @Override
            public String getFieldName() {
                return FIELD_DBID;
            }

            @Override
            public long getFieldValue() {
                return 888888;
            }
        };
    }
}
