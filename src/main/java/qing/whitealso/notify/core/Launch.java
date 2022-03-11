package qing.whitealso.notify.core;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.client.RestTemplate;
import qing.whitealso.notify.email.EmailUtils;

/**
 * @author baiye
 * @since 2022/3/11 3:48 下午
 **/
public class Launch {

    private static final String roomNo = "71415";

    private static final String baseUrl = "https://www.douyu.com/betard/";

    private static boolean isSend = true;

    public static int notifyMessage() {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(baseUrl + roomNo, String.class);
        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONObject room = jsonObject.getJSONObject("room");
        return room.getInteger("show_status");
    }

    public static void sendMail() {
        if (isSend) {
            EmailUtils.sendMail("858261367@qq.com", "老大开播提醒", "老大开播了！", "");
        }
    }

    public static void updateStatus(boolean send) {
        isSend = send;
    }

}
