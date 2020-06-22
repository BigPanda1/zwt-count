package com.zjy.zwtcount.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 中小企业诉求
 * @author chenjia
 * @date 20200323
 */
@Data
public class EnterpriseAppealDto {

    /**
     * 完成粤信签登录后返回的token
     */
    @JSONField(name= "ryz_access_token")
    private String ryzAccessToken;

    /**
     * 当传此字段并且值为
     * shenzhen_access_token_permissions
     * 时，即开启多次 token 换取身份信息机制
     */
    @JSONField(name="ryz_access_token_switch")
    private String ryzAccessTokenSwitch="shenzhen_access_token_permissions";

}
