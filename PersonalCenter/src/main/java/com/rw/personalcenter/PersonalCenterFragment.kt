package com.rw.personalcenter

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.rw.basemvp.BaseFragment
import com.rw.basemvp.BaseWrapperFragment
import com.rw.basemvp.presenter.BaseView
import com.rw.basemvp.presenter.DefaultPresenter
import com.rw.basemvp.presenter.MvpPresenter
import com.rw.service.ServiceViewModule
import com.rw.service.bean.LoginOutBean
import kotlinx.android.synthetic.main.pc_fragment.*

/**
 * Created by Amuse
 * Date:2021/4/15.
 * Desc:
 */
@Route(path = "/pc/PersonalCenterFragment")
class PersonalCenterFragment : BaseFragment<DefaultPresenter>() {
    override fun getViewLayout(): Int {
       return R.layout.pc_fragment
    }

    override fun initView() {
        login_state.setOnClickListener {
            ARouter.getInstance().build("/login/LoginActivity").navigation()
        }

        login_out.setOnClickListener {
            ServiceViewModule.get()?.loginOutService?.value= LoginOutBean()
        }
        ServiceViewModule.get()?.loginService?.observeForever {
            login_state.text="用户${it.userName}"
            login_out.visibility= View.VISIBLE
        }
        ServiceViewModule.get()?.loginOutService?.observeForever {
            login_state.text="未登录"
            login_out.visibility= View.GONE
        }

    }

    override fun loadData() {

    }

    override fun getPresenter(): DefaultPresenter {
        return DefaultPresenter()
    }
}