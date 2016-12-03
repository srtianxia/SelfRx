package rx.operator;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by srtianxia on 2016/12/3.
 */
public final class OnSubscribeMap<T, R> implements Observable.OnSubscribe<R> {
    Observable<T> source;

    final Func1<? super T, ? extends R> transformer;

    public OnSubscribeMap(Observable<T> source, Func1<? super T, ? extends R> transformer) {
        this.source = source;
        this.transformer = transformer;
    }

    @Override
    public void call(Subscriber<R> subscriber) {
        MapSubscriber<T, R> parent = new MapSubscriber<T, R>(subscriber, transformer);
        //在rxJava中也是为了方便一起取消订阅，这里为空实现
        subscriber.add(parent);
        source.subscribe(parent);
    }


    static final class MapSubscriber<T, R> extends Subscriber<T> {
        final Subscriber<? super R> actual;

        final Func1<? super T, ? extends R> mapper;


        public MapSubscriber(Subscriber<? super R> actual, Func1<? super T, ? extends R> mapper) {
            this.actual = actual;
            this.mapper = mapper;
        }


        @Override
        public void onNext(T t) {
            //真正的变换逻辑
            R result;
            try {
                result = mapper.call(t);
            } catch (Throwable ex) {
                actual.onError(ex);
                return;
            }
            actual.onNext(result);
        }

        @Override
        public void onError(Throwable t) {
            actual.onError(t);
        }

        @Override
        public void onCompleted() {
            actual.onCompleted();
        }
    }
}
