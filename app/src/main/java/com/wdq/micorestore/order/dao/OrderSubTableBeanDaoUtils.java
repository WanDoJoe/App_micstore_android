package com.wdq.micorestore.order.dao;

import android.content.Context;
import android.util.Log;

import com.wdq.micorestore.DAO.DaoManager;
import com.wdq.micorestore.order.bean.OrderSubTableBean;
import com.wdq.micorestore.order.bean.OrderSubTableBeanDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by sinosoft_wan on 2018/11/9.
 */

public class OrderSubTableBeanDaoUtils {
    private static final String TAG = OrderSubTableBeanDaoUtils.class.getSimpleName();
    private DaoManager mManager;

    public OrderSubTableBeanDaoUtils(Context context){
        mManager = DaoManager.getInstance();
        mManager.init(context);
    }

    /**
     * 完成meizi记录的插入，如果表未创建，先创建Meizi表
     * @param bean
     * @return
     */
    public boolean insertByOne(OrderSubTableBean bean){
        boolean flag = false;
        flag = mManager.getDaoSession().getOrderSubTableBeanDao().insert(bean)

                == -1 ? false : true;//.getMeiziDao().insert(meizi) == -1 ? false : true;
        Log.i(TAG, "insert OrderSubMenu :" + flag + "-->" + bean.toString());
        return flag;
    }

    /**
     * 插入多条数据，在子线程操作
     * @param beanList
     * @return
     */
    public boolean insertList(final List<OrderSubTableBean> beanList) {
        boolean flag = false;
        try {
            mManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (OrderSubTableBean bean : beanList) {
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
    public boolean updateByOne(OrderSubTableBean bean){
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
    public boolean deleteByOne(OrderSubTableBean bean){
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
            mManager.getDaoSession().deleteAll(OrderSubTableBean.class);
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
    public List<OrderSubTableBean> queryAll(){
        return mManager.getDaoSession().loadAll(OrderSubTableBean.class);
    }

    /**
     * 根据主键id查询记录
     * @param key
     * @return
     */
    public OrderSubTableBean queryById(long key){
        return mManager.getDaoSession().load(OrderSubTableBean.class, key);
    }

    /**
     * 使用native sql进行查询操作
     */
    public List<OrderSubTableBean> queryByNativeSql(String sql, String[] conditions){
        return mManager.getDaoSession().queryRaw(OrderSubTableBean.class, sql, conditions);
    }


    /**
     * 使用queryBuilder进行查询
     * @return
     */
    public List<OrderSubTableBean> queryByQueryBuilder(long id){
        QueryBuilder<OrderSubTableBean> queryBuilder = mManager.getDaoSession().queryBuilder(OrderSubTableBean.class);
        return queryBuilder.where(OrderSubTableBeanDao.Properties.Id.eq(id)).list();
    }
}
