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
        account.setBannerOption(resultSet.getBoolean("banneroption"));
        account.setBannerName(resultSet.getString("BANNERNAME"));

        return account;
    }

    public void insertAccount(Account account) {

    }

    public void insertProfile(Account account) {

    }

    public void insertSignon(Account account) {

        this.account = account;
    }

    public void updateAccount(Account account) {

        this.account = account;
    }

    public void updateProfile(Account account) {

    }

    public void updateSignon(Account account) {

    }
//    public static void main(String[] args) {
//        AccountDaoImpl accountDao = new AccountDaoImpl();
//        Account account = new Account();
//        account.setUsername("j2ee");
//        account.setPassword("j2ee");
//        Account account1 = accountDao.getAccountByUsernameAndPassword(account);
//        System.out.println(account1.getBannerName());
//
//    }

}
