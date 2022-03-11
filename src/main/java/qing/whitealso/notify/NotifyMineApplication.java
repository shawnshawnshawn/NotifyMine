package qing.whitealso.notify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import qing.whitealso.notify.core.Launch;

/**
 * @author baiye
 * @since 2022/3/11 3:47 下午
 **/
@EnableScheduling
@SpringBootApplication
public class NotifyMineApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotifyMineApplication.class, args);
        Launch.notifyMessage();
    }
}
