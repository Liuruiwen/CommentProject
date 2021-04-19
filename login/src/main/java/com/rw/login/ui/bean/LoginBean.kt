package com.rw.login.ui.bean

import com.rw.basemvp.bean.BaseBean

/**
 * Created by Amuse
 * Date:2021/4/14.
 * Desc:
 */
data class LoginBean (
        var data:DataBean
):BaseBean()


data class DataBean(
      var  admin :Boolean,
      var id:Int,
      var nickname:String,
      var type:Int
)
