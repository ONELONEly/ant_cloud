package com.gree.result;

/**
 * The interface Response info.
 *
 * @Description 返回信息接口
 * @Author 艺锦欧巴 【jinyuk@foxmail.com/180484@gree.cn.com】
 * @CreateTime 2019 -04-09 17:19:09
 * @Version V 1.0
 */

public interface ResponseInfo {

    /**
     * Response code integer.
     *
     * @return the integer
     * @Description 返回码
     * @Author 艺锦欧巴 【jinyuk@foxmail.com/180484@gree.cn.com】
     * @CreateTime 2019 -04-09 17:19:09
     * @Version V 1.0
     */
    String getResponseCode();

    /**
     * Response msg string.
     *
     * @return the string
     * @Description 返回信息
     * @Author 艺锦欧巴 【jinyuk@foxmail.com/180484@gree.cn.com】
     * @CreateTime 2019 -04-09 17:19:09
     * @Version V 1.0
     */
    String getResponseMsg();
}
