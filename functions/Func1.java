package rx.functions;
/**
 * Created by srtianxia on 2016/12/3.
 */
public interface Func1<T, R> extends Function {
    R call(T t);
}