package persistence;


import domain.Search;

import java.util.List;

public interface SearchDao {
    List<Search> getSearchList(String var1);

    List<Search> nameSearchList(String var1);

    Search itemSearchList(String var1);
}
