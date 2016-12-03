package rx.scheduler;

import rx.Subscription;
import rx.action.Action0;

import java.util.concurrent.Future;

/**
 * Created by srtianxia on 2016/12/3.
 */
public class ScheduledAction implements Runnable, Subscription {
    private Action0 action;

    public ScheduledAction(Action0 action) {
        this.action = action;
    }


    @Override
    public void run() {
        action.call();
    }

    @Override
    public void unSubscribe() {

    }

    public void add(Future<?> future) {

    }

    @Override
    public boolean isUnSubscribed() {
        return false;
    }
}
