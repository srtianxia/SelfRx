package rx.scheduler;

import rx.Subscription;
import rx.action.Action0;

/**
 * Created by srtianxia on 2016/12/3.
 */
public abstract class Scheduler {
    public abstract Worker createWorker();

    public abstract static class Worker implements Subscription {

        public abstract Subscription schedule(Action0 action);

    }
}
