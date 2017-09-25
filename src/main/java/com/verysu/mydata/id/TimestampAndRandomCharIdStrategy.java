package com.verysu.mydata.id;

import java.util.Random;

/**
 * 时间戳+随机字符生成ID策略
 *
 * @author Cocodroid
 * @create 2017-08-21 11:02
 */
public class TimestampAndRandomCharIdStrategy extends AbstractIdStrategy{

    /**生成随机字符串用的char数组*/
    private static char[] chars = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'n', 'm', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9',
            'A','B','C','D','E','F','G',
            'H','I','J','K','L','M','N',
            'O','P','Q','R','S','T',
            'U','V','W','X','Y','Z'
    };

    @Override
    public String generateId() {
        Random r = new Random();
        StringBuffer randomStr = new StringBuffer("");
        for(int i=0; i<5; i++) {
            randomStr.append(chars[r.nextInt(chars.length)]);
        }
        return System.currentTimeMillis() + randomStr.toString();
    }

}
