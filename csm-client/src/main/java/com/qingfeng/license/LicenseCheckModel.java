package com.qingfeng.license;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName LicenseCheckModel
 * @author Administrator
 * @version 1.0.0
 * @Description 自定义需要校验的License参数
 * @createTime 2022/4/30 0030 18:28
 */
@Data
public class LicenseCheckModel implements Serializable{

    private static final long serialVersionUID = 8600137500316662317L;
    /**
     * 可被允许的IP地址
     */
    private List<String> ipAddress;

    /**
     * 可被允许的MAC地址
     */
    private List<String> macAddress;

    /**
     * 可被允许的CPU序列号
     */
    private String cpuSerial;

    /**
     * 可被允许的主板序列号
     */
    private String mainBoardSerial;


}
