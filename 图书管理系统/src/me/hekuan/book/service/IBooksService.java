package me.hekuan.book.service;

import me.hekuan.book.vo.Books;

import java.util.List;
import java.util.Map;

/**
 * Created by Oscar on 2017/6/7.
 */
public interface IBooksService {
    /**
     * 增加图书详情
     * @param vo 要执行操作的对象
     * @return 成功返回true,失败返回false
     * @throws Exception
     */
    public boolean insert(Books vo) throws Exception;

    /**
     * 此方法表示查询admin表和item表的全部数据
     * @return
     * @throws Exception
     */
    public Map<String,Object> findByAdminAndItem() throws Exception;

    /**
     * 定义分页接口类
     * @param column
     * @param keyWord
     * @param currentPage
     * @param lineSize
     * @return
     * @throws Exception
     */
    public Map<String,Object> listBySplit(String column,String keyWord,int currentPage,int lineSize) throws Exception;

    /**
     * 根据bid删除图书
     * @param bid
     * @return
     */
    public boolean deleteByBid(Integer bid) throws Exception;

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
