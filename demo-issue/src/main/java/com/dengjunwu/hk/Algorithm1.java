package com.dengjunwu.hk;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import java.util.Map;

public class Algorithm1 {

    public static void main(String[] args) {
        Integer[] resources = new Integer[]{
                1,2,3,4,5,7,8,9,10,11,12,13,
                1,2,3,4,5,7,8,9,10,11,12,13,
                1,2,3,4,5,6,7,8,9,10,11,12,13,
                1,3,4,5,6,7,8,9,10,11,12,13
        };
        /**
         * 标记所有的扑克牌
         */
        Map<Integer, Integer> tmp = Maps.newHashMap();
        for (Integer i : resources){
            if (tmp.containsKey(i)){
                Integer value = tmp.get(i);
                tmp.put(i, ++value);
            }else {
                tmp.put(i, 1);
            }
        }
        /**
         * 查找被拿走的扑克牌
         */
        Map<Integer, Integer> result = Maps.newHashMap();
        for (Map.Entry<Integer, Integer> entry : tmp.entrySet()){
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if (value < 4){
                result.put(key, 4 - value);
            }
        }
        System.out.println("拿走的扑克牌及其数量为：" + JSONObject.toJSON(result));
    }
}
