package com.ming;

import com.alibaba.druid.pool.DruidDataSource;
import io.shardingsphere.api.config.MasterSlaveRuleConfiguration;
import io.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author ming_he
 * @date 2018/11/25 21:15
 */
public class ReadWriteSplittingTest {

    public static void main(String[] args) throws SQLException {
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        // 配置主库
        DruidDataSource masterDataSource = new DruidDataSource();
        masterDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        masterDataSource.setUrl("jdbc:mysql://192.168.56.104:3306/demo?useSSL=false&allowPublicKeyRetrieval=true");
        masterDataSource.setUsername("ming");
        masterDataSource.setPassword("Hm123!@#");
        dataSourceMap.put("ds_master", masterDataSource);

        // 配置第一个从库
        DruidDataSource slaveDataSource1 = new DruidDataSource();
        slaveDataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
        slaveDataSource1.setUrl("jdbc:mysql://192.168.56.102:3306/demo?useSSL=false&allowPublicKeyRetrieval=true");
        slaveDataSource1.setUsername("ming");
        slaveDataSource1.setPassword("Hm123!@#");
        dataSourceMap.put("ds_slave0", slaveDataSource1);

        // 配置读写分离规则
        MasterSlaveRuleConfiguration masterSlaveRuleConfig = new MasterSlaveRuleConfiguration("ds_master_slave", "ds_master", Arrays.asList("ds_slave0"));

        // 获取数据源对象
        DataSource dataSource = MasterSlaveDataSourceFactory.createDataSource(dataSourceMap, masterSlaveRuleConfig, new HashMap<String, Object>(), new Properties());

        String sql = "select `id`, `username` from t_user";
        Connection conn = dataSource.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        try (ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                System.out.println(rs.getInt("id"));
                System.out.println(rs.getString("username"));
            }
        }
    }
}
