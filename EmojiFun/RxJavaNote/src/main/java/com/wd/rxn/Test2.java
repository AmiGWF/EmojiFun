package com.wd.rxn;

/**
 * Author : wudu
 * Time : 2019/3/10.
 * Tips :
 */
public abstract class Test2 {

    public abstract Test2  a2();

    public static String b2(){return "";}

    public final int c2(){return  1;}

    public static  Test2 create(String a){
        return Util.get(new Test3(a));
    }


}
