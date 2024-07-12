package com.zyb.springweb.controller

import com.zyb.springweb.bean.User
import com.zyb.springweb.mapper.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.sql.DataSource


@RestController
@RequestMapping("/zyb")
class HelloController {

    @Autowired
    private var usermapper: UserMapper? = null

    @Autowired
    private var dataSource: DataSource? = null

    @GetMapping("/zhangshan")
    fun getZhangShan(): User {
        return User(1,"zhangshan", 23)
    }

    @GetMapping("/lisi")
    fun getLisi(): User {
        return User(2,"lisi", 23)
    }
}