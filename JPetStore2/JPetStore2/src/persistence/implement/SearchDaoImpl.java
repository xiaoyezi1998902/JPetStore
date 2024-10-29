package persistence.implement;


import domain.Product;
import domain.Search;
import persistence.DBUtil;
import persistence.SearchDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SearchDaoImpl implements SearchDao {

    private static final String GET_SEARCH_LIST=
            "SELECT ITEMID,LISTPRICE,ATTR1 AS attribute1, I.PRODUCTID as \"product.productId\",NAME FROM ITEM I, PRODUCT P WHERE P.PRODUCTID = I.PRODUCTID AND I.PRODUCTID=?";
    private static final String ITEM_SEARCH_LIST=
            "select\n" +
                    "      I.ITEMID,\n" +
                    "      LISTPRICE,\n" +
                    "      UNITCOST,\n" +
                    "      SUPPLIER AS supplierId,\n" +
                    "      I.PRODUCTID AS \"product.productId\",\n" +
                    "      NAME AS \"product.name\",\n" +
                    "      DESCN AS \"product.description\",\n" +
                    "      CATEGORY AS \"product.categoryId\",\n" +
                    "      STATUS,\n" +
                    "      ATTR1 AS attribute1,\n" +
                    "      ATTR2 AS attribute2,\n" +
                    "      ATTR3 AS attribute3,\n" +
                    "      ATTR4 AS attribute4,\n" +
                    "      ATTR5 AS attribute5,\n" +
                    "      QTY AS quantity\n" +
                    "    from ITEM I, INVENTORY V, PRODUCT P\n" +
                    "    where P.PRODUCTID = I.PRODUCTID\n" +
                    "      and I.ITEMID = V.ITEMID\n" +
                    "      and I.ITEMID = ?";
    private static final String NAME_SEARCH_LIST=
            "SELECT\n" +
                    "      I.ITEMID,\n" +
                    "      LISTPRICE,\n" +
                    "      UNITCOST,\n" +
                    "      SUPPLIER AS supplierId,\n" +
                    "      I.PRODUCTID AS \"product.productId\",\n" +
                    "      NAME AS \"product.name\",\n" +
                    "      DESCN AS \"product.description\",\n" +
                    "      CATEGORY AS \"product.categoryId\",\n" +
                    "      STATUS,\n" +
                    "      ATTR1 AS attribute1,\n" +
                    "      ATTR2 AS attribute2,\n" +
                    "      ATTR3 AS attribute3,\n" +
                    "      ATTR4 AS attribute4,\n" +
                    "      ATTR5 AS attribute5\n" +
                    "    FROM ITEM I, PRODUCT P\n" +
                    "    WHERE P.PRODUCTID = I.PRODUCTID\n" +
                    "    AND P.NAME =?";

    @Override
    public List<Search> getSearchList(String var1) {
        List<Search> SearchList = new ArrayList<>();
        try {
            Connection connection= DBUtil.getConnection();
            PreparedStatement statement=connection.prepareStatement(GET_SEARCH_LIST);
            statement.setString(1,var1);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                Search search=new Search();
                search.setItemId(resultSet.getString(1));
                search.setListPrice(resultSet.getBigDecimal(2));
                search.setProductId(resultSet.getString(4));
                search.setAttribute1(resultSet.getString(3));
                search.setName(resultSet.getString(5));
                SearchList.add(search);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return SearchList;
    }

    @Override
    public List<Search> nameSearchList(String var1) {
        List<Search> SearchList = new ArrayList<>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(NAME_SEARCH_LIST);
            preparedStatement.setString(1,var1);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Search search=new Search();
                search.setItemId(resultSet.getString(1));
                search.setListPrice(resultSet.getBigDecimal(2));
                search.setUnitCost(resultSet.getBigDecimal(3));
                search.setSupplierId(resultSet.getInt(4));
                search.setProductId(resultSet.getString(5));
                Product product = new Product();
                search.setName(resultSet.getString(6));
                product.setDescription(resultSet.getString(7));
                product.setCategoryId(resultSet.getString(8));
                search.setProduct(product);
                search.setStatus(resultSet.getString(9));
                search.setAttribute1(resultSet.getString(10));
                search.setAttribute2(resultSet.getString(11));
                search.setAttribute3(resultSet.getString(12));
                search.setAttribute4(resultSet.getString(13));
                search.setAttribute5(resultSet.getString(14));
                SearchList.add(search);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return SearchList;
    }

    @Override
    public Search itemSearchList(String var1) {
        Search search=null;
            try {
                Connection connection = DBUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(ITEM_SEARCH_LIST);
                statement.setString(1, var1);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {

                    search = new Search();
                    search.setItemId(resultSet.getString(1));
                    search.setListPrice(resultSet.getBigDecimal(2));
                    search.setUnitCost(resultSet.getBigDecimal(3));
                    search.setSupplierId(resultSet.getInt(4));
                    Product product=new Product();
                    product.setProductId(resultSet.getString(5));
                    search.setName(resultSet.getString(6));
                    search.setDescription(resultSet.getString(7));
                    search.setCategoryId(resultSet.getString(8));
                    search.setProduct(product);
                    search.setStatus(resultSet.getString(9));
                    search.setAttribute1(resultSet.getString(10));
                    search.setAttribute2(resultSet.getString(11));
                    search.setAttribute3(resultSet.getString(12));
                    search.setAttribute4(resultSet.getString(13));
                    search.setAttribute5(resultSet.getString(14));
                    search.setQuantity(resultSet.getInt(15));
                    }
                    DBUtil.closeResultSet(resultSet);
                    DBUtil.closeStatement(statement);
                    DBUtil.closeConnection(connection);

                }catch(SQLException e){
                    e.printStackTrace();
                }

        return search;
    }


}
