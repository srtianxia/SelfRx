package rx;

/**
 * Created by srtianxia on 2016/12/3.
 */
public interface Subscription {
    void unSubscribe();

    boolean isUnSubscribed();
}