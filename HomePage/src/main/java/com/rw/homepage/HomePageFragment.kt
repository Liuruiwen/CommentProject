package com.rw.homepage

import com.alibaba.android.arouter.facade.annotation.Route
import com.rw.basemvp.BaseWrapperFragment
import com.rw.homepage.presenter.HomePagePresenter
import com.rw.service.ServiceViewModule
import kotlinx.android.synthetic.main.homepage_fragment.*

/**
 * Created by Amuse
 * Date:2021/4/15.
 * Desc:
 */
@Route(path = "/home/homePageFragment")
 class HomePageFragment : BaseWrapperFragment<HomePagePresenter>() {
    override fun getViewLayout(): Int {
        return R.layout.homepage_fragment
    }

    override fun initView() {

        ServiceViewModule.get()?.loginService?.observeForever {
            login_state.text="用户${it.userName}"
        }
        ServiceViewModule.get()?.loginOutService?.observeForever {
            login_state.text="未登录"
        }
    }

    override fun loadData() {

    }

    override fun getPresenter(): HomePagePresenter {
        return HomePagePresenter()
    }

    override fun lazyData() {

    }
}