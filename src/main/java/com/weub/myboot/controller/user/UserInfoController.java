package com.weub.myboot.controller.user;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/userInfo")
public class UserInfoController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @RequiresPermissions("userInfo:view")
    public String list() {
        return "user/info/list";
    }

    @RequestMapping(value = "add", method = {RequestMethod.GET, RequestMethod.PUT})
    @RequiresPermissions(value = "userInfo:add")
    public String add() {
        return "user/info/add";
    }

    @RequestMapping(value = "del", method = {RequestMethod.GET, RequestMethod.DELETE})
    @RequiresPermissions(value = "userInfo:del")
    public String del() {
        return "user/info/del";
    }
}
