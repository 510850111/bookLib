package me.hekuan.book.service.impl;

import me.hekuan.book.dbc.DatabaseConnection;
import me.hekuan.book.factory.DAOFactory;
import me.hekuan.book.service.IBooksService;
import me.hekuan.book.vo.Books;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据增加操作
 * Created by Oscar on 2017/6/7.
 */
public class BooksServiceImpl implements IBooksService {
    private DatabaseConnection dbc = new DatabaseConnection();

    @Override
    public boolean insert(Books vo) throws Exception {
        try {
            return DAOFactory.getIBooksDAOInstance(this.dbc.getConn()).doCreate(vo);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean deleteByBid(Integer bid) throws Exception {
        try {
            return DAOFactory.getIBooksDAOInstance(this.dbc.getConn()).deleteByBid(bid);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean purchaseByBid(Books vo, String aid) throws Exception {
        try {
            return DAOFactory.getIBooksDAOInstance(this.dbc.getConn()).purchaseByBid(vo, aid);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public List<Books> purchaseList(String aid) throws Exception {
        try {
            return DAOFactory.getIBooksDAOInstance(this.dbc.getConn()).purchaseList(aid);
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public Map<String, Object> findByAdminAndItem() throws Exception {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("allAdmins", DAOFactory.getIAdminDAOInstance(this.dbc.getConn()).findAll());
            map.put("allItems", DAOFactory.getIItemDAOInstance(this.dbc.getConn()).findAll());
            return map;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public Map<String, Object> listBySplit(String column, String keyWord, int currentPage, int lineSize) throws Exception {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("allBooks", DAOFactory.getIBooksDAOInstance(this.dbc.getConn()).findAllBySplit(column, keyWord, currentPage, lineSize));
            map.put("allCounts", DAOFactory.getIBooksDAOInstance(this.dbc.getConn()).getAllCount(column, keyWord));
            return map;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }


}
