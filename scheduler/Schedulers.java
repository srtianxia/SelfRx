package rx.scheduler;

/**
 * Created by srtianxia on 2016/12/3.
 */
public class Schedulers {
    public static Scheduler newThread() {
        return new NewThreadScheduler();
    }
}
