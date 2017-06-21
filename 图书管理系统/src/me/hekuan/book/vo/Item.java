package me.hekuan.book.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Oscar on 2017/6/5.
 */
public class Item implements Serializable {
    private Integer iid;
    private  String name;
    private  String note;

    public List<Books> getBook() {
        return book;
    }

    public void setBook(List<Books> book) {
        this.book = book;
    }

    private List<Books> book;//y一个类别下有多本书


    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
