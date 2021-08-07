package com.example.mvp_core

import java.lang.ref.SoftReference

abstract class BasePresenter<Repo: BaseRepository<*>,V : IView>(view : V) {

    protected lateinit var mRepository : Repo
    //软引用
    protected lateinit var mView: SoftReference<V>

    init {
        mRepository = createRepository()
        mView = SoftReference<V>(view)
    }

    abstract fun createRepository(): Repo

}