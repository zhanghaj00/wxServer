/**
 * Create time
 */
package shop.haj.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Hao on .
 * description。
 * @since 1.9.0
 */
public class TimeUtil {
    private final static SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static Boolean compareToday(String timeStr){
        String now = dataFormat.format(new Date());
       if(getInterval(now,timeStr,3)>0){
           return  false;
       }else{
           return true;
       }
    }

    public static int getInterval(String beginStr, String endStr, int f) {
        int hours = 0;
        try {
            Date beginDate = dataFormat.parse(beginStr);
            Date endDate = dataFormat.parse(endStr);
            long millisecond = endDate.getTime() - beginDate.getTime(); // 日期相减得到日期差X(单位:毫秒)
            /**
             * Math.abs((int)(millisecond/1000)); 绝对值 1秒 = 1000毫秒
             * millisecond/1000 --> 秒 millisecond/1000*60 - > 分钟
             * millisecond/(1000*60*60) -- > 小时 millisecond/(1000*60*60*24) -->
             * 天
             * */
            switch (f) {
                case 0: // second
                    return (int) (millisecond / 1000);
                case 1: // minute
                    return (int) (millisecond / (1000 * 60));
                case 2: // hour
                    return (int) (millisecond / (1000 * 60 * 60));
                case 3: // day
                    return (int) (millisecond / (1000 * 60 * 60 * 24));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hours;
    }
}
