package com.xcy.fzbcity.all.utils;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Optional;

public class GetInt {

    /**
     * （1）四舍五入把double转化int整型，0.5进一，小于0.5不进一
     * @param number
     * @return
     */
    public static String getInt(double number){
        BigDecimal bd=new BigDecimal(number).setScale(0, BigDecimal.ROUND_HALF_UP);
        Log.i("长度","size"+bd.toString());
        return bd.toString();
    }


    /**
     * （2）四舍五入把double转化为int类型整数,0.5也舍去,0.51进一
     * @param dou
     * @return
     */
    public static int DoubleFormatInt(Double dou){
        DecimalFormat df = new DecimalFormat("######0"); //四色五入转换成整数
        return Integer.parseInt(df.format(dou));
    }


    /**
     * （3）去掉小数凑整:不管小数是多少，都进一
     * @param number
     * @return
     */
    public static int ceilInt(double number){
        return (int) Math.ceil(number);
    }


    /**
     * 测试
     * @param args
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        System.out.println("getInt============="+getInt(20.5));
        System.out.println("DoubleFormatInt=========="+DoubleFormatInt(20.5));
        System.out.println("ceilInt================="+ceilInt(20.01));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Optional<Integer> StringToInt(String s){
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

}
