package com.anga.csm;

import com.anga.csm.license.LicenseCheckModel;
import com.anga.csm.license.LicenseCreator;
import com.anga.csm.license.LicenseCreatorParam;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author Administrator
 * @version 1.0.0
 * @ProjectName qingfeng-license
 * @Description TODO
 * @createTime 2022年04月30日 21:27:00
 */
@SpringBootTest
public class LicenseTest {

    /**
     * {
     * 	"subject": "license_demo",
     * 	"privateAlias": "privateKey",
     * 	"keyPass": "private_password1234",
     * 	"storePass": "public_password1234",
     * 	"licensePath": "D:/license/license.lic",
     * 	"privateKeysStorePath": "D:/license/privateKeys.keystore",
     * 	"issuedTime": "2022-04-10 00:00:01",
     * 	"expiryTime": "2022-05-31 23:59:59",
     * 	"consumerType": "User",
     * 	"consumerAmount": 1,
     * 	"description": "这是证书描述信息",
     * 	"licenseCheckModel": {
     * 		"ipAddress": [],
     * 		"macAddress": [],
     * 		"cpuSerial": "",
     * 		"mainBoardSerial": ""
     *        }
     * }
     */
    @Test
    public void licenseCreate() {
        // 生成license需要的一些参数
        LicenseCreatorParam param = new LicenseCreatorParam();
        param.setSubject("license_demo");
        param.setPrivateAlias("privateKey");
        param.setKeyPass("private_password1234");
        param.setStorePass("public_password1234");
        param.setLicensePath("D:/license/license.lic");
        param.setPrivateKeysStorePath("D:/license/privateKeys.keystore");
        Calendar issueCalendar = Calendar.getInstance();
        param.setIssuedTime(issueCalendar.getTime());
        Calendar expiryCalendar = Calendar.getInstance();
        expiryCalendar.set(2022, Calendar.UNDECIMBER, 31, 23, 59, 59);
        param.setExpiryTime(expiryCalendar.getTime());
        param.setConsumerType("user");
        param.setConsumerAmount(1);
        param.setDescription("这是证书描述信息");
        //自定义需要校验的License参数
        LicenseCheckModel licenseCheckModel = new LicenseCheckModel();
        licenseCheckModel.setCpuSerial("");
        licenseCheckModel.setMainBoardSerial("");
        licenseCheckModel.setIpAddress(new ArrayList<>());
        licenseCheckModel.setMacAddress(new ArrayList<>());
        LicenseCreator licenseCreator = new LicenseCreator(param);
        param.setLicenseCheckModel(licenseCheckModel);
        // 生成license
        licenseCreator.generateLicense();
    }

}
