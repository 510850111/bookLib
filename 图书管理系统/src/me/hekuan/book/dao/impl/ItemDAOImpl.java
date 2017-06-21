package me.hekuan.book.dao.impl;

import me.hekuan.book.dao.IItemDAO;
import me.hekuan.book.vo.Item;
import me.hekuan.util.AbstractDAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Oscar on 2017/6/5.
 */
public class ItemDAOImpl  extends AbstractDAOImpl implements IItemDAO{

    public ItemDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public boolean doCreate(Item vo) throws SQLException {
        String sql = "INSERT INTO item(name,note) VALUES(?,?)";
        super.pstat = super.conn.prepareStatement(sql);
        super.pstat.setString(1,vo.getName());
        super.pstat.setString(2,vo.getNote());
        return super.pstat.executeUpdate() > 0;
    }

    @Override
    public boolean doUpdate(Item vo) throws SQLException {
        String sql = "UPDATE item SET name = ?,note = ? WHERE iid = ?";
        super.pstat = super.conn.prepareStatement(sql);
        super.pstat.setInt(3, vo.getIid());
        super.pstat.setString(2,vo.getName());
        super.pstat.setString(1,vo.getNote());
        return super.pstat.executeUpdate() > 0;

    }

    @Override
    public boolean doRemove(Set<?> ids) throws SQLException {
        return false;
    }

    @Override
    public Item findById(Integer id) throws SQLException {
        Item vo = null;
        String sql = "SELECT iid , name , note FORM item WHERE iid = ?";
        super.pstat = super.conn.prepareStatement(sql);
        int one = 1;
        super.pstat.setInt(one,id);
        ResultSet rs  = super.pstat.executeQuery();
        if(rs.next()){
            vo = new Item();
            vo.setIid(rs.getInt(1));
            vo.setName(rs.getString(2));
            vo.setNote(rs.getString(3));
        }
        return vo;
    }

    @Override
    public List<Item> findAll() throws SQLException {
        List<Item> all = new ArrayList<Item>();
        String sql = "SELECT iid , name , note FROM item";
        super.pstat = super.conn.prepareStatement(sql);
        ResultSet rs  = super.pstat.executeQuery();
        while(rs.next()){
            Item vo = new Item();
            vo.setIid(rs.getInt(1));
            vo.setName(rs.getString(2));
            vo.setNote(rs.getString(3));
            all.add(vo);
        }
        return all;
    }

    @Override
    public List<Item> findAllBySplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws SQLException {
        return null;
    }

    public boolean deleteByIid(Integer iid) throws SQLException {
        String sql = "DELETE FROM item WHERE iid = ?";
        super.pstat = super.conn.prepareStatement(sql);
        super.pstat.setInt(1,iid);
        return super.pstat.executeUpdate() > 0;
    }



}
