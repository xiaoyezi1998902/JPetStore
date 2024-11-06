package persistence.implement;


import domain.Log;
import persistence.DBUtil;
import persistence.LogDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class LogDaoImpl implements LogDao
{
    private static final String INSERT_LOG = "INSERT INTO log (logTime, userName, title, content) VALUES (?,?,?,?)";

    private static final String FIND_LOG_BY_USERNAME = "SElECT * FROM log WHERE userName = ?";

    @Override
    public void InsertLog(Log log)
    {
        try
        {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOG);
            preparedStatement.setString(1, log.getLogTime());
            preparedStatement.setString(2, log.getUserName());
            preparedStatement.setString(3, log.getTitle());
            preparedStatement.setString(4, log.getContent());
            int rows = preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Log> FindLogByUserName(String userName)
    {
        ArrayList<Log> logs = new ArrayList<>();
        try
        {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_LOG_BY_USERNAME);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                Log log = new Log();
                log.setId(resultSet.getInt("id"));
                log.setLogTime(resultSet.getString("logTime"));
                log.setUserName(resultSet.getString("userName"));
                log.setTitle(resultSet.getString("title"));
                log.setContent(resultSet.getString("content"));
                logs.add(log);
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closeConnection(connection);
        }
        catch (SQLException | ParseException e)
        {
            e.printStackTrace();
        }
        return logs;
    }
}
