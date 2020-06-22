package com.zjy.zwtcount.entity;

import lombok.Data;

@Data
public class PreClaimVerificationVo {
    String rtCode;//核查标识,核查结果满足申领条件的返回1，否则返回0原因见失败原因字段

    String rtMsg;//核查失败原因,当核查标识为失败时返回失败原因

    String command;//联机处理代码

    String data;//是否持有二代卡信息
}