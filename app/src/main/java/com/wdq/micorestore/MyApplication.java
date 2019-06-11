package com.wdq.micorestore;

import android.app.Application;

//import com.uuzuche.lib_zxing.ZApplication;
//import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.wdq.micorestore.DAO.DaoManager;
import com.wdq.micorestore.bean.UserBean;
import com.wdq.micorestore.order.bean.OrderUserBean;
import com.wdq.micorestore.order.dao.OrderUserBeanDaoUtils;
import com.wdq.micorestore.utils.DateUtil;

/**
 * Created by sinosoft_wan on 2018/9/20.
 */

public class MyApplication extends Application {
    public OrderUserBean myUserBean;
    private static MyApplication mApp;
    OrderUserBeanDaoUtils orderUserBeanDaoUtils;
    @Override
    public void onCreate() {
        super.onCreate();
        mApp=this;
//        ZXingLibrary.initDisplayOpinion(this);
        DaoManager.getInstance().init(this);

        orderUserBeanDaoUtils=new OrderUserBeanDaoUtils(this);
        if(orderUserBeanDaoUtils.queryAll().size()<1) {
            OrderUserBean userBean = new OrderUserBean();
            userBean.setDate(DateUtil.geDate());
            userBean.setId(1L);
            userBean.setUsername("admin");
            userBean.setPassword("admin");
            userBean.setName("管理员");
            userBean.setRole("admin");
            userBean.setHeaderImg("aaaaa");
            orderUserBeanDaoUtils.insertByOne(userBean);
        }
    }

    public static MyApplication sharedApp() {
        return mApp;
    }

    public OrderUserBeanDaoUtils getOrderUserBeanDaoUtils() {
        return orderUserBeanDaoUtils;
    }

    public void setOrderUserBeanDaoUtils(OrderUserBeanDaoUtils orderUserBeanDaoUtils) {
        this.orderUserBeanDaoUtils = orderUserBeanDaoUtils;
    }

    public OrderUserBean getMyUserBean() {
        return myUserBean;
    }

    public void setMyUserBean(OrderUserBean myUserBean) {
        this.myUserBean = myUserBean;
    }
}
