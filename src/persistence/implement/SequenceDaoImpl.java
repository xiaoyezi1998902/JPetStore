package persistence.implement;

import domain.Sequence;
import persistence.DBUtil;
import persistence.SequenceDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SequenceDaoImpl implements SequenceDao {
    public static final String GET_SEQUENCE = "SELECT name, nextid\n" +
            "    FROM SEQUENCE\n" +
            "    WHERE NAME = ?";
    public static final String UPDATE_SEQUENCE = "UPDATE SEQUENCE\n" +
            "    SET NEXTID = ?\n" +
            "    WHERE NAME = ?";

    @Override
    public Sequence getSequence(Sequence var1) {
        Sequence sequence = null;

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_SEQUENCE);
            preparedStatement.setString(1, var1.getName());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                sequence = new Sequence();
                sequence.setName(resultSet.getString(1));
                sequence.setNextId(resultSet.getInt(2));
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sequence;
    }

    @Override
    public void updateSequence(Sequence var1) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SEQUENCE);
            preparedStatement.setInt(1, var1.getNextId());
            preparedStatement.setString(2, var1.getName());
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
