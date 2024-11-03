package persistence.implement;

import domain.Account;
import persistence.AccountDao;
import persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDaoImpl implements AccountDao {
    private static final String GET_ACCOUNT_BY_USERNAME_AND_PASSWORD = "SELECT "+
            "SIGNON.USERNAME,"+
            "ACCOUNT.EMAIL,ACCOUNT.FIRSTNAME,ACCOUNT.LASTNAME,ACCOUNT.STATUS,"+
            "ACCOUNT.ADDR1 AS address1,ACCOUNT.ADDR2 AS address2,"+
            "ACCOUNT.CITY,ACCOUNT.STATE,ACCOUNT.ZIP,ACCOUNT.COUNTRY,ACCOUNT.PHONE,"+
            "PROFILE.LANGPREF AS languagePreference,PROFILE.FAVCATEGORY AS favouriteCategoryid,"+
            "PROFILE.MYLISTOPT AS listoption,PROFILE.BANNEROPT AS banneroption,"+
            "BANNERDATA.BANNERNAME "+
            "FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA "+
            "WHERE ACCOUNT.USERID=? AND SIGNON.PASSWORD =? "+
            "AND SIGNON.USERNAME=ACCOUNT.USERID "+
            "AND PROFILE.USERID=ACCOUNT.USERID "+
            "AND PROFILE.FAVCATEGORY=BANNERDATA.FAVCATEGORY";
    private static final String UPDATE_ACCOUNT= " UPDATE ACCOUNT SET\n" +
            "                         EMAIL = ?,\n" +
            "                         FIRSTNAME = ?,\n" +
            "                         LASTNAME = ?,\n" +
            "                         STATUS = ?,\n" +
            "                         ADDR1 = ?,\n" +
            "                         ADDR2 = ?,\n" +
            "                         CITY = ?,\n" +
            "                         STATE = ?,\n" +
            "                         ZIP = ?,\n" +
            "                         COUNTRY = ?,\n" +
            "                         PHONE = ?\n" +
            "      WHERE USERID = ?";
    private static final String INSERT_ACCOUNT=" INSERT INTO ACCOUNT\n" +
            "      (EMAIL, FIRSTNAME, LASTNAME, STATUS, ADDR1, ADDR2, CITY, STATE, ZIP, COUNTRY, PHONE, USERID)\n" +
            "    VALUES\n" +
            "      (?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_PROFILE="INSERT INTO PROFILE (LANGPREF, FAVCATEGORY,BANNEROPT,USERID)\n" +
            "    VALUES (?,?,?,?)";
    private static final String INSERT_SIGNON="INSERT INTO SIGNON (PASSWORD,USERNAME)\n" +
            "    VALUES (?,?)";
    private static final String UPDATE_PROFILE=" UPDATE PROFILE SET\n" +
            "      LANGPREF =?,\n" +
            "      FAVCATEGORY =?,\n" +
            "      BANNEROPT=?\n"+
            "    WHERE USERID =?";
    private static final String UPDEAT_SIGNON=" UPDATE SIGNON SET PASSWORD =?\n" +
            "    WHERE USERNAME =?";

    private Account account;

    public static String getGetAccountByUsername(String username) {
        return null;
    }

    @Override
    public Account getAccountByUsername(String username) {
        return null;
    }

    public Account getAccountByUsernameAndPassword(Account account) {
        Account accountResult = null;
        try {
            Connection connection= DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_USERNAME_AND_PASSWORD);
            preparedStatement.setString(1,account.getUsername());
            preparedStatement.setString(2,account.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                accountResult = this.resultSetToAccount(resultSet);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountResult;
    }
    private Account resultSetToAccount(ResultSet resultSet) throws SQLException {

        Account account = new Account();
        account.setUsername(resultSet.getString("username"));
        account.setEmail(resultSet.getString("email"));
        account.setFirstName(resultSet.getString("firstname"));
        account.setLastName(resultSet.getString("lastname"));
        account.setStatus(resultSet.getString("status"));
        account.setAddress1(resultSet.getString("address1")); // 别名处理
        account.setAddress2(resultSet.getString("address2")); // 别名处理
        account.setCity(resultSet.getString("CITY"));
        account.setState(resultSet.getString("STATE"));
        account.setZip(resultSet.getString("ZIP"));
        account.setCountry(resultSet.getString("COUNTRY"));
        account.setPhone(resultSet.getString("PHONE"));
        account.setLanguagePreference(resultSet.getString("languagePreference"));
        account.setFavouriteCategoryId(resultSet.getString("favouriteCategoryid"));
        account.setListOption(resultSet.getBoolean("listoption"));
        account.setBannerOption(resultSet.getInt("banneroption") == 1 ? true : false);
        account.setBannerName(resultSet.getString("BANNERNAME"));

        return account;
    }

    public void insertAccount(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT);
            preparedStatement.setString(1,account.getEmail());
            preparedStatement.setString(2,account.getFirstName());
            preparedStatement.setString(3,account.getLastName());
            preparedStatement.setString(4,account.getStatus());
            preparedStatement.setString(5,account.getAddress1());
            preparedStatement.setString(6,account.getAddress2());
            preparedStatement.setString(7,account.getCity());
            preparedStatement.setString(8,account.getState());
            preparedStatement.setString(9,account.getZip());
            preparedStatement.setString(10,account.getCountry());
            preparedStatement.setString(11,account.getPhone());
            preparedStatement.setString(12,account.getUsername());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertProfile(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROFILE);
            preparedStatement.setString(1,account.getLanguagePreference());
            preparedStatement.setString(2,account.getFavouriteCategoryId());
            preparedStatement.setInt(3,account.isBannerOption() ? 1 : 0);
            preparedStatement.setString(4,account.getUsername());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void insertSignon(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SIGNON);
            preparedStatement.setString(1,account.getPassword());
            preparedStatement.setString(2,account.getUsername());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAccount(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT);
            preparedStatement.setString(1,account.getEmail());
            preparedStatement.setString(2,account.getFirstName());
            preparedStatement.setString(3,account.getLastName());
            preparedStatement.setString(4,account.getStatus());
            preparedStatement.setString(5,account.getAddress1());
            preparedStatement.setString(6,account.getAddress2());
            preparedStatement.setString(7,account.getCity());
            preparedStatement.setString(8,account.getState());
            preparedStatement.setString(9,account.getZip());
            preparedStatement.setString(10,account.getCountry());
            preparedStatement.setString(11,account.getPhone());
            preparedStatement.setString(12,account.getUsername());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateProfile(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROFILE);
            preparedStatement.setString(1,account.getLanguagePreference());
            preparedStatement.setString(2,account.getFavouriteCategoryId());
            preparedStatement.setInt(3,account.isBannerOption() ? 1 : 0);
            preparedStatement.setString(4,account.getUsername());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateSignon(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDEAT_SIGNON);
            preparedStatement.setString(1,account.getPassword());
            preparedStatement.setString(2,account.getUsername());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
