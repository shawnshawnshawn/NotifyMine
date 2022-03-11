package qing.whitealso.notify.core;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author baiye
 * @since 2022/3/11 4:29 下午
 **/
@Component
public class Task {

    @Scheduled(cron = "0 0/1 * * * ?")
    public void notifyMsg() {
        int status = Launch.notifyMessage();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(new Date());
        System.out.println("当前开播状态：" + (status == 2 ? "未开播" : "开播中") + ", 当前时间：" + format);
        if (status != 2) {
            Launch.sendMail();
            Launch.updateStatus(false);
        } else {
            Launch.updateStatus(true);
        }
    }
}
