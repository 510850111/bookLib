package me.hekuan.book.factory;


import me.hekuan.book.dao.IAdminDAO;
import me.hekuan.book.dao.IBooksDAO;
import me.hekuan.book.dao.IItemDAO;
import me.hekuan.book.dao.IMemberDAO;
import me.hekuan.book.dao.impl.AdminDAOImpl;
import me.hekuan.book.dao.impl.BooksDAOImpl;
import me.hekuan.book.dao.impl.ItemDAOImpl;
import me.hekuan.book.dao.impl.MemberDAOImpl;

import java.sql.Connection;

public class DAOFactory {
    public static IAdminDAO getIAdminDAOInstance(Connection conn){
        return new AdminDAOImpl(conn);
    }
    public static IMemberDAO getIMemberDAOInstance(Connection conn){
        return  new MemberDAOImpl(conn);
    }
    public static IItemDAO getIItemDAOInstance(Connection conn) { return  new ItemDAOImpl(conn); }
    public static IBooksDAO getIBooksDAOInstance(Connection conn){return  new BooksDAOImpl(conn);}
}
