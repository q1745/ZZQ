package com.example.net.rxjava;


import androidx.lifecycle.LifecycleOwner;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import java.security.acl.Owner;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author:ZZQ
 * @date:2021/7/24
 */
public class ObservableUtils {

    private ObservableUtils(){

    }
    private static ObservableUtils instance = new ObservableUtils();
    public static ObservableUtils getInstance() {
        return instance;
    }

    public <T> void doObservable(Observable<T> observable, Observer<T> observer, LifecycleOwner owner) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<T>autoDisposable(AndroidLifecycleScopeProvider.from(owner)))
                .subscribe(observer);
    }

}
