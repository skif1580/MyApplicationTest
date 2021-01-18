package com.example.myapplicationtest.net.response

abstract class Response {
    var status_code: Int = 0
    var status_message: String = ""
    var success: Boolean = true
}

//json rpc 2.0 https://www.jsonrpc.org/specification