package com.ming.quartz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author ming_he
 * @date 2019/7/10 00:11
 */
//@Component
public class QuartzJobLogListener implements JobListener {
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    @QuartzDataSource
    private DataSource dataSource;
    private static final String INSERT_SQL = "insert into QRTZ_LOG(JOB_NAME, status, errMsg, createdate, modifytime) " +
            "values(?, ?, ?, now() ,now())";

    @Override
    public String getName() {
        return "QuartzJobLogListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        //logger.info(context.getJobDetail().getKey() + "jobToBeExecuted...");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {

    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        logger.info(context.getJobDetail().getKey() + "jobWasExecuted..." + context.getJobRunTime());
        int status = 1;
        String errMsg = null;
        if (jobException != null) {
            status = 0;
            errMsg = jobException.getMessage();
        }
        saveLog(context.getJobDetail().getKey().getName(), status, errMsg);
    }

    private void saveLog(String jobName, int status, String errorMsg) {
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement ps = connection.prepareStatement(INSERT_SQL);

            ps.setString(1, jobName);
            ps.setInt(2, status);
            ps.setString(3, errorMsg);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
