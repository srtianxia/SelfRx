package rx;

import rx.scheduler.Scheduler;

/**
 * Created by srtianxia on 2016/12/3.
 */
public class OperatorSubscribeOn<T> implements Observable.OnSubscribe<T> {
    private Observable<T> source;
    private Scheduler scheduler;

    public OperatorSubscribeOn(Observable<T> source, Scheduler scheduler) {
        this.source = source;
        this.scheduler = scheduler;
    }

    @Override
    public void call(Subscriber<T> subscriber) {
        Scheduler.Worker inner = scheduler.createWorker();
        inner.schedule(() -> {
            Subscriber<T> s = new Subscriber<T>() {
                @Override
                public void onNext(T t) {
                    subscriber.onNext(t);
                }

                @Override
                public void onError(Throwable t) {
                    subscriber.onError(t);
                }

                @Override
                public void onCompleted() {
                    subscriber.onCompleted();
                }
            };

            source.subscribe(s);
        });
    }

}
