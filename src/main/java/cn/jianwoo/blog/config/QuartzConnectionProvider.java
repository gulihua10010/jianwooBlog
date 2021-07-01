package cn.jianwoo.blog.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.quartz.SchedulerException;
import org.quartz.utils.ConnectionProvider;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author gulihua
 */
public class QuartzConnectionProvider implements ConnectionProvider {
    //JDBC驱动
    public String driver;
    //JDBC连接串
    public String url;
    //数据库用户名
    public String user;
    //数据库用户密码
    public String password;
    //数据库最大连接数
    public int maxConnections;
    //数据库SQL查询每次连接返回执行到连接池，以确保它仍然是有效的。
    public String validationQuery;
    private boolean validateOnCheckout;
    private int idleConnectionValidationSeconds;
    public String maxCachedStatementsPerConnection;
    private String discardIdleConnectionsSeconds;
    public static final int DEFAULT_DB_MAX_CONNECTIONS = 10;
    public static final int DEFAULT_DB_MAX_CACHED_STATEMENTS_PER_CONNECTION = 120;
    //Druid连接池
    private DruidDataSource datasource;

    @Override
    public Connection getConnection() throws SQLException {
        return datasource.getConnection();
    }


    @Override
    public void shutdown() throws SQLException {
        datasource.close();
    }


    @Override
    public void initialize() throws SQLException {
        if (this.url == null) {
            throw new SQLException("DBPool could not be created: DB URL cannot be null");
        }
        if (this.driver == null) {
            throw new SQLException("DBPool driver could not be created: DB driver class name cannot be null!");
        }
        if (this.maxConnections < 0) {
            throw new SQLException("DBPool maxConnectins could not be created: Max connections must be greater than zero!");
        }
        datasource = new DruidDataSource();
        try {
            datasource.setDriverClassName(this.driver);
        } catch (Exception e) {
            try {
                throw new SchedulerException("Problem setting driver class name on datasource: " + e.getMessage(), e);
            } catch (SchedulerException e1) {
            }
        }
        datasource.setUrl(this.url);
        datasource.setUsername(this.user);
        datasource.setPassword(this.password);
        datasource.setMaxActive(this.maxConnections);
        datasource.setMinIdle(1);
        datasource.setMaxWait(0);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(DEFAULT_DB_MAX_CONNECTIONS);
        if (this.validationQuery != null) {
            datasource.setValidationQuery(this.validationQuery);
            if (!this.validateOnCheckout)
                datasource.setTestOnReturn(true);
            else
                datasource.setTestOnBorrow(true);
            datasource.setValidationQueryTimeout(this.idleConnectionValidationSeconds);
        }
    }


    /**
     * getter and setter
     */
    public String getDriver() {
        return driver;
    }


    public void setDriver(String driver) {
        this.driver = driver;
    }


    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }


    public void setUser(String user) {
        this.user = user;
    }


    public int getMaxConnections() {
        return maxConnections;
    }


    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getValidationQuery() {
        return validationQuery;
    }


    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }


    public boolean isValidateOnCheckout() {
        return validateOnCheckout;
    }


    public void setValidateOnCheckout(boolean validateOnCheckout) {
        this.validateOnCheckout = validateOnCheckout;
    }


    public int getIdleConnectionValidationSeconds() {
        return idleConnectionValidationSeconds;
    }


    public void setIdleConnectionValidationSeconds(int idleConnectionValidationSeconds) {
        this.idleConnectionValidationSeconds = idleConnectionValidationSeconds;
    }


    public String getMaxCachedStatementsPerConnection() {
        return maxCachedStatementsPerConnection;
    }


    public void setMaxCachedStatementsPerConnection(String maxCachedStatementsPerConnection) {
        this.maxCachedStatementsPerConnection = maxCachedStatementsPerConnection;
    }


    public String getDiscardIdleConnectionsSeconds() {
        return discardIdleConnectionsSeconds;
    }


    public void setDiscardIdleConnectionsSeconds(String discardIdleConnectionsSeconds) {
        this.discardIdleConnectionsSeconds = discardIdleConnectionsSeconds;
    }


    public static int getDefaultDbMaxConnections() {
        return DEFAULT_DB_MAX_CONNECTIONS;
    }


    public static int getDefaultDbMaxCachedStatementsPerConnection() {
        return DEFAULT_DB_MAX_CACHED_STATEMENTS_PER_CONNECTION;
    }


    public DruidDataSource getDatasource() {
        return datasource;
    }


    public void setDatasource(DruidDataSource datasource) {
        this.datasource = datasource;
    }
}
