package com.rw.basemvp

import android.os.Bundle
import android.util.Log
import android.view.Window
import androidx.lifecycle.ViewModelProvider
import com.rw.basemvp.bean.BaseModel
import com.rw.basemvp.dialog.LoadingDialog
import com.rw.basemvp.presenter.BaseView
import com.rw.basemvp.presenter.MvpPresenter

/*
* @Author:      Amuser
* @CreateDate:   2019-12-18 9:19
*@Description: 
*/
abstract class BaseActivity<P : MvpPresenter<BaseView>> : BaseWrapperActivity(), BaseView {


    protected var mPresenter: P? = null
    private var mLoadingDialog: LoadingDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(getLayout())
//        intiView()
//        val type = this.javaClass.getGenericSuperclass() as ParameterizedType
//        val presenterClass = type.actualTypeArguments[0] as Class<out WrapperPresenter<BaseView>>
//        try {
//            val type = this.javaClass.getGenericSuperclass() as ParameterizedType
//            val presenterClass = type.actualTypeArguments[0] as Class<out WrapperPresenter<BaseView>>
//            if (presenterClass.newInstance() is MvpPresenter<BaseView>){
//                this.mPresenter = presenterClass.newInstance() as P
//                mPresenter?.attachView(this)
//                if (mPresenter!=null){
//                    lifecycle.addObserver(mPresenter!!)
//                }
//            }
//
//        } catch (e: IllegalAccessException) {
//            e.printStackTrace()
//        } catch (e: java.lang.InstantiationException) {
//            e.printStackTrace()
//        }
        mPresenter=getPresenter()
        mPresenter?.attachView(this)
        mPresenter?.apply {
            lifecycle.addObserver(this)
        }
        mLoadingDialog = createLoadingDialog()
    }

    override fun onDestroy() {
        super.onDestroy()
        onHideLoading()
        mLoadingDialog = null
        mPresenter?.apply {
            lifecycle.removeObserver(this)
        }

    }

    override fun onBeforeSuccess() {
    }

    override fun onShowLoading() {
        Log.d("?????????????????????", "========${mLoadingDialog?.isShowing}")
        if(mLoadingDialog!=null && !mLoadingDialog?.isShowing!!){
          mLoadingDialog?.show()
        }

    }

    override fun onHideLoading() {
        if (!isFinishing && mLoadingDialog?.isShowing!!) {
            mLoadingDialog?.apply {
                if (isShowing){
                    runOnUiThread { dismiss() }
                }
            }

        }
    }



    abstract fun getPresenter():P


    private fun createLoadingDialog(): LoadingDialog? {
        if (mLoadingDialog == null) {
            mLoadingDialog = LoadingDialog(this)
            mLoadingDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        }
        return mLoadingDialog
    }

    private val viewModule:BaseModel by lazy {
        ViewModelProvider.AndroidViewModelFactory(application).create(BaseModel::class.java)
    }
    override fun getViewModel(): BaseModel?{
        return viewModule
    }
//    fun  showToast(message:String){
//        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
//    }
}