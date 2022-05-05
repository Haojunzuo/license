package com.anga.csm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * @ProjectName QingfengServerApplication
 * @author Administrator
 * @version 1.0.0
 * @Description 认证服务器端
 * @createTime 2022/4/30 0030 17:17
 */
@SpringBootApplication
@ServletComponentScan
@PropertySource({"license-config.properties"}) //加载额外的配置
public class CsmServerApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(CsmServerApplication.class, args);
    }
}
