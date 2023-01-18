package com.zty.onlineedu.common.base.utils;

import java.util.Objects;
import java.util.UUID;

/**
 * @Author zty
 * @Date 2023/1/18 21:22
 * uuid生成生成工具*
 */
public class UUIDUtils {
    public UUIDUtils() {

    }

    /**
     * 生成原始的随机uuid值(中间会有-分割 例子：1e070852-3d5f-4498-8a5d-cc897b09e9f9)
     *
     * @return
     */
    public static UUID getUUID() {
        return UUID.randomUUID();

    }

    /**
     * 生成字符串类型的原始的随机uuid值 (中间会有-分割 例子：”1e070852-3d5f-4498-8a5d-cc897b09e9f9“)
     *
     * @return
     */
    public static String getUUIDString() {
        return getUUID().toString();

    }

    /**
     * 生成最常用的32位的uuid，没有-分割 (例子：”1e0708523d5f44988a5dcc897b09e9f9“)
     *
     * @return
     */
    public static String getUUID32() {
        return getUUIDString().replace("-", "");

    }

    /**
     * 将一个字符串转换成 UUID 对象。该方法接受一个字符串参数，该字符串应该是一个符合 UUID 格式的字符串。
     * 如果传入的字符串不是一个符合 UUID 格式的字符串，则会抛出一个 IllegalArgumentException 异常。
     *
     * @param uuidString
     * @return
     */
    public static UUID parse(String uuidString) {
        return UUID.fromString(uuidString);
    }

    /**
     * 比较两个UUID对象是否一样
     * @param u1
     * @param u2
     * @return
     */
    public static boolean equalsUUID(UUID u1, UUID u2) {
        return Objects.equals(u1, u2);
    }
}
