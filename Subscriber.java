package rx;

/**
 * Created by srtianxia on 2016/12/3.
 */
public abstract class Subscriber<T> implements Observer<T>, Subscription {
    private boolean unSubscribed = false;
    private Subscription subscription;

    @Override
    public void unSubscribe() {
        if (!unSubscribed) {
            subscription = null;
            unSubscribed = true;
        }
    }

    public void add(Subscription s) {

    }

    @Override
    public boolean isUnSubscribed() {
        return unSubscribed;
    }
}