package com.rw.homepage.presenter

import androidx.lifecycle.LifecycleOwner
import com.rw.basemvp.presenter.BaseView
import com.rw.basemvp.presenter.MvpPresenter

/**
 * Created by Amuse
 * Date:2021/4/15.
 * Desc:
 */
class HomePagePresenter:MvpPresenter<BaseView> (){
    override fun getBaseUrl(): String {
       return ""
    }

    override fun getToken(): String {
       return ""
    }

    override fun onCreate(owner: LifecycleOwner) {

    }

    override fun onStart(owner: LifecycleOwner) {

    }

    override fun onStop(owner: LifecycleOwner) {

    }

    override fun onResume(owner: LifecycleOwner) {

    }

    override fun onPause(owner: LifecycleOwner) {

    }
}