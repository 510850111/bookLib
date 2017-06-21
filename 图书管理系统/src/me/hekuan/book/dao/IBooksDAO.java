package me.hekuan.book.dao;

import me.hekuan.book.vo.Books;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Oscar on 2017/6/7.
 */
public interface IBooksDAO extends IDAO<Integer,Books> {
    /**
     * 根据bid删除书籍
     * @param bid
     * @return
     */
    public boolean deleteByBid(Integer bid) throws SQLException;

    /**
     * 通过bid购买书籍,同时需要知道是谁买的
     * @param vo,aid
     * @return
     * @throws Exception
     */
    public boolean purchaseByBid(Books vo,String aid) throws Exception;

    /**
     * 通过aid查询购买清单
     * @param aid
     * @return
     * @throws Exception
     */
    public List<Books> purchaseList(String aid) throws Exception;



}
