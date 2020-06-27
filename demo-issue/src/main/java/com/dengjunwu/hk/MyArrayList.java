package com.dengjunwu.hk;

public class MyArrayList {
    /**
     * Object类型的数组
     */
    Object[] data ;
    /**
     * 统计数组中元素的数量
     */
    int size;

    public MyArrayList() {
        //初始化长度为10
        this(10);
    }
    MyArrayList(int length){
        //通过构造方法指定数组的长度
        data = new Object[length];
    }

    public int getLength(){
        return size;
    }

    void add(Object obj){
        if(size >= data.length){
            //构建一个新的数组，容量默认增加10
            Object[] newdata = new Object[data.length+10];
            //将原来的数组内容拷贝到扩容后的数组中
            System.arraycopy(data, 0, newdata, 0, size);
        }
        //将新增的元素添加在数组的末尾
        data[size] = obj;
        //数组真实长度自增1
        size++;
    }

    //查找指定索引处的元素;
    public Object get(int index){
        if(index<0||index>size){
            throw new ArrayIndexOutOfBoundsException("数组越界了，索引范围是：0~"+(size-1));
        }
        return data[index];
    }
    //删除指定索引处的元素
    public void delete(int index){
        if(index < 0|| index > size){
            throw new ArrayIndexOutOfBoundsException("数组越界了，索引范围是：0~"+(size-1));
        }
        System.arraycopy(data, index+1, data, index, size-index-1);
        size--;
    }
}
