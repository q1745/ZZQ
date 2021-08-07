package com.example.mvp_core

abstract class BaseRepository<M : IModel>{

    protected lateinit var mModel:M
    init {
        mModel = createModel()
    }

    abstract fun createModel(): M

}