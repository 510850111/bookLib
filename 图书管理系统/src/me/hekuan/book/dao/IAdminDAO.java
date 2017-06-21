package me.hekuan.book.dao;

        import me.hekuan.book.vo.Admin;

        import java.sql.SQLException;
        import java.util.Date;

public interface IAdminDAO extends IDAO<String,Admin> {
    /**
     * 实现用户登录检查操作
     * @param vo 表示要执行检查的对象（aid,password,flag）
     * @return 吃饭返回true，失败返回false；
     * @throws java.sql.SQLException
     */
    public boolean findLogin(Admin vo)throws SQLException;

    /**
     * 实现用户数据更新操作
     * @param aid 表示要更新的主键
     * @return
     * @throws java.sql.SQLException
     */
    public boolean doUpdateByLastDate(String aid)throws SQLException;

    /**
     * 实现更改密码操作
     * @param vo
     * @return
     * @throws SQLException
     */
    public boolean changeAdminPassword(Admin vo)throws SQLException;

    /**
     * 实现注销操作
     * @param aid
     * @return
     * @throws SQLException
     */
    public boolean logOut(String aid);

    /**
     *通过pid删除购买记录
     * @param pid
     * @return
     */
    public boolean deleteByPid(Integer pid) throws SQLException;

}
