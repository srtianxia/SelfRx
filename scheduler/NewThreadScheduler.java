package rx.scheduler;

/**
 * Created by srtianxia on 2016/12/3.
 */
public class NewThreadScheduler extends Scheduler {
    @Override
    public Worker createWorker() {
        return new NewThreadWorker();
    }
}
