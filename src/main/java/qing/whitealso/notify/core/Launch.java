package qing.whitealso.notify.core;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.client.RestTemplate;
import qing.whitealso.notify.email.EmailUtils;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

/**
 * @author baiye
 * @since 2022/3/11 3:48 下午
 **/
public class Launch {

    private static final String roomNo = "71415";

    private static final String baseUrl = "https://www.douyu.com/betard/";

    private static boolean isSend = true;

    private static boolean isClose = false;

    private static Date startTime;

    private static Date endTime;

    public static int notifyMessage() {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(baseUrl + roomNo, String.class);
        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONObject room = jsonObject.getJSONObject("room");
        return room.getInteger("show_status");
    }

    public static void sendMail() {
        if (isSend) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = sdf.format(new Date());
            startTime = new Date();
            EmailUtils.sendMail("858261367@qq.com", "老大开播提醒", "老大开播了！,开播时间：" + format, "");
        }
    }

    public static void updateOpen(boolean send) {
        isSend = send;
    }

    public static void sendCloseMail() {
        if (isClose) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = sdf.format(new Date());
            endTime = new Date();
            Duration duration = Duration.between(startTime.toInstant(), endTime.toInstant());
            long seconds = duration.getSeconds();
            int h = Math.toIntExact(seconds / 3600);
            int hs = Math.toIntExact(seconds % 3600);
            int m = hs / 60;
            int s = hs % 60;
            String time = h + "小时" + m + "分钟" + s + "秒";
            EmailUtils.sendMail(
                    "858261367@qq.com",
                    "老大下播提醒",
                    "555~，老大下播了！" +
                            "\n下播播时间：" + format +
                            "\n总时长：" + time,
                    "");
        }
    }

    public static void updateClose(boolean close) {
        isClose = close;
    }

}
