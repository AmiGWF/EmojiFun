package com.wd.rxn;

import android.widget.TextView;

import org.reactivestreams.Publisher;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Author : wudu
 * Time : 2019/3/10.
 * Tips :
 */
public class ObservableNote {

    public static void run() {
        observableCreate();
        observableDefer();
    }

    public static void observableCreate() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

            }

        });


        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> emitter) throws Exception {

            }
        }, BackpressureStrategy.BUFFER);


    }

    /**
     * 延迟
     */
    public static void observableDefer() {
        Observable.defer(new Callable<ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> call() throws Exception {
                return null;
            }
        });

        Flowable.defer(new Callable<Publisher<?>>() {
            @Override
            public Publisher<?> call() throws Exception {
                return null;
            }
        });
    }

    public static void observableEmpty() {
        Observable.empty();

        Observable.never();

        Observable.error(new Throwable());

        Observable.error(new Callable<Throwable>() {
            @Override
            public Throwable call() throws Exception {
                return null;
            }
        });

        Flowable.empty();
    }

    public static void observableFrom() {
        Observable.fromArray(1, 2, 3);

        Observable.fromFuture(new Future<String>() {
            @Override
            public boolean cancel(boolean b) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public String get() throws ExecutionException, InterruptedException {
                return null;
            }

            @Override
            public String get(long l, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
                return null;
            }
        });

        Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return null;
            }
        });

        Flowable.fromArray(1, 2, 3);
    }

    public static void observableInterval() {
        Observable.interval(1, TimeUnit.SECONDS);

        Observable.interval(1, 1, TimeUnit.SECONDS,Schedulers.io());

        Flowable.interval(10, TimeUnit.DAYS);
    }

    public static void observableJust() {
        Observable.just(1, 2, 3);

        Flowable.just(1);
    }

    public static void observableRange() {
        Observable.range(1, 10);

        Flowable.range(1, 10);
    }


    public static void observableRepeat() {
        Observable.just(1).repeat(1);

        Flowable.just(1).repeat(1);
    }

    public static void observableSrart() {
        Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return null;
            }
        }).startWith("");


        Flowable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return null;
            }
        }).startWith("");
    }

    public static void observable() {
        Observable.timer(10, TimeUnit.SECONDS);

        Flowable.timer(1, TimeUnit.SECONDS);
    }

    public static void observerTransform(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

            }
        }).buffer(1,9);


        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                return null;
            }
        });

        Observable.just(new Random()).flatMap(new Function<Random, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Random random) throws Exception {
                return Observable.just(random.nextInt()+"");
            }
        });

    }
}
