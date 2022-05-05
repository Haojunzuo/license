package com.anga.csm.controller;

import com.anga.csm.license.*;
import com.anga.csm.license.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName LicenseCreatorController
 * @author Administrator
 * @version 1.0.0
 * @Description 于生成证书文件，不能放在给客户部署的代码里
 * @createTime 2022/4/30 0030 18:13
 */
@RestController
@RequestMapping("/license")
public class LicenseCreatorController {

    /**
     * 证书生成路径
     */
    @Value("${license.licensePath}")
    private String licensePath;

    /**
     * @title 获取服务器硬件信息
     * @description @param osName 操作系统类型，如果为空则自动判断
     * @author Administrator
     * @updateTime 2022/4/30 0030 18:14
     */
    @RequestMapping(value = "/getServerInfos")
    public LicenseCheckModel getServerInfos(@RequestParam(value = "osName",required = false) String osName) {
        //操作系统类型
        if(StringUtils.isBlank(osName)){
            osName = System.getProperty("os.name");
        }
        osName = osName.toLowerCase();

        AbstractServerInfos abstractServerInfos = null;

        //根据不同操作系统类型选择不同的数据获取方法
        if (osName.startsWith("windows")) {
            abstractServerInfos = new WindowsServerInfos();
        } else if (osName.startsWith("linux")) {
            abstractServerInfos = new LinuxServerInfos();
        }else{//其他服务器类型
            abstractServerInfos = new LinuxServerInfos();
        }

        return abstractServerInfos.getServerInfos();
    }


    /**
     * @title 生成证书
     * @description
     * {
     *     "result": "ok",
     *     "msg": {
     *         "subject": "license_demo",
     *         "privateAlias": "privateKey",
     *         "keyPass": "private_password1234",
     *         "storePass": "public_password1234",
     *         "licensePath": "D:/license/license.lic",
     *         "privateKeysStorePath": "D:/license/privateKeys.keystore",
     *         "issuedTime": "2022-04-10 00:00:01",
     *         "expiryTime": "2022-05-31 23:59:59",
     *         "consumerType": "User",
     *         "consumerAmount": 1,
     *         "description": "这是证书描述信息",
     *         "licenseCheckModel": {
     *             "ipAddress": [],
     *             "macAddress": [],
     *             "cpuSerial": "",
     *             "mainBoardSerial": ""
     *         }
     *     }
     * }
     * @author Administrator
     * @updateTime 2022/4/30 0030 18:14
     */
    @RequestMapping(value = "/generateLicense",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Map<String,Object> generateLicense(@RequestBody(required = true) LicenseCreatorParam param) {
        Map<String,Object> resultMap = new HashMap<>(2);

        if(StringUtils.isBlank(param.getLicensePath())){
            param.setLicensePath(licensePath);
        }

        LicenseCreator licenseCreator = new LicenseCreator(param);
        boolean result = licenseCreator.generateLicense();

        if(result){
            resultMap.put("result","ok");
            resultMap.put("msg",param);
        }else{
            resultMap.put("result","error");
            resultMap.put("msg","证书文件生成失败！");
        }

        return resultMap;
    }

}
