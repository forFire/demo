package com.sinoiov.lhjh.notify.server.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 省编码对应城市
 */
public class ProviceCodeUtil {

    private static final Log logger = LogFactory.getLog(ProviceCodeUtil.class);
    private static Map<String, String> proviceCode = null;

    private static Map<String, String> ios_gray_modules_map = new HashMap<String, String>();

    private ProviceCodeUtil() {

    }
    static{
        synchronized (ios_gray_modules_map) {
            logger.info("[ProviceCodeUtil] 静态初始化Map");
            initToMap();
        }
    }

    /**
     * 根据code获取name
     * @param
     * @return userId
     */
    public static String getNameByCode(String code) {
        if (proviceCode == null || proviceCode.isEmpty()) {
            initToMap();
        }
        logger.debug("根据code获取name"+proviceCode.get("44"));
        return proviceCode.get(code);
    }

    /**
     * 初始化文件信息
     */
    private static void initToMap() {
        try {
            proviceCode = new ConcurrentHashMap<String, String>();
            ResourceBundle bundle = ResourceBundle.getBundle("proviceCode");
            Enumeration<String> enumeration = bundle.getKeys();
            while (enumeration.hasMoreElements()) {
                String key = enumeration.nextElement();
                String value = new String(bundle.getString(key).getBytes("ISO-8859-1"), "UTF-8");
                proviceCode.put(key,value);
            }
            logger.info("ProviceCodeUtil初始化完成，大小："+proviceCode.size());

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[ProviceCodeUtil] 读取weatherCity文件失败");
        }
    }
}
