package io.todoit.modules.sys.controller;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import io.todoit.common.annotation.WebLog;
import io.todoit.config.wechat.mini.WxMiniConfiguration;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-04-03
 */
@Slf4j
@RestController
@RequestMapping("/sys/user")
public class UserController {

    @WebLog
    @RequestMapping("/login")
    public Object get(String code){
        HashMap<String, Object> map = new HashMap(2);
        try {
            WxMaService wxMaService = WxMiniConfiguration.getWxMaService();
            WxMaJscode2SessionResult sessionInfo = wxMaService.getUserService().getSessionInfo(code);
            map.put("openid", sessionInfo.getOpenid());
            map.put("sessionKey()", sessionInfo.getSessionKey());
            return map;
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            return e.getMessage();
        }
    }
}
