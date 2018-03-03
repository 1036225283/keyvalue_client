package _1036225283.com.keyValue.client;


import com._1036225283.util.self.log.LogManager;

/**
 * log record
 * Created by xws on 7/26/17.
 */
public class Log {

    static LogManager log = LogManager.getInstance();

    //日志记录
    public static void info(String info, Object object) {
        log.dateInfo(object, info);
    }
}
