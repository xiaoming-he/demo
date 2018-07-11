package com.ming.plugin;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.*;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.dialect.oracle.ast.stmt.*;
import com.alibaba.druid.sql.dialect.oracle.visitor.OracleASTVisitorAdapter;
import com.alibaba.druid.util.JdbcConstants;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Stack;

public class MultiTenantVisitor extends OracleASTVisitorAdapter {

    private SQLStatement sqlStatement;

    private ITableFieldCondition conditionDecision;

    private Stack<SQLSelectQueryBlock> stacks = new Stack<>();

    private static SQLUtils.FormatOption DEFAULT_FORMAT_OPTION = new SQLUtils.FormatOption(false, false);


    public MultiTenantVisitor(ITableFieldCondition conditionDecision) {
        this.conditionDecision = conditionDecision;
    }

    @Override
    public boolean visit(SQLSelectStatement x) {
        setSqlStatement(x);
        return true;
    }

    @Override
    public boolean visit(OracleSelectQueryBlock x) {
        stacks.push(x);
        return true;
    }

    @Override
    public void endVisit(OracleSelectQueryBlock x) {
        stacks.pop();
    }

    @Override
    public boolean visit(OracleSelectTableReference x) {
        if (!stacks.empty()) {
            stacks.peek().addCondition(newEqualityCondition(x.getName().getSimpleName(), x.getAlias()));
        }
        return true;
    }

    @Override
    public boolean visit(OracleUpdateStatement x) {
        setSqlStatement(x);
        x.addCondition(newEqualityCondition(x.getTableName().getSimpleName(), x.getTableSource().getAlias()));
        return true;
    }

    @Override
    public boolean visit(OracleDeleteStatement x) {
        setSqlStatement(x);
        x.addCondition(newEqualityCondition(x.getTableName().getSimpleName(), x.getTableSource().getAlias()));
        return true;
    }

    @Override
    public boolean visit(OracleInsertStatement x) {
        setSqlStatement(x);
        newInsertStatement(x);
        return true;
    }

    public SQLStatement getSqlStatement() {
        return sqlStatement;
    }

    private void setSqlStatement(SQLStatement sqlStatement) {
        this.sqlStatement = sqlStatement;
    }

    private SQLExpr newEqualityCondition(String tableName, String tableAlias) {
        String fieldName = conditionDecision.getFieldName();
        if (!conditionDecision.adjudge(tableName)) return null;
        SQLExpr left = StringUtils.isBlank(tableAlias) ? new SQLIdentifierExpr(fieldName) :
                new SQLPropertyExpr(new SQLIdentifierExpr(tableAlias), fieldName);
        return new SQLBinaryOpExpr(left, new SQLIntegerExpr(conditionDecision.getFieldValue()), SQLBinaryOperator.Equality);
    }

    private void newInsertStatement(OracleInsertStatement x) {
        SQLIdentifierExpr fieldName = new SQLIdentifierExpr(conditionDecision.getFieldName());
        List<SQLExpr> columns = x.getColumns();
        if (!columns.isEmpty() && !columns.contains(fieldName)) {
            x.addColumn(fieldName);
            x.getValues().addValue(new SQLIntegerExpr(conditionDecision.getFieldValue()));
        }
    }

    public String doTranslate(String sql) {
        List<SQLStatement> statementList = SQLUtils.parseStatements(sql, JdbcConstants.ORACLE);
        statementList.stream().forEach(sqlStatement -> sqlStatement.accept(this));
        return SQLUtils.toSQLString(getSqlStatement(), JdbcConstants.ORACLE, DEFAULT_FORMAT_OPTION);
    }
}
