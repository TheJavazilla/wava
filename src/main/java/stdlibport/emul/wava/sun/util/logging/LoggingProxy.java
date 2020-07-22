package wava.sun.util.logging;

public interface LoggingProxy {

    public Object getLogger(String name);

    public Object getLevel(Object logger);

    public void setLevel(Object logger, Object newLevel);

    public boolean isLoggable(Object logger, Object level);

    public void log(Object logger, Object level, String msg);

    public void log(Object logger, Object level, String msg, Throwable t);

    public void log(Object logger, Object level, String msg, Object... params);

    public java.util.List<String> getLoggerNames();

    public String getLoggerLevel(String loggerName);

    public void setLoggerLevel(String loggerName, String levelName);

    public String getParentLoggerName(String loggerName);

    public Object parseLevel(String levelName);

    public String getLevelName(Object level);

    public int getLevelValue(Object level);

    public String getProperty(String key);

}