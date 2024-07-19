package com.zyb.tempapplication

import org.junit.Test

class ConstructInitTest {

    @Test
    fun create(){
//        Person("zhangshan", 30)
        Man("zhangshan", 30,33)
    }
}


open class Person constructor(open var name:String, open var age:Int){
    var msg = "测试Msg"
    constructor(name: String): this(name,33){
        println("次构造调用: name:$name,age:$age,msg: $msg")
    }

    init {
        println("init调用: name:$name,age:$age,msg: $msg")
        msg = "lishi"
        println("init调用: name:$name,age:$age,msg: $msg")
    }
    var msg2 = "测试Msg"

}

class Man(override var name:String, override var age: Int): Person(name,age){
//    constructor(name: String,age: Int):super(name,age){
//        println("Man 2 个参数 $name  $age")
//    }

    constructor(name: String,age: Int,number: Number): this(name,age){
        println("Man 2 个参数 $name  $age  $number")
    }
    init {
        println("Man init")
    }
}