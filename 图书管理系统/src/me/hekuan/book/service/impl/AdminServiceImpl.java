package me.hekuan.book.service.impl;

import me.hekuan.book.dbc.DatabaseConnection;
import me.hekuan.book.factory.DAOFactory;
import me.hekuan.book.service.IAdminService;
import me.hekuan.book.vo.Admin;

public class AdminServiceImpl implements IAdminService {
    private DatabaseConnection dbc = new DatabaseConnection();

    @Override
    public boolean login(Admin vo) throws Exception {
        try {
            if (DAOFactory.getIAdminDAOInstance(this.dbc.getConn()).findLogin(vo)) {
                return DAOFactory.getIAdminDAOInstance(this.dbc.getConn()).doUpdateByLastDate(vo.getAid());
            }
            return false;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean changeAdminPassword(Admin vo) throws Exception {
        try {
            return DAOFactory.getIAdminDAOInstance(this.dbc.getConn()).changeAdminPassword(vo);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean deleteByPid(Integer pid) throws Exception {
        try {
            return DAOFactory.getIAdminDAOInstance(this.dbc.getConn()).deleteByPid(pid);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }
}
