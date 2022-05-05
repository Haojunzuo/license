package com.qingfeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * @ProjectName QingfengClientApplication
 * @author Administrator
 * @version 1.0.0
 * @createTime 2022/4/30 0030 17:58
 */
@SpringBootApplication
@ServletComponentScan
@PropertySource({"license-config.properties"}) //加载额外的配置
public class CsmClientApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(CsmClientApplication.class, args);
    }
}
