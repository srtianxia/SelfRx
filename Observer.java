package rx;

/**
 * Created by srtianxia on 2016/12/3.
 */
public interface Observer<T> {
    void onNext(T t);

    void onError(Throwable t);

    void onCompleted();
}
