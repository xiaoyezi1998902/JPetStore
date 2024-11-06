package persistence;


import domain.Log;

import java.util.ArrayList;

public interface LogDao
{
    public void InsertLog(Log log);

    public ArrayList<Log> FindLogByUserName(String userName);
}

