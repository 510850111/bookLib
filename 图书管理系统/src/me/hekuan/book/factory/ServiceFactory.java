package me.hekuan.book.factory;

import me.hekuan.book.dao.IItemDAO;
import me.hekuan.book.service.IAdminService;
import me.hekuan.book.service.IBooksService;
import me.hekuan.book.service.IItemService;
import me.hekuan.book.service.IMemberService;
import me.hekuan.book.service.impl.AdminServiceImpl;
import me.hekuan.book.service.impl.BooksServiceImpl;
import me.hekuan.book.service.impl.IItemServiceImpl;
import me.hekuan.book.service.impl.MemberServiceImpl;

public class ServiceFactory {
    public static IAdminService getIAdminServiceInstance(){
        return  new AdminServiceImpl();
    }

    public static IMemberService getIMemberServiceInstance(){
        return  new MemberServiceImpl();
    }

    public static IItemService getIItemServiceInstance(){return new IItemServiceImpl();}

    public static IBooksService getIBooksServiceInstance(){return new BooksServiceImpl();}
}
