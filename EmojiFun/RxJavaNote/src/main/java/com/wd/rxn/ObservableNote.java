package com.wd.rxn;

import android.util.Log;

import org.reactivestreams.Subscription;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Author : wudu
 * Time : 2019/3/10.
 * Tips :
 */
public class ObservableNote {
    private static String TAG = "wddd";

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
    static Integer a = 0;

    public static void observableDefer() {
        Observable observable = Observable.defer(new Callable<ObservableSource<?>>() {
            @Override
            public ObservableSource<?> call() throws Exception {
                return Observable.just(a);
            }
        });
        a = 10;
        observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer o) throws Exception {
                Log.d(TAG, "数据 : " + o);
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

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        Observable.fromIterable(list).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "数据 : " + integer);
            }
        });


    }

    public static void observableInterval() {
        Observable.intervalRange(2, 10, 2, 1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(TAG, "下游收到数据 : " + aLong);
                    }
                });

    }

    public static void observableJust() {
        Observable.just(1, 2, 3);

        Flowable.just(1);
    }

    public static void observableRange() {
        Observable.range(2, 10).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "下游收到数据 : " + integer);
            }
        });


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
        Observable.timer(3, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.d(TAG, "onNext 收到数据 ： " + aLong);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete------");
                    }
                });
    }

    public static void observerTransform() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

            }
        }).buffer(1, 9);


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
                return Observable.just(random.nextInt() + "");
            }
        });
    }

    public static void o1() {
        Observable
                .just(1, 2, 3, 4, 5, 6, 7, 8)
                .buffer(2, 3)
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        Log.d(TAG, " 缓存区里的事件数量 = " + integers.size());
                        for (Integer value : integers) {
                            Log.d(TAG, " 接收事件 = " + value);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, " 事件发送结束---");
                    }
                });
    }

    public static void o2() {
        Observable.zip(Observable.just(1, 2, 3), Observable.just("A", "B", "C", "D"), new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                Log.d(TAG, " apply 收到事件 integer = " + integer + ",  s = " + s);
                return integer + s;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, " accept 收到合并后的事件 s = " + s);
            }
        });

    }


    public static void o3() {
        Observable o1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "o1 发送事件 1");
                emitter.onNext(1);
                Log.d(TAG, "o1 发送事件 2");
                emitter.onNext(2);
                Log.d(TAG, "o1 发送事件 3");
                emitter.onNext(3);
                Log.d(TAG, "o1 发送事件 onComplete");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());

        Observable o2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.d(TAG, "o2 发送事件 A");
                emitter.onNext("A");
                Log.d(TAG, "o2 发送事件 B");
                emitter.onNext("B");
                Log.d(TAG, "o2 发送事件 C");
                emitter.onNext("C");
                Log.d(TAG, "o2 发送事件 D");
                emitter.onNext("D");
                Log.d(TAG, "o2 发送事件 onComplete");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.newThread());

        Observable.zip(o1, o2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                Log.d(TAG, "zip 开始进行合并-------------");
                return integer + s;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "下游收到 zip 合并结果  s = " + s);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "下游结束-------------");
            }
        });
    }

    public static void o4() {
        Observable.combineLatest(
                Observable.just(1, 2, 3),
                Observable.just(5, 6, 7, 8),
                Observable.just("A", "B", "C", "D"),
                new Function3<Integer, Integer, String, String>() {
                    @Override
                    public String apply(Integer integer, Integer aLong, String s) throws Exception {
                        Log.d(TAG, "合并事件  integer = " + integer + ",  aLong = " + aLong + ",   s = " + s);
                        return integer + aLong + s;
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "下游收到合并结果 = " + s);
            }
        });
    }

    public static void o5() {
        Observable.combineLatest(
                Observable.just(1, 2, 3),
                Observable.just("A", "B", "C"),
                new BiFunction<Integer, String, String>() {
                    @Override
                    public String apply(Integer integer, String s) throws Exception {
                        Log.d(TAG, "合并事件  integer = " + integer + ",   s = " + s);
                        return integer + s;
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "下游收到合并结果 = " + s);

            }
        });
    }

    public static void o6() {
        Observable.just(1, 2, 3, 4)
                .collect(new Callable<ArrayList<Integer>>() {
                    @Override
                    public ArrayList<Integer> call() throws Exception {
                        return new ArrayList<>();
                    }
                }, new BiConsumer<ArrayList<Integer>, Integer>() {
                    @Override
                    public void accept(ArrayList<Integer> integers, Integer integer) throws Exception {
                        integers.add(integer);
                    }
                }).subscribe(new Consumer<ArrayList<Integer>>() {
            @Override
            public void accept(ArrayList<Integer> integers) throws Exception {
                for (int data : integers) {
                    Log.d(TAG, "下游收到的数据包含 ： " + data);
                }
            }
        });
    }

    public static void o7() {
//        Observable.just(1,2,3)
//                .startWith(0)
//                .startWithArray(4,5,6)
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        Log.d(TAG,"下游收到的数据包含 ： "+integer);
//                    }
//                });

        Observable.just(1, 2, 3)
                .startWith(Observable.just(8, 9))
                .startWithArray(4, 5, 6)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "下游收到的数据包含 ： " + integer);
                    }
                });
    }

    public static void o8() {
        Observable.just(1, 2, 3, 4)
                .startWithArray(5, 6, 7)
                .count()
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.e(TAG, "发送的事件数量 =  " + aLong);
                    }
                });
    }

    public static void o9() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new NullPointerException());
                emitter.onNext(3);

            }
        }).delay(2, TimeUnit.SECONDS, false)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext下游收到事件 ： " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError下游收到事件 ： " + e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public static void o10() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onError(new Throwable("发生错误了"));
            }
        }).doOnEach(new Consumer<Notification<Integer>>() {
            // 1. 当Observable每发送1次数据事件就会调用1次
            @Override
            public void accept(Notification<Integer> integerNotification) throws Exception {
                Log.d(TAG, "doOnEach: " + integerNotification.getValue());
            }
        }).doOnNext(new Consumer<Integer>() {
            // 2. 执行Next事件前调用
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "doOnNext: " + integer);
            }
        }).doAfterNext(new Consumer<Integer>() {
            // 3. 执行Next事件后调用
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "doAfterNext: " + integer);
            }
        }).doOnComplete(new Action() {
            // 4. Observable正常发送事件完毕后调用
            @Override
            public void run() throws Exception {
                Log.e(TAG, "doOnComplete: ");
            }
        }).doOnError(new Consumer<Throwable>() {
            // 5. Observable发送错误事件时调用
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d(TAG, "doOnError: " + throwable.getMessage());
            }
        }).doOnSubscribe(new Consumer<Disposable>() {
            // 6. 观察者订阅时调用
            @Override
            public void accept(@NonNull Disposable disposable) throws Exception {
                Log.e(TAG, "doOnSubscribe: ");
            }
        }).doAfterTerminate(new Action() {
            // 7. Observable发送事件完毕后调用，无论正常发送完毕 / 异常终止
            @Override
            public void run() throws Exception {
                Log.e(TAG, "doAfterTerminate: ");
            }
        }).doFinally(new Action() {
            // 8. 最后执行
            @Override
            public void run() throws Exception {
                Log.e(TAG, "doFinally: ");
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "接收到了事件" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }
        });
    }

    public static void o11() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("AA");
                emitter.onNext("BB");
                //emitter.onError(new Throwable("上游突然发生了错误"));
                emitter.onError(new NullPointerException());
            }
        }).onErrorReturn(new Function<Throwable, String>() {
            @Override
            public String apply(Throwable throwable) throws Exception {
                return "我知道上游发生了错误，没关系，我来拯救。";
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "下游onNext收到数据 : " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "下游onError收到数据 : " + e.toString());
            }

            @Override
            public void onComplete() {
            }
        });
    }

    public static void o12() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("AA");
                emitter.onNext("BB");
                // emitter.onError(new Throwable("上游突然发生了错误"));
                emitter.onError(new Exception("uuuuuuu"));
                //emitter.onError(new NullPointerException());
            }
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> apply(Throwable throwable) throws Exception {
                return Observable.just("A", "B", "C");
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "下游onNext收到数据 : " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "下游onError收到数据 : " + e.toString());
            }

            @Override
            public void onComplete() {
            }
        });

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("AA");
                emitter.onNext("BB");
                //emitter.onError(new Throwable("上游突然发生了错误"));
                //emitter.onError(new Exception("uuuuuuu"));
                emitter.onError(new NullPointerException());
            }
        }).onExceptionResumeNext(new ObservableSource<String>() {
            @Override
            public void subscribe(Observer<? super String> observer) {
                observer.onNext("上游出现异常，我捕获到了。");
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "下游onNext收到数据 : " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "下游onError收到数据 : " + e.toString());
            }

            @Override
            public void onComplete() {
            }
        });
    }

    public static void o13() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                //emitter.onError(new Throwable("上游发生了错误"));
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
                return objectObservable.flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Object o) throws Exception {
                        Log.d(TAG, "我来决定是否重复--");
                        //只是发送了一个onConplete事件，下游并不会收到onComple回调，所以不会进行重复发送。
                        //return Observable.empty();

                        //发送了error事件，不会进行重复发送，并且error会回调到下游的onError中。
                        return Observable.error(new Throwable("我要终止"));

                        //发送的是onNext事件，所以会重复发送原始的Observable的事件，并一直重复。
                        //return Observable.just(1);
                    }
                });
            }
        })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "下游onNext接收到事件 ： " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "下游onError接收到事件 ： " + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "下游onComplete---------");
                    }
                });
    }

    public static void o14() {
        Observable.just(1, 2, 3, 4)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        //integer表示合并之后的结果，integer2表示发送过来的新数据
                        return integer * integer2;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "下游 ：" + integer);
            }
        });
    }


    public static void o15() {
        Observable.just(1, 2, 3, 4)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return "变换 " + integer;
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "下游收到数据 : " + s);
            }
        });
    }


    public static void o16() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("A");
                emitter.onNext("B");
                emitter.onNext("C");
                emitter.onNext("D");
                emitter.onNext("E");
                emitter.onNext("F");
                emitter.onNext("G");
                emitter.onNext("H");
                emitter.onComplete();
            }
        }).flatMap(new Function<String, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(String s) throws Exception {
                List<String> list = new ArrayList<>();
                list.add("flatmap_" + s);
                return Observable.fromIterable(list);
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d(TAG, "下游收到事件 ： " + o.toString());
            }
        });
    }


    public static List<Classes> getClassesList(int num) {
        List<Classes> classesList = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            Classes c1 = new Classes();
            c1.setCid(1);
            c1.setcName("学生" + num + "--课程" + i);
            classesList.add(c1);
        }
        return classesList;
    }

    public static List<Student> getStudents() {
        List<Student> studentList = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            Student student = new Student();
            student.setUid(i);
            student.setName("学生-" + i);
            student.setClassName(getClassesList(i));
            if (i % 2 == 0) {
                student.setScode("2");
            } else if (i % 3 == 0) {
                student.setScode("3");
            } else {
                student.setScode("1");
            }

            Log.d(TAG, "学生scode : " + student.getScode());
            studentList.add(student);
        }

        return studentList;
    }


    /**
     * 上游发送了一批学生的信息，
     * 要求下游不进行遍历就可以收到每个学生的每一门课程的名称
     */
    public static void o17() {
        Observable.create(new ObservableOnSubscribe<Student>() {
            @Override
            public void subscribe(ObservableEmitter<Student> emitter) throws Exception {
                emitter.onNext(getStudents().get(0));
                emitter.onNext(getStudents().get(1));
                emitter.onNext(getStudents().get(2));
                emitter.onNext(getStudents().get(3));
                emitter.onNext(getStudents().get(4));
                emitter.onNext(getStudents().get(5));
                emitter.onNext(getStudents().get(6));
                emitter.onNext(getStudents().get(7));
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread()).flatMap(new Function<Student, ObservableSource<Classes>>() {
            @Override
            public ObservableSource<Classes> apply(Student student) throws Exception {
                return Observable.fromIterable(student.className);
            }
        }).subscribe(new Consumer<Classes>() {
            @Override
            public void accept(Classes classes) throws Exception {
                Log.d(TAG, "下游收到数据 ： " + classes.cName);
            }
        });
    }

    public static void r1() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer >= 3;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "下游收到事件 : " + integer);
            }
        });
    }

    public static void r2() {
        Observable.just(1, "rxjava", 3, "android", 5)
                .ofType(String.class).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "下游收到事件 : " + s);
            }
        });
    }


    public static void r3() {
        Observable.just(1, "rxjava", 3, "android", 5, 6, 7)
                .skip(2)
                .skipLast(2)
                .subscribe(new Consumer<Serializable>() {
                    @Override
                    public void accept(Serializable serializable) throws Exception {
                        Log.d(TAG, "下游收到事件 : " + serializable.toString());
                    }
                });


        Observable.intervalRange(0, 8, 0, 1, TimeUnit.SECONDS)
                .skip(2, TimeUnit.SECONDS)
                .skipLast(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Serializable>() {
                    @Override
                    public void accept(Serializable serializable) throws Exception {
                        Log.d(TAG, "下游收到事件2 : " + serializable.toString());
                    }
                });
    }

    public static void r4() {
        Observable.just(1, 2, 2, 4, 5, 5, 5, 1, 2, 1)
                .distinct()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "下游收到事件: " + integer);
                    }
                });

        Observable.just(1, 2, 2, 4, 5, 5, 5, 1, 2, 1)
                .distinctUntilChanged()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "下游收到事件2 : " + integer);
                    }
                });

        Observable.fromIterable(getStudents())
                .distinctUntilChanged(new Function<Student, String>() {
                    @Override
                    public String apply(Student student) throws Exception {
                        return student.getScode();
                    }
                }).flatMap(new Function<Student, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Student student) throws Exception {
                return Observable.just(student.name + " - " + student.scode);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "下游收到事件3: " + s);
            }
        });

    }

    public static void r5() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "上游发送事件 ： 1");
                emitter.onNext(1);
                Log.d(TAG, "上游发送事件 ： 2");
                emitter.onNext(2);
                Log.d(TAG, "上游发送事件 ： 3");
                emitter.onNext(3);
                Log.d(TAG, "上游发送事件 ： 4");
                emitter.onNext(4);
                Log.d(TAG, "上游发送事件 ： 5");
                emitter.onNext(5);
                emitter.onComplete();

            }
        }).takeLast(2).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "下游事件 ：" + integer);
            }
        });
    }

    public static void r6() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onComplete();
            }
        }).elementAtOrError(8).subscribe(new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onSuccess(Integer integer) {
                Log.d(TAG, "发生onSuccess");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "发生error : " + e.toString());
            }
        });
    }

    public static void r7() {
        List<ObservableSource<Integer>> list = new ArrayList<>();
        list.add(Observable.just(1, 2, 3).delay(1, TimeUnit.SECONDS));
        list.add(Observable.just(4, 5, 6));
        Observable.amb(list).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "收到数据 :" + integer);
            }
        });
    }

    public static void r8() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                //emitter.onNext("AA");
                emitter.onComplete();

            }
        }).defaultIfEmpty("BB")
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG,"onNext 数据 ： "+s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"onError 数据 ： "+e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG,"onComplete");
                    }
                });
    }


    public static void wlog(String s) {
        Log.d(TAG, "");

    }


    public static void rxMaybe(){
        Maybe.create(new MaybeOnSubscribe<String>() {
            @Override
            public void subscribe(MaybeEmitter<String> emitter) throws Exception {
                emitter.onSuccess("Success");
                emitter.onError(new Throwable("Error"));
                emitter.onComplete();

            }
        }).subscribe(new MaybeObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
    public static void rxObservable(){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("A");
                emitter.onNext("B");
                emitter.onError(new Throwable("Error"));
                emitter.onComplete();

            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    public static void rxSingle(){
        Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(SingleEmitter<String> emitter) throws Exception {
                emitter.onSuccess("Success");
                emitter.onError(new Throwable("Error"));

            }
        }).subscribe(new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public static void rxCompletable(){
        Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                emitter.onError(new Throwable("Error"));
                emitter.onComplete();

            }
        }).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public static void rxFlowable(){
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> emitter) throws Exception {
                emitter.onNext("A");
                emitter.onNext("B");
                emitter.onError(new Throwable("Error"));
                emitter.onComplete();

            }
        },BackpressureStrategy.ERROR)
                .subscribe(new FlowableSubscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
