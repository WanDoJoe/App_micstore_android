package com.wdq.micorestore.grocery.dao;

import android.content.Context;
import android.util.Log;

import com.wdq.micorestore.DAO.DaoManager;
import com.wdq.micorestore.grocery.bean.GroceryGoodsHistoryTypeBean;
import com.wdq.micorestore.grocery.bean.GroceryGoodsHistoryTypeBeanDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by sinosoft_wan on 2019-6-11.
 */

public class GroceryGoodsHistoryTypeBeanDaoUtils {
    private static final String TAG = GroceryGoodsHistoryTypeBeanDaoUtils.class.getSimpleName();
    private DaoManager mManager;

    public GroceryGoodsHistoryTypeBeanDaoUtils(Context context){
        mManager = DaoManager.getInstance();
        mManager.init(context);
    }

    /**
     * 完成meizi记录的插入，如果表未创建，先创建Meizi表
     * @param goodsBean
     * @return
     */
    public boolean insertGoods(GroceryGoodsHistoryTypeBean goodsBean){
        boolean flag = false;
        flag = mManager.getDaoSession().getGroceryGoodsHistoryTypeBeanDao().insert(goodsBean)== -1 ? false : true;//.getMeiziDao().insert(meizi) == -1 ? false : true;
        Log.i(TAG, "insert goodsBean :" + flag + "-->" + goodsBean.toString());
        return flag;
    }

    /**
     * 插入多条数据，在子线程操作
     * @param meiziList
     * @return
     */
    public boolean insertMultMeizi(final List<GroceryGoodsHistoryTypeBean> meiziList) {
        boolean flag = false;
        try {
            mManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (GroceryGoodsHistoryTypeBean meizi : meiziList) {
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
    public boolean updateMeizi(GroceryGoodsHistoryTypeBean meizi){
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
    public boolean deleteMeizi(GroceryGoodsHistoryTypeBean meizi){
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
            mManager.getDaoSession().deleteAll(GroceryGoodsHistoryTypeBean.class);
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
    public List<GroceryGoodsHistoryTypeBean> queryAllMeizi(){
        return mManager.getDaoSession().loadAll(GroceryGoodsHistoryTypeBean.class);
    }

    /**
     * 根据主键id查询记录
     * @param key
     * @return
     */
    public GroceryGoodsHistoryTypeBean queryMeiziById(long key){
        return mManager.getDaoSession().load(GroceryGoodsHistoryTypeBean.class, key);
    }

    /**
     * 使用native sql进行查询操作
     */
    public List<GroceryGoodsHistoryTypeBean> queryMeiziByNativeSql(String sql, String[] conditions){
        return mManager.getDaoSession().queryRaw(GroceryGoodsHistoryTypeBean.class, sql, conditions);
    }


    /**
     * 使用queryBuilder进行查询
     * @return
     */
    public List<GroceryGoodsHistoryTypeBean> queryMeiziByQueryBuilder(long id){
        QueryBuilder<GroceryGoodsHistoryTypeBean> queryBuilder = mManager.getDaoSession().queryBuilder(GroceryGoodsHistoryTypeBean.class);
        return queryBuilder.where(GroceryGoodsHistoryTypeBeanDao.Properties.Id.eq(id)).list();
    }
}
