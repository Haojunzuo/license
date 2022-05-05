package com.anga.csm;


import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Arrays;

@SpringBootTest
public class MACTest {

    @Test
    public void macTest() throws Exception{
        InetAddress ia;
        ia = InetAddress.getLocalHost();
        System.out.println("ia = " + ia);
        byte[] mac = null;
        mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
        System.out.println("mac = " + mac);
        byte[] hardwareAddress = NetworkInterface.getByInetAddress(ia).getHardwareAddress();


        System.out.println("hardwareAddress = " + hardwareAddress);
        // 下面代码是把mac地址拼装成String
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mac.length; i++) {
            if (i != 0) {
                sb.append("-");
            }
            // mac[i] & 0xFF 是为了把byte转化为正整数
            String s = Integer.toHexString(mac[i] & 0xFF);
            sb.append(s.length() == 1 ? 0 + s : s);
        }
        String s = sb.toString().toUpperCase();
        System.out.println(s);

    }
}
