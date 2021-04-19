package com.rw.login.ui

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.rw.basemvp.BaseActivity
import com.rw.basemvp.bean.BaseBean
import com.rw.basemvp.widget.TitleView
import com.rw.login.R
import com.rw.login.ui.bean.LoginBean
import com.rw.login.ui.presenter.LoginPresenter
import com.rw.service.ServiceViewModule
import com.rw.service.bean.AccountBean
import kotlinx.android.synthetic.main.activity_login.*
@Route(path = "/login/LoginActivity")
class LoginActivity : BaseActivity<LoginPresenter>(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.login->{
                val user=username.text.toString().trim()
                val psd=password.text.toString().trim()
                mPresenter?.postData(0,HttpApi.LOGIN_URL, LoginBean::class.java,true
                    ,LinkedHashMap<String,Any>().apply {
                        put("username",user)
                        put("password",psd)
                    })
            }
        }
    }

    override fun getPresenter(): LoginPresenter {
        return LoginPresenter()
    }

    override fun setLayout(): Int {
        return R.layout.activity_login
    }

    override fun initData(savedInstanceState: Bundle?, titleView: TitleView) {
        titleView.setTitle("登录")
        login.setOnClickListener(this)
       getViewModel()?.baseBean?.observeForever {
           when(it?.requestType){
               HttpApi.LOGIN_URL->{
                   showToast("登录成功")
                   val bean =it as LoginBean
                   ServiceViewModule.get()?.loginService?.value= AccountBean(bean.data.nickname,bean.data.id.toString(),bean.data.type.toString())
                   finish()
               }
               else->showToast("让我说点什么好")
           }
       }
    }


}