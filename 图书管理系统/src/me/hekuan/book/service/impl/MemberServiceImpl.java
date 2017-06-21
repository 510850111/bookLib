package me.hekuan.book.service.impl;

import me.hekuan.book.dbc.DatabaseConnection;
import me.hekuan.book.factory.DAOFactory;
import me.hekuan.book.service.IMemberService;
import me.hekuan.book.vo.Member;

public class MemberServiceImpl implements IMemberService {
    private DatabaseConnection dbc = new DatabaseConnection();
    @Override
    public boolean insert(Member vo) throws Exception {
        try{
            // 表示mid数据不存在
            if(DAOFactory.getIMemberDAOInstance(this.dbc.getConn()).findById(vo.getMid()) == null){
                return DAOFactory.getIMemberDAOInstance(this.dbc.getConn()).doCreate(vo);
            }
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
        return false;
    }
}
