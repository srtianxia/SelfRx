package rx.scheduler;

import org.jetbrains.annotations.NotNull;
import rx.Subscription;
import rx.action.Action0;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

/**
 * Created by srtianxia on 2016/12/3.
 */
public class NewThreadWorker extends Scheduler.Worker implements Subscription {

    private ScheduledExecutorService executor;

    public NewThreadWorker() {
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1, new ThreadFactory() {
            @Override
            public Thread newThread(@NotNull Runnable r) {
                Thread thread = new Thread(r, "newThread");
                thread.setDaemon(false);
                return thread;
            }
        });
        this.executor = exec;
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public boolean isUnSubscribed() {
        return false;
    }

    @Override
    public Subscription schedule(Action0 action) {
        ScheduledAction runAction = new ScheduledAction(action);
        Future<?> f = executor.submit(runAction);
        //add是为了取消订阅，这里只是一个空实现
        runAction.add(f);

        /*这里线程池的关闭应该是unSubscribe的时候做的工作，
          这里只是为了说明原理，2秒后就自动关闭了，也证明了
          在实际开发中应该及时unSubscribe，避免内存泄漏*/

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        f.cancel(true);
        executor.shutdownNow();

        return runAction;
    }
}
