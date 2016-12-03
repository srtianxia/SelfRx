package rx;

import rx.functions.Func1;
import rx.operator.OnSubscribeMap;
import rx.scheduler.Scheduler;

/**
 * Created by srtianxia on 2016/12/3.
 */
public class Observable<T> {
    private OnSubscribe<T> onSubscribe;

    protected Observable(OnSubscribe<T> onSubscribe) {
        this.onSubscribe = onSubscribe;
    }

    public static <T> Observable<T> create(OnSubscribe<T> onSubscribe) {
        return new Observable<T>(onSubscribe);
    }

    public Subscription subscribe(Subscriber<T> subscriber) {
        try {
            onSubscribe.call(subscriber);
            return subscriber;
        } catch (Throwable t) {
            subscriber.onError(t);
            return UNSUBSCRIBED;
        }
    }


    public Observable<T> subscribeOn(Scheduler scheduler) {
        return create(new OperatorSubscribeOn<T>(this, scheduler));
    }

    public final <R> Observable<R> map(Func1<? super T, ? extends R> func) {
        return create(new OnSubscribeMap<T, R>(this, func));
    }


    public interface OnSubscribe<T> {
        //事件类唯一的接口方法就是call()
        void call(Subscriber<T> subscriber);
    }

    public static final Subscription UNSUBSCRIBED = new Subscription() {
        @Override
        public void unSubscribe() {

        }

        @Override
        public boolean isUnSubscribed() {
            return true;
        }
    };
}