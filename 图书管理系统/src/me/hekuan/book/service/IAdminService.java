package me.hekuan.book.service;

import me.hekuan.book.dao.IAdminDAO;
import me.hekuan.book.vo.Admin;

public interface IAdminService {
    /**
     * 实现管理员登录检查操作，调用IAdminDAO接口中的findLogin()方法
     * @param vo 表示要操作的对象，包括aid,password
     * @return 成功返回true,并且将最后一次登录日期传递到页面，失败false
     * @throws Exception
     */
    public boolean login(Admin vo)throws Exception;

    /**
     * 更改管理员的密码
     * @param vo
     * @return
     * @throws Exception
     */
    public boolean changeAdminPassword(Admin vo) throws Exception;

    /**
     * 通过pid删除购买记录
     * @param pid
     * @return
     */
    public boolean deleteByPid(Integer pid) throws Exception;

}
