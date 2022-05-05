package com.anga.csm.license;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @title LicenseCheckModel
 * @description 自定义需要校验的License参数
 * @author Administrator
 * @updateTime 2022/4/30 0030 18:19
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
