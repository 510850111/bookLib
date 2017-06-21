package me.hekuan.book.service;

import me.hekuan.book.vo.Item;

import java.util.List;

/**
 * Created by Oscar on 2017/6/5.
 */
public interface IItemService {
    /**
     * 实现分类的增加操作
     *
     * @param vo 表示要执行的对象
     * @return 成功返回true 失败返回false
     * @throws Exception
     */
    public boolean insert(Item vo) throws Exception;

    /**
     * 定义数据全部查询操作接口
     * @return
     * @throws Exception
     */
    public List<Item> list() throws Exception;


    /**
     * 根据iid删除数据
     * @param iid
     * @return
     * @throws Exception
     */
    public boolean deleteByIid(Integer iid) throws Exception;

    /**
     * 更新数据
     * @param vo
     * @return
     * @throws Exception
     */
    public boolean doUpdate(Item vo) throws Exception;
}
