package com.zty.onlineedu.common.base.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * @Author zty
 * @Date 2023/4/11 23:52
 * 随机数工具类
 */
public class RandomUtils {

    private static final Random random = new Random();
    // 设置数字的位数为4位，若不足4位则补充为0
    private static final DecimalFormat fourdf = new DecimalFormat("0000");

    // 设置数字的位数为6位，若不足6位则补充为0
    private static final DecimalFormat sixdf = new DecimalFormat("000000");

    /**
     * 随机返回四位数的字符串
     * @return 四位数的字符串
     */
    public static String getFourBitRandom() {
        //random.nextInt(10000) 伪随机数 从0到9999 包含0  如：0023, 0425等
        return fourdf.format(random.nextInt(10000));
    }

    /**
     * 随机返回六位数的字符串
     * @return 六位数的字符串
     */
    public static String getSixBitRandom() {
        //random.nextInt(1000000) 伪随机数 从0到999999 包含0  如：000023, 000425等
        return sixdf.format(random.nextInt(1000000));
    }

    /**
     * 给定数组，抽取n个数据
     * @param list
     * @param n
     * @return
     */
    public static ArrayList getRandom(List list, int n) {

        Random random = new Random();

        HashMap<Object, Object> hashMap = new HashMap<Object, Object>();

        // 生成随机数字并存入HashMap
        for (int i = 0; i < list.size(); i++) {

            int number = random.nextInt(100) + 1;

            hashMap.put(number, i);
        }

        // 从HashMap导入数组
        Object[] robjs = hashMap.values().toArray();

        ArrayList r = new ArrayList();

        // 遍历数组并打印数据
        for (int i = 0; i < n; i++) {
            r.add(list.get((int) robjs[i]));
            System.out.print(list.get((int) robjs[i]) + "\t");
        }
        System.out.print("\n");
        return r;
    }
}
