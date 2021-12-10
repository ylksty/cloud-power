package com.bjpowernode.execute;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 线程执行结果类
 *
 * @param <V>
 */
public class AsyncResult<V> implements Future<V> {

    //成员变量
    private final V value;

    /**
     * 扩展一个构造方法
     *
     * @param value
     */
    public AsyncResult(V value) {
        this.value = value;
    }

    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    public boolean isCancelled() {
        return false;
    }

    public boolean isDone() {
        return true;
    }

    public V get() {
        return this.value;
    }

    public V get(long timeout, TimeUnit unit) {
        return this.value;
    }
}
