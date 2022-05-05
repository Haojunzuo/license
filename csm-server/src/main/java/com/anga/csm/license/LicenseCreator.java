package com.anga.csm.license;

import de.schlichtherle.license.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.security.auth.x500.X500Principal;
import java.io.File;
import java.text.MessageFormat;
import java.util.prefs.Preferences;

/**
 * @ProjectName LicenseCreator
 * @author Administrator
 * @version 1.0.0
 * @Description License生成类
 * @createTime 2022/4/30 0030 18:19
 */
public class LicenseCreator {
    private static Logger logger = LogManager.getLogger(LicenseCreator.class);
    private final static X500Principal DEFAULT_HOLDER_AND_ISSUER = new X500Principal("CN=localhost, OU=localhost, O=localhost, L=SH, ST=SH, C=CN");
    private LicenseCreatorParam param;

    public LicenseCreator(LicenseCreatorParam param) {
        this.param = param;
    }

    /**
     * @title generateLicense
     * @description 生成License证书
     * @author Administrator
     * @updateTime 2022/4/30 0030 18:19
     */
    public boolean generateLicense(){
        try {
            LicenseManager licenseManager = new CustomLicenseManager(initLicenseParam());
            LicenseContent licenseContent = initLicenseContent();

            licenseManager.store(licenseContent,new File(param.getLicensePath()));

            return true;
        }catch (Exception e){
            logger.error(MessageFormat.format("证书生成失败：{0}",param),e);
            return false;
        }
    }

    /**
     * @title initLicenseParam
     * @description 初始化证书生成参数
     * @author Administrator
     * @updateTime 2022/4/30 0030 18:19
     */
    private LicenseParam initLicenseParam(){
        Preferences preferences = Preferences.userNodeForPackage(LicenseCreator.class);

        //设置对证书内容加密的秘钥
        CipherParam cipherParam = new DefaultCipherParam(param.getStorePass());
        //自定义KeyStoreParam，用于将公私钥存储文件存放到其他磁盘位置而不是项目中
        KeyStoreParam privateStoreParam = new CustomKeyStoreParam(LicenseCreator.class
                ,param.getPrivateKeysStorePath()
                ,param.getPrivateAlias()
                ,param.getStorePass()
                ,param.getKeyPass());

        //组织License参数
        LicenseParam licenseParam = new DefaultLicenseParam(param.getSubject()
                ,preferences
                ,privateStoreParam
                ,cipherParam);

        return licenseParam;
    }

    /**
     * @title initLicenseContent
     * @description 设置证书生成正文信息
     * @author Administrator
     * @updateTime 2022/4/30 0030 18:19
     */
    private LicenseContent initLicenseContent(){
        LicenseContent licenseContent = new LicenseContent();
        licenseContent.setHolder(DEFAULT_HOLDER_AND_ISSUER);
        licenseContent.setIssuer(DEFAULT_HOLDER_AND_ISSUER);

        licenseContent.setSubject(param.getSubject());
        licenseContent.setIssued(param.getIssuedTime());
        licenseContent.setNotBefore(param.getIssuedTime());
        licenseContent.setNotAfter(param.getExpiryTime());
        licenseContent.setConsumerType(param.getConsumerType());
        licenseContent.setConsumerAmount(param.getConsumerAmount());
        licenseContent.setInfo(param.getDescription());

        //扩展校验服务器硬件信息
//        Object o = param.getLicenseCheckModel();
//        licenseContent.setExtra(o);
        licenseContent.setExtra(param.getLicenseCheckModel());

        return licenseContent;
    }

}
