package me.hekuan.book.dao;

import me.hekuan.book.vo.Item;

import java.sql.SQLException;


/**
 * Created by Oscar on 2017/6/5.
 */
public interface IItemDAO extends IDAO<Integer,Item> {

    public boolean deleteByIid(Integer iid) throws SQLException;



}
