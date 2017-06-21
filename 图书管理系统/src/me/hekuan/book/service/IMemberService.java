package me.hekuan.book.service;


import me.hekuan.book.vo.Member;

public interface IMemberService {
    /**
     * 实现数据增加操作
     * @param vo 表要执行增加的对象
     * @return
     * @throws Exception
     */
    public boolean insert(Member vo)throws Exception;
}
