package com.zyb.springweb.mapper

import com.zyb.springweb.bean.User
import org.apache.ibatis.annotations.Mapper

@Mapper
interface UserMapper {

    fun getUserInfoByName(name:String): User

    fun getAllUser(): List<User>

    fun getUserInfoById(id:Int): User

    fun insertUser(user:User):Int

    fun updateUser(user:User):Int


    fun deleteUser(id:Int):Int
}