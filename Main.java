package rx;

import rx.functions.Func1;
import rx.scheduler.Schedulers;

/**
 * Created by srtianxia on 2016/12/3.
 */
public class Main {
    public static void main(String[] args) {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<String> subscriber) {
                System.out.println(Thread.currentThread().getName());
                subscriber.onNext("hello");
                subscriber.onCompleted();
            }
        }).map(new Func1<String, Integer>() {
            @Override
            public Integer call(String s) {
                return s.length();
            }
        }).subscribeOn(Schedulers.newThread()).
                subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onNext(Integer s) {
                        System.out.println(s);
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.print(t);
                    }

                    @Override
                    public void onCompleted() {

                    }
                });
    }
}
