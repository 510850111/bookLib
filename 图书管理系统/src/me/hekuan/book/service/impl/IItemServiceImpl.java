package me.hekuan.book.service.impl;

import me.hekuan.book.dbc.DatabaseConnection;
import me.hekuan.book.factory.DAOFactory;
import me.hekuan.book.service.IItemService;
import me.hekuan.book.vo.Item;

import java.util.List;

/**
 * Created by Oscar on 2017/6/5.
 */
public class IItemServiceImpl implements IItemService {

    private DatabaseConnection dbc = new DatabaseConnection();
    @Override
    public boolean insert(Item vo) throws Exception {
        try{
            return DAOFactory.getIItemDAOInstance(this.dbc.getConn()).doCreate(vo);
        }catch (Exception e ){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public List<Item> list() throws Exception {
        try{
            return DAOFactory.getIItemDAOInstance(this.dbc.getConn()).findAll();
        }catch (Exception e ){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    public boolean deleteByIid(Integer iid) throws Exception {

        try{
            return DAOFactory.getIItemDAOInstance(this.dbc.getConn()).deleteByIid(iid);
        }catch (Exception e ){

            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean doUpdate(Item vo) throws Exception {
        try{
            return DAOFactory.getIItemDAOInstance(this.dbc.getConn()).doUpdate(vo);
        }catch (Exception e ){
            throw e;
        }finally {
            this.dbc.close();
        }
    }
}
