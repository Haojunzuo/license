package com.qingfeng.license;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @ProjectName AbstractServerInfos
 * @author Administrator
 * @version 1.0.0
 * @Description 用于获取客户服务器的基本信息，如：IP、Mac地址、CPU序列号、主板序列号等
 * @createTime 2022/4/30 0030 18:24
 */
public abstract class AbstractServerInfos {
    private static Logger logger = LogManager.getLogger(AbstractServerInfos.class);

    /**
     * @title getServerInfos
     * @description 组装需要额外校验的License参数
     * @author Administrator
     * @updateTime 2022/4/30 0030 18:24
     */
    public LicenseCheckModel getServerInfos(){
        LicenseCheckModel result = new LicenseCheckModel();

        try {
            result.setIpAddress(this.getIpAddress());
            result.setMacAddress(this.getMacAddress());
            result.setCpuSerial(this.getCPUSerial());
            result.setMainBoardSerial(this.getMainBoardSerial());
        }catch (Exception e){
            logger.error("获取服务器硬件信息失败",e);
        }

        return result;
    }

    /**
     * @title getIpAddress
     * @description 获取IP地址
     * @author Administrator
     * @updateTime 2022/4/30 0030 18:24
     */
    protected abstract List<String> getIpAddress() throws Exception;

    /**
     * @title getMacAddress
     * @description 获取Mac地址
     * @author Administrator
     * @updateTime 2022/4/30 0030 18:24
     */
    protected abstract List<String> getMacAddress() throws Exception;

    /**
     * @title getCPUSerial
     * @description 获取CPU序列号
     * @author Administrator
     * @updateTime 2022/4/30 0030 18:25
     */
    protected abstract String getCPUSerial() throws Exception;

    /**
     * @title getMainBoardSerial
     * @description 获取主板序列号
     * @author Administrator
     * @updateTime 2022/4/30 0030 18:25
     */
    protected abstract String getMainBoardSerial() throws Exception;

    /**
     * @title getLocalAllInetAddress
     * @description 获取当前服务器所有符合条件的InetAddress
     * @author Administrator
     * @updateTime 2022/4/30 0030 18:25
     */
    protected List<InetAddress> getLocalAllInetAddress() throws Exception {
        List<InetAddress> result = new ArrayList<>(4);

        // 遍历所有的网络接口
        for (Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces(); networkInterfaces.hasMoreElements(); ) {
            NetworkInterface iface = (NetworkInterface) networkInterfaces.nextElement();
            // 在所有的接口下再遍历IP
            for (Enumeration inetAddresses = iface.getInetAddresses(); inetAddresses.hasMoreElements(); ) {
                InetAddress inetAddr = (InetAddress) inetAddresses.nextElement();

                //排除LoopbackAddress、SiteLocalAddress、LinkLocalAddress、MulticastAddress类型的IP地址
                if(!inetAddr.isLoopbackAddress() /*&& !inetAddr.isSiteLocalAddress()*/
                        && !inetAddr.isLinkLocalAddress() && !inetAddr.isMulticastAddress()){
                    result.add(inetAddr);
                }
            }
        }

        return result;
    }

    /**
     * @title getMacByInetAddress
     * @description 获取某个网络接口的Mac地址
     * @author Administrator
     * @updateTime 2022/4/30 0030 18:25
     */
    protected String getMacByInetAddress(InetAddress inetAddr){
        try {
            byte[] mac = NetworkInterface.getByInetAddress(inetAddr).getHardwareAddress();
            StringBuffer stringBuffer = new StringBuffer();

            for(int i=0;i<mac.length;i++){
                if(i != 0) {
                    stringBuffer.append("-");
                }

                //将十六进制byte转化为字符串
                String temp = Integer.toHexString(mac[i] & 0xff);
                if(temp.length() == 1){
                    stringBuffer.append("0" + temp);
                }else{
                    stringBuffer.append(temp);
                }
            }

            return stringBuffer.toString().toUpperCase();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        return null;
    }

}
