package com.cy.amodule;


import com.cy.router.AdRequest;
import com.cy.router.RouterBuilder;

/**
 * ************************************************************
 * author：cy
 * version：
 * create：2019/04/30 10:44
 * desc：
 * ************************************************************
 */

public class ARouterBuilderImpl implements RouterBuilder {
    @Override
    public AdRequest buildAdRequest() {
        return new ARequestImpl();
    }

}
