package domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log
{
    private int id; //mysql自增主键

    private Date logTime; //日志记录的时间

    private String userName; //用户名

    private String title; //日志标题

    private String content; //日志详细内容

    public Log()
    {

    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getLogTime()
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(logTime);
    }

    public void setLogTime(Date logTime)
    {
        if (logTime == null)
        {
            this.logTime = new Date();
        } else
        {
            this.logTime = logTime;
        }
    }

    public void setLogTime(String logTime) throws ParseException
    {
        if (logTime == null)
        {
            this.logTime = new Date();
        } else
        {
            this.logTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(logTime);
        }
    }

}
