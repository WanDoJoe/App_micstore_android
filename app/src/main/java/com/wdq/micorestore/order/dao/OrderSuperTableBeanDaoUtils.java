package com.wdq.micorestore.order.dao;

import android.content.Context;
import android.util.Log;

import com.wdq.micorestore.DAO.DaoManager;
import com.wdq.micorestore.order.bean.OrderSuperTableBean;
import com.wdq.micorestore.order.bean.OrderSuperTableBeanDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by sinosoft_wan on 2018/11/9.
 */

public class OrderSuperTableBeanDaoUtils {
    private static final String TAG = OrderSuperTableBeanDaoUtils.class.getSimpleName();
    private DaoManager mManager;

    public OrderSuperTableBeanDaoUtils(Context context){
        mManager = DaoManager.getInstance();
        mManager.init(context);
    }

    /**
     * 完成meizi记录的插入，如果表未创建，先创建Meizi表
     * @param bean
     * @return
     */
    public boolean insertByOne(OrderSuperTableBean bean){
        boolean flag = false;
        flag = mManager.getDaoSession().getOrderSuperTableBeanDao().insert(bean)

                == -1 ? false : true;//.getMeiziDao().insert(meizi) == -1 ? false : true;
        Log.i(TAG, "insert OrderSubMenu :" + flag + "-->" + bean.toString());
        return flag;
    }

    /**
     * 插入多条数据，在子线程操作
     * @param beanList
     * @return
     */
    public boolean insertList(final List<OrderSuperTableBean> beanList) {
        boolean flag = false;
        try {
            mManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (OrderSuperTableBean bean : beanList) {
                        mManager.getDaoSession().insertOrReplace(bean);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改一条数据
     * @param bean
     * @return
     */
    public boolean updateByOne(OrderSuperTableBean bean){
        boolean flag = false;
        try {
            mManager.getDaoSession().update(bean);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除单条记录
     * @param bean
     * @return
     */
    public boolean deleteByOne(OrderSuperTableBean bean){
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().delete(bean);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除所有记录
     * @return
     */
    public boolean deleteAll(){
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().deleteAll(OrderSuperTableBean.class);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 查询所有记录
     * @return
     */
    public List<OrderSuperTableBean> queryAll(){
        return mManager.getDaoSession().loadAll(OrderSuperTableBean.class);
    }

    /**
     * 根据主键id查询记录
     * @param key
     * @return
     */
    public OrderSuperTableBean queryById(long key){
        return mManager.getDaoSession().load(OrderSuperTableBean.class, key);
    }

    /**
     * 使用native sql进行查询操作
     */
    public List<OrderSuperTableBean> queryByNativeSql(String sql, String[] conditions){
        return mManager.getDaoSession().queryRaw(OrderSuperTableBean.class, sql, conditions);
    }


    /**
     * 使用queryBuilder进行查询
     * @return
     */
    public List<OrderSuperTableBean> queryByQueryBuilder(long id){
        QueryBuilder<OrderSuperTableBean> queryBuilder = mManager.getDaoSession().queryBuilder(OrderSuperTableBean.class);
        return queryBuilder.where(OrderSuperTableBeanDao.Properties.Id.eq(id)).list();
    }
}
