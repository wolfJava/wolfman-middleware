package com.wolfman.mongo.explorer.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wolfman.mongo.explorer.common.constants.SystemConstant;
import com.wolfman.mongo.explorer.entity.Member;
import com.wolfman.mongo.explorer.service.IMemberService;
import org.springframework.stereotype.Service;

import javax.core.common.ResultMsg;

/**
 * Created by Tom on 2018/8/27.
 */
@Service
public class MemberService implements IMemberService{
    @Override
    public ResultMsg<?> login(String loginName, String loginPass) {
        return new ResultMsg<Member>(SystemConstant.RESULT_STATUS_SUCCESS,"登录成功",new Member());
    }

    @Override
    public ResultMsg<?> logout(String loginName) {
        JSONObject data = new JSONObject();
        data.put("loginHost","/index.html");
        return new ResultMsg<>(SystemConstant.RESULT_STATUS_SUCCESS,"退出成功",data);
    }
}
