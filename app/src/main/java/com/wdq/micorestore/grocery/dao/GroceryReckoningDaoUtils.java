package com.wdq.micorestore.grocery.dao;

import android.content.Context;
import android.util.Log;

import com.wdq.micorestore.DAO.DaoManager;
import com.wdq.micorestore.grocery.bean.GroceryReckoning;
import com.wdq.micorestore.grocery.bean.GroceryReckoningDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by sinosoft_wan on 2019-6-12.
 */

public class GroceryReckoningDaoUtils {
    private static final String TAG = GroceryReckoningDaoUtils.class.getSimpleName();
    private DaoManager mManager;

    public GroceryReckoningDaoUtils(Context context){
        mManager = DaoManager.getInstance();
        mManager.init(context);
    }

    /**
     * 完成meizi记录的插入，如果表未创建，先创建Meizi表
     * @param bean
     * @return
     */
    public boolean insertGoods(GroceryReckoning bean){
        boolean flag = false;
        flag = mManager.getDaoSession().getGroceryReckoningDao().insert(bean)
                == -1 ? false : true;
        Log.i(TAG, "insert goodsBean :" + flag + "-->" + bean.toString());
        return flag;
    }

    /**
     * 插入多条数据，在子线程操作
     * @param meiziList
     * @return
     */
    public boolean insertMultMeizi(final List<GroceryReckoning> meiziList) {
        boolean flag = false;
        try {
            mManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (GroceryReckoning meizi : meiziList) {
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
    public boolean updateMeizi(GroceryReckoning meizi){
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
    public boolean deleteMeizi(GroceryReckoning meizi){
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
            mManager.getDaoSession().deleteAll(GroceryReckoning.class);
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
    public List<GroceryReckoning> queryAllMeizi(){
        return mManager.getDaoSession().loadAll(GroceryReckoning.class);
    }

    /**
     * 根据主键id查询记录
     * @param key
     * @return
     */
    public GroceryReckoning queryMeiziById(long key){
        return mManager.getDaoSession().load(GroceryReckoning.class, key);
    }

    /**
     * 使用native sql进行查询操作
     */
    public List<GroceryReckoning> queryMeiziByNativeSql(String sql, String[] conditions){
        return mManager.getDaoSession().queryRaw(GroceryReckoning.class, sql, conditions);
    }


    /**
     * 使用queryBuilder进行查询
     * @return
     */
    public List<GroceryReckoning> queryMeiziByQueryBuilder(long id){
        QueryBuilder<GroceryReckoning> queryBuilder = mManager.getDaoSession().queryBuilder(GroceryReckoning.class);
        return queryBuilder.where(GroceryReckoningDao.Properties.Id.eq(id)).list();
    }
}
