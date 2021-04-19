package com.rw.basemvp.bean


open class BaseBean {
    var errorCode:Int?=0;
    var errorMsg:String?=""
    val error: Boolean?= false
//    val results: Results
    var requestCount:Int?=0
    var result:String?=null
    var requestType:String?=null

}