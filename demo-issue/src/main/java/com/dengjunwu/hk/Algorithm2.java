package com.dengjunwu.hk;

import java.util.Random;

public class Algorithm2 {

    /**
     * 每次确定生成数据的最大范围
     * @param total
     * @param people
     * @return
     */
    static int maxNum(int total, int people){
        int i = 2 * (total / people);
        return i;
    }
    public static void main(String[] args) {
        calResult(5,20);
    }
    public static void calResult(int number, int sum){
        Random rd=new Random();
        int num;
        for(int i = 1; i < number; i++){
            num = rd.nextInt(maxNum(sum,number - i));
            System.out.println(num);
            sum = sum - num;
        }
        System.out.println(sum);
    }
}
