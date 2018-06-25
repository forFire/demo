
package com.flyer.util;

/**
 * 对配置库config, system等propertiest的文件读取。
 * 
 * @author Ceruto
 * @version 2.0.7
 */
public interface ConfigFile {


    /**
     * 获得MQTT定时任务下发的间隔时间【单位是毫秒】
     * 
     * @return
     */
    public String getInfoSendInterval();

    /**
     * 消息流水每次批处理条数
     * 
     * @return
     */
    public String getInfoSplitLen();

    /**
     * editor生成图片路径
     * 
     * @return
     */
    public String getUeditorImageWriteStr();

    /**
     * editor生成图片路径分割字符
     * 
     * @return
     */
    public String getInfoImageSplitStr();

    /**
     * editor生成图片文件访问路径
     * 
     * @return
     */
    public String getInfoImageReadStr();

    /**
     * editor生成图片压缩宽
     * 
     * @return
     */
    public String getImgCompressWidth();

    /**
     * editor生成图片压缩高
     * 
     * @return
     */
    public String getImgCompressHeight();

    /**
     * editor图片
     * 
     * @return
     */
    public String getReplaceCompressStr();

    /**
     * editor生成html文件写入路径（服务器绝对路径）
     * 
     * @return
     */
    public String getInfoHtmlWriteUrl();

    /**
     * editor生成html文件读取访问路径，存入消息表中url
     * 
     * @return
     */
    public String getInfoHtmlReadUrl();

    /**
     * 生成图片上传路径
     * 
     * @return
     */
    public String getPicPath();

    /**
     * 生成图片上传路径
     * 
     * @return
     */
    public String getPicReadPath();

    /**
     * 生成APK上传路径
     * 
     * @return
     */
    public String getApkPath();

    /**
     * 生成APK服务器地址
     * 
     * @return
     */
    public String getApkServer();

    /**
     * 生成APK上传读取路径
     * 
     * @return
     */
    public String getApkReadPath();
    
    /**
     * 生成html路径
     * 
     * @return
     */
    public String getNewsHtmlWriteUrl();

    /**
     * 读取html路径
     * 
     * @return
     */
    public String getNewsHtmlReadUrl();

    /**
     * 生成html模板路径
     * 
     * @return
     */
    public String getInfoExampleHtml();

    /**
     * 生成html模板路径
     * 
     * @return
     */
    public String getNewsExampleHtml();
    public String getNewsAcExampleHtml();

    /**
     * 获得北京数字认证股份有限公司的证书管理的webappName
     * 
     * @return
     */
    public String getBJCAName();

    /**
     * 获得北京数字认证股份有限公司的证书管理配置明细文件路径
     * 
     * @return
     */
    public String getBJCACofigPath();

    /**
     * 获得 Redis 服务器地址
     * 
     * @Description:
     * @author yanghaipeng
     * @date 2015年8月26日下午7:12:45 --------------------------------------<br>
     * @param
     * @return
     * @throws
     */
    public String getRedisAddr();
    
    /**
     * 大卡用户重置密码短信内容
     * @return
     */
    public String getSmsResetPwd();
    
    /**
     * 删除动态用户提示内容
     * @return
     */
    public String getDynamicDelMsg();
    
    /**
     * 删除评论用户提示内容
     * @return
     */
    public String getCommentDelMsg();
    
    /**
     * 批量短信多少条休息一下
     * @return
     */
    public int getSmsCountSleep();

    /**
     * Apk增量包生产类路径
     * @return
     */
    public String getApkPatchLibPath();

}
