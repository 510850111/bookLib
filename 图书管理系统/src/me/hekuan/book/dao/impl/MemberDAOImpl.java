package me.hekuan.book.dao.impl;

import me.hekuan.book.dao.IMemberDAO;
import me.hekuan.book.vo.Member;
import me.hekuan.util.AbstractDAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class MemberDAOImpl extends AbstractDAOImpl implements IMemberDAO {
    public MemberDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public boolean doCreate(Member vo) throws SQLException {
        String sql = "INSERT INTO member(mid,name,age,sex,phoneNumber) VALUES(?,?,?,?,?)";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1,vo.getMid());
        super.pstmt.setString(2,vo.getName());
        super.pstmt.setInt(3,vo.getAge());
        super.pstmt.setInt(4,vo.getSex());
        super.pstmt.setString(5,vo.getPhoneNumber());
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doUpdate(Member vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doRemove(Set<?> ids) throws SQLException {
        return false;
    }

    @Override
    public Member findById(String id) throws SQLException {
        Member vo = null;
        String sql  = "SELECT mid,name,age,sex,phoneNumber FROM member WHERE mid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1,id);
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()){
            vo = new Member();
            vo.setMid(rs.getString(1));
            vo.setName(rs.getString(2));
            vo.setAge(rs.getInt(3));
            vo.setSex(rs.getInt(4));
            vo.setPhoneNumber(rs.getString(5));
        }
        return vo;
    }

    @Override
    public List<Member> findAll() throws SQLException {
        return null;
    }

    @Override
    public List<Member> findAllBySplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws SQLException {
        return null;
    }

}
