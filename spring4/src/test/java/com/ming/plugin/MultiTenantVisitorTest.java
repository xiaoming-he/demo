package com.ming.plugin;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MultiTenantVisitorTest {

    private ITableFieldCondition conditionDecision = new ITableFieldCondition() {
        @Override
        public boolean adjudge(String tableName) {
            return true;
        }

        @Override
        public String getFieldName() {
            return "dbId";
        }

        @Override
        public long getFieldValue() {
            return 888888;
        }
    };

    @Test
    public void simpleSQLSelectStatementTest() {
        String expectSql = "select * from user u where u.fid = ? and u.dbId = 888888";
        String sql = "select * from user u where u.fid = ?";
        assertEquals(expectSql, toSQLString(sql));
    }

    @Test
    public void joinSqlSelectStatementTest() {
        String expectSql = "select * from user u inner join employee e on u.fdbId = e.fdbId  where u.fid = ? and u.dbId = 888888 and e.dbId = 888888";
        String sql = "select * from user u  inner join employee e on u.fdbId = e.fdbId where u.fid = ?";
        assertEquals(expectSql, toSQLString(sql));
    }

    @Test
    public void subSqlSelectStatementTest() {
        String expectSql = "select * from ( select * from employee where dbId = 888888 )";
        String sql = "select * from ( select * from employee ) ";
        assertEquals(expectSql, toSQLString(sql));
    }

    @Test
    public void subAndJoinSqlSelectStatementTest() {
        String expectSql = "select * from user u inner join ( select * from employee where dbId = 888888 ) e on u.fdbId = e.fdbId  where u.dbId = 888888";
        String sql = "select * from user u inner join (select * from employee ) e on u.fdbId = e.fdbId";
        assertEquals(expectSql, toSQLString(sql));
    }

    @Test
    public void inSqlSelectStatementTest() {
        String expectSql = "select * from user where fid in ( select fid from employee where dbId = 888888 ) and dbId = 888888";
        String sql = "select * from user where fid in (select fid from employee)";
        assertEquals(expectSql, toSQLString(sql));
    }

    @Test
    public void unionSqlSelectStatementTest() {
        String expectSql = "select fid from user where dbId = 888888 union all select fid from employee where dbId = 888888";
        String sql = "select fid from user union all select fid from employee ";
        assertEquals(expectSql, toSQLString(sql));
    }

    @Test
    public void simpleSqlUpdateStatementTest() {
        String expectSql = "update user u set fid = ?, fname = ? where u.dbId = 888888";
        String sql = "update user u set fid = ? , fname = ?";
        assertEquals(expectSql, toSQLString(sql));
    }

    @Test
    public void whereSqlUpdateStatementTest() {
        String expectSql = "update user set fname = ? where fid = ? and dbId = 888888";
        String sql = "update user set fname = ? where fid = ?";
        assertEquals(expectSql, toSQLString(sql));
    }

    @Test
    public void inSqlUpdateStatementTest() {
        String expectSql = "update user u set fname = ? where fid in ( select fid from employee where dbId = 888888 ) and u.dbId = 888888";
        String sql = "update user u set fname = ? where fid in ( select fid from employee ) ";
        assertEquals(expectSql, toSQLString(sql));
    }

    @Test
    public void simpleDeleteStatementTest() {
        String expectSql = "delete from user where dbId = 888888";
        String sql = "delete from user";
        assertEquals(expectSql, toSQLString(sql));
    }

    @Test
    public void whereDeleteStatementTest() {
        String expectSql = "delete from user where fid = ? and dbId = 888888";
        String sql = "delete from user where fid = ?";
        assertEquals(expectSql, toSQLString(sql));
    }

    @Test
    public void inDeleteStatementTest() {
        String expectSql = "delete from user u where fid in ( select fid from employee where dbId = 888888 ) and u.dbId = 888888";
        String sql = "delete from user u where fid in (select fid from employee ) ";
        assertEquals(expectSql, toSQLString(sql));
    }

    @Test
    public void simpleInsertStatementTest() {
        String expectSql = "insert into user (fid, fname, dbId) values (?, ?, 888888)";
        String sql = "insert into user(fid, fname) values(?, ?)";
        assertEquals(expectSql, toSQLString(sql));
    }

    @Test
    public void selectInsertStatementTest() {
        String expectSql = "insert into user select fid from employee where dbId = 888888";
        String sql = "insert into user select fid from employee";
        assertEquals(expectSql, toSQLString(sql));
    }

    private String toSQLString(String sql) {
        MultiTenantVisitor visitor = new MultiTenantVisitor(conditionDecision);
        return visitor.doTranslate(sql);
    }
}