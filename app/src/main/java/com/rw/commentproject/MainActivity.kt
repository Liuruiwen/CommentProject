package com.rw.commentproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.rw.basemvp.adapter.TabAdapter
import com.rw.login.ui.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val homeFragment=ARouter.getInstance().build("/home/homePageFragment").navigation()
        val pcFragment=ARouter.getInstance().build("/pc/PersonalCenterFragment").navigation()
        val list= arrayListOf<Fragment>()
        if (homeFragment is Fragment){
            list.add(homeFragment)
        }

        if (pcFragment is Fragment){
            list.add(pcFragment)
        }
        val adapter=TabAdapter(supportFragmentManager,list)
        viewpager.adapter=adapter
        tv_main.setOnClickListener {
            viewpager.currentItem=0
            setTextState(1,tv_main)
            setTextState(0,tv_pc)
        }
        tv_pc.setOnClickListener {
            viewpager.currentItem=1
            setTextState(0,tv_main)
            setTextState(1,tv_pc)
        }
//        tv_login.setOnClickListener {
//            startActivity(Intent(this@MainActivity,LoginActivity::class.java))
//        }
    }

    private fun setTextState(type :Int,tv:TextView){
        val color=if (type==1) R.color.textMain else R.color.white
        tv.setTextColor(ContextCompat.getColor(this,color))
        tv.setBackgroundColor(ContextCompat.getColor(this,if (type==1) R.color.white else R.color.colorMain))
    }
}