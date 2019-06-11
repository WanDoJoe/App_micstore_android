package com.wdq.micorestore.grocery.dao;

import android.content.Context;
import android.util.Log;

import com.wdq.micorestore.DAO.DaoManager;
import com.wdq.micorestore.grocery.bean.GroceryGoodsSizeBean;
import com.wdq.micorestore.grocery.bean.GroceryGoodsSizeBeanDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by sinosoft_wan on 2019-6-11.
 */

public class GroceryGoodsHistorySizeBeanDaoUtils {
    private static final String TAG = GroceryGoodsHistorySizeBeanDaoUtils.class.getSimpleName();
    private DaoManager mManager;

    public GroceryGoodsHistorySizeBeanDaoUtils(Context context){
        mManager = DaoManager.getInstance();
        mManager.init(context);
    }

    /**
     * 完成meizi记录的插入，如果表未创建，先创建Meizi表
     * @param goodsBean
     * @return
     */
    public boolean insertGoods(GroceryGoodsSizeBean goodsBean){
        boolean flag = false;
        flag = mManager.getDaoSession().getGroceryGoodsSizeBeanDao().insert(goodsBean)== -1 ? false : true;//.getMeiziDao().insert(meizi) == -1 ? false : true;
        Log.i(TAG, "insert goodsBean :" + flag + "-->" + goodsBean.toString());
        return flag;
    }

    /**
     * 插入多条数据，在子线程操作
     * @param meiziList
     * @return
     */
    public boolean insertMultMeizi(final List<GroceryGoodsSizeBean> meiziList) {
        boolean flag = false;
        try {
            mManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (GroceryGoodsSizeBean meizi : meiziList) {
                        mManager.getDaoSession().insertOrReplace(meizi);
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
     * @param meizi
     * @return
     */
    public boolean updateMeizi(GroceryGoodsSizeBean meizi){
        boolean flag = false;
        try {
            mManager.getDaoSession().update(meizi);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除单条记录
     * @param meizi
     * @return
     */
    public boolean deleteMeizi(GroceryGoodsSizeBean meizi){
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().delete(meizi);
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
            mManager.getDaoSession().deleteAll(GroceryGoodsSizeBean.class);
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
    public List<GroceryGoodsSizeBean> queryAllMeizi(){
        return mManager.getDaoSession().loadAll(GroceryGoodsSizeBean.class);
    }

    /**
     * 根据主键id查询记录
     * @param key
     * @return
     */
    public GroceryGoodsSizeBean queryMeiziById(long key){
        return mManager.getDaoSession().load(GroceryGoodsSizeBean.class, key);
    }

    /**
     * 使用native sql进行查询操作
     */
    public List<GroceryGoodsSizeBean> queryMeiziByNativeSql(String sql, String[] conditions){
        return mManager.getDaoSession().queryRaw(GroceryGoodsSizeBean.class, sql, conditions);
    }


    /**
     * 使用queryBuilder进行查询
     * @return
     */
    public List<GroceryGoodsSizeBean> queryMeiziByQueryBuilder(long id){
        QueryBuilder<GroceryGoodsSizeBean> queryBuilder = mManager.getDaoSession().queryBuilder(GroceryGoodsSizeBean.class);
        return queryBuilder.where(GroceryGoodsSizeBeanDao.Properties.Id.eq(id)).list();
    }
}
