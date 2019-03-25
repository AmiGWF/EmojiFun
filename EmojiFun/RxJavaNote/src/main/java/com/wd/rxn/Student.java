package com.wd.rxn;

import java.util.List;

/**
 * Author : wudu
 * Time : 2019/3/17.
 * Tips :
 */
public class Student {
    public int uid;
    public String name;
    public List<Classes> className;
    public String scode;

    public String getScode() {
        return scode;
    }

    public void setScode(String scode) {
        this.scode = scode;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Classes> getClassName() {
        return className;
    }

    public void setClassName(List<Classes> className) {
        this.className = className;
    }
}
