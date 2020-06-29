package com.dengjunw;

public class BubbleSort {
    public static void main(String[] args) {
        int[] array = new int[]{9,0,3,6,1,7,8};

        for (int i = 0; i < array.length; i++){
            for (int j = 0 ; j < array.length - i - 1; j++){
                if (array[j] > array[j + 1]){
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                }
            }
        }
        for (int i = 0 ; i < array.length; i++){
            System.out.println(array[i]);
        }
    }
}
