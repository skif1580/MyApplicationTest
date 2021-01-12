package com.example.myapplicationtest.net

import java.lang.RuntimeException

class ApiException(val code:Int,val msg:String) :RuntimeException()