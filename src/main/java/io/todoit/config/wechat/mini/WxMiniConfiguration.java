package io.todoit.config.wechat.mini;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaInMemoryConfig;
import cn.binarywang.wx.miniapp.message.WxMaMessageRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author:zhangd
 * @date:2019/4/21 21:58
 */
@Configuration
@EnableConfigurationProperties(WxMiniProperties.class)
public class WxMiniConfiguration {

    private WxMiniProperties properties;

    private static WxMaService wxMaService;

    @PostConstruct
    public void init() {
        String appid = this.properties.getAppid();
        if (appid == null) {
            throw new RuntimeException("wx appid not null");
        }

        WxMaInMemoryConfig config = new WxMaInMemoryConfig();
        config.setAppid(properties.getAppid());
        config.setSecret(properties.getSecret());
        config.setToken(properties.getToken());
        config.setAesKey(properties.getAesKey());
        config.setMsgDataFormat(properties.getMsgDataFormat());

        WxMaService service = new WxMaServiceImpl();
        service.setWxMaConfig(config);
        wxMaService = service;
    }

    @Autowired
    public WxMiniConfiguration(WxMiniProperties properties) {
        this.properties = properties;
    }


    public static WxMaService getWxMaService() {
        if (wxMaService == null) {
            throw new IllegalArgumentException(String.format("未找到对应appid的配置，请核实！"));
        }

        return wxMaService;
    }

}
