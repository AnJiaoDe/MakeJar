package com.cy.router;

/**
 * ************************************************************
 * author：cy
 * version：
 * create：2019/04/30 10:45
 * desc：// * desc：建造者模式，此出router使用接口是为了反射方便，只需反射实现类的构造方法，调用method时无需反射
 * <p>
 * ************************************************************
 */

public interface RouterBuilder {
    public AdRequest buildAdRequest();
}
