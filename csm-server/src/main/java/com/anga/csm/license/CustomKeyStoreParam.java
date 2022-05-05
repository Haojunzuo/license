package com.anga.csm.license;

import de.schlichtherle.license.AbstractKeyStoreParam;

import java.io.*;

/**
 * @ProjectName CustomKeyStoreParam
 * @author Administrator
 * @version 1.0.0
 * @Description 自定义KeyStoreParam，用于将公私钥存储文件存放到其他磁盘位置而不是项目中
 * @createTime 2022/4/30 0030 18:16
 */
public class CustomKeyStoreParam extends AbstractKeyStoreParam {

    /**
     * 公钥/私钥在磁盘上的存储路径
     */
    private String storePath;
    private String alias;
    private String storePwd;
    private String keyPwd;

    public CustomKeyStoreParam(Class clazz, String resource,String alias,String storePwd,String keyPwd) {
        super(clazz, resource);
        this.storePath = resource;
        this.alias = alias;
        this.storePwd = storePwd;
        this.keyPwd = keyPwd;
    }


    @Override
    public String getAlias() {
        return alias;
    }

    @Override
    public String getStorePwd() {
        return storePwd;
    }

    @Override
    public String getKeyPwd() {
        return keyPwd;
    }

    /**
     * @title getStream
     * @description
     * 复写de.schlichtherle.license.AbstractKeyStoreParam的getStream()方法<br/>
     * 用于将公私钥存储文件存放到其他磁盘位置而不是项目中
     * @author Administrator
     * @updateTime 2022/4/30 0030 18:16
     */
    @Override
    public InputStream getStream() throws IOException {
        final InputStream in = new FileInputStream(new File(storePath));
        if (null == in){
            throw new FileNotFoundException(storePath);
        }

        return in;
    }
}
