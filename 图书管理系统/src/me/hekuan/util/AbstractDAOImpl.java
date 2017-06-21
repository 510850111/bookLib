package me.hekuan.util;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * 实现dao接口中的一些公共方法
 * Created by Oscar on 2017/5/25.
 */
public abstract class AbstractDAOImpl {
    protected Connection conn;
    protected PreparedStatement pstat;
    public PreparedStatement pstmt;

    public AbstractDAOImpl(Connection conn){
        this.conn = conn;
    }
}
