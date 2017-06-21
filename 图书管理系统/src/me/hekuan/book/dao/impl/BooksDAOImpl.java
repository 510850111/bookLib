package me.hekuan.book.dao.impl;

import me.hekuan.book.dao.IBooksDAO;
import me.hekuan.book.vo.Admin;
import me.hekuan.book.vo.Books;
import me.hekuan.book.vo.Item;
import me.hekuan.util.AbstractDAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Oscar on 2017/6/7.
 */
public class BooksDAOImpl extends AbstractDAOImpl implements IBooksDAO {
    public BooksDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public boolean doCreate(Books vo) throws SQLException {
        String sql = "INSERT INTO books(iid,aid,name,credate,status,note,price) VALUES(?,?,?,?,?,?,?)";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setInt(1,vo.getItem().getIid());
        super.pstmt.setString(2, vo.getAdmin().getAid());
        super.pstmt.setString(3,vo.getName());
        super.pstmt.setTimestamp(4, new Timestamp(vo.getCredate().getTime()));
        super.pstmt.setInt(5,vo.getStatus());
        super.pstmt.setString(6,vo.getNote());
        super.pstmt.setInt(7,vo.getPrice());

        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doUpdate(Books vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doRemove(Set<?> ids) throws SQLException {
        return false;
    }

    @Override
    public Books findById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<Books> findAll() throws SQLException {
        return null;
    }

    @Override
    public List<Books> findAllBySplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws SQLException {
        List<Books> all= new ArrayList<Books>();
        String sql = " SELECT b.bid,b.name,b.credate,b.status ,a.aid,i.name,b.price,b.note" +
                " FROM books b,admin a,item i " +
                " WHERE b.iid = i.iid AND b.aid = a.aid AND b."+ column +" LIKE ? LIMIT ?,? ";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1,"%"+keyWord+"%");
        super.pstmt.setInt(2,(currentPage-1)*lineSize);
        super.pstmt.setInt(3,lineSize);

        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()){
            Books vo = new Books();
            vo.setBid(rs.getInt(1));
            vo.setName(rs.getString(2));
            vo.setCredate(rs.getDate(3));
            vo.setStatus(rs.getInt(4));
            vo.setPrice(rs.getInt(7));
            vo.setNote(rs.getString(8));

            Admin admin = new Admin();
            admin.setAid(rs.getString(5));
            vo.setAdmin(admin);

            Item item = new Item();
            item.setName(rs.getString(6));
            vo.setItem(item);

            all.add(vo);
        }
        return all;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws SQLException {
        //统计全部数据量
        String sql = "SELECT COUNT(*) FROM books WHERE " + column +" LIKE ?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1,"%"+keyWord+"%");
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()){
            return rs.getInt(1);
        }else {
            return 0;
        }
    }

    @Override
    public boolean deleteByBid(Integer bid) throws SQLException {
        String sql = "DELETE FROM books WHERE bid = ?";
        super.pstat = super.conn.prepareStatement(sql);
        super.pstat.setInt(1,bid);
        return super.pstat.executeUpdate() > 0;
    }

    @Override
    public boolean purchaseByBid(Books vo, String aid) throws Exception {
        String sql = "INSERT INTO purchase(bid,aid,price,credate,status,note) VALUES(?,?,?,?,?,?)";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setInt(1,vo.getBid());
        super.pstmt.setString(2, aid);
        super.pstmt.setInt(3, vo.getPrice());
        super.pstmt.setTimestamp(4, new Timestamp(new java.util.Date().getTime()));
        super.pstmt.setInt(5,vo.getStatus());
        super.pstmt.setString(6,vo.getNote());

        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public List<Books> purchaseList(String aid) throws Exception {
        List<Books> all = new ArrayList<Books>();
        String sql = "SELECT bid,price,credate,status,note,pid FROM purchase";
        super.pstmt = super.conn.prepareStatement(sql);
        ResultSet rs  = super.pstmt.executeQuery();
        while(rs.next()){
            Books vo = new Books();
            vo.setBid(rs.getInt(1));
            vo.setPrice(rs.getInt(2));
            vo.setCredate(rs.getDate(3));
            vo.setStatus(rs.getInt(4));
            vo.setNote(rs.getString(5));

            Item item = new Item();
            item.setIid(rs.getInt(6));
            vo.setItem(item);
            all.add(vo);
        }
        return all;
    }

}
