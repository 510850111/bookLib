package me.hekuan.util.test;

import me.hekuan.util.MD5Code;

/**
 * Created by Oscar on 2017/5/25.
 */
public class TestMD5Code {
    public static void main(String[] args){
        System.out.println(new MD5Code().getMD5ofStr("adminadmin"));
    }
}
