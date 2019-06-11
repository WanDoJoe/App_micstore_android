package com.wdq.micorestore.grocery.dao;

import android.content.Context;
import android.util.Log;

import com.wdq.micorestore.DAO.DaoManager;
import com.wdq.micorestore.grocery.bean.GroceryGoods;
import com.wdq.micorestore.grocery.bean.GroceryGoodsDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by sinosoft_wan on 2019-6-11.
 */

public class GroceryGoodsDaoUtils {
    private static final String TAG = GroceryGoodsDaoUtils.class.getSimpleName();
    private DaoManager mManager;

    public GroceryGoodsDaoUtils(Context context){
        mManager = DaoManager.getInstance();
        mManager.init(context);
    }

    /**
     * 完成meizi记录的插入，如果表未创建，先创建Meizi表
     * @param bean
     * @return
     */
    public boolean insertGoods(GroceryGoods bean){
        boolean flag = false;
        flag = mManager.getDaoSession().getGroceryGoodsDao().insert(bean)
                == -1 ? false : true;
        Log.i(TAG, "insert goodsBean :" + flag + "-->" + bean.toString());
        return flag;
    }

    /**
     * 插入多条数据，在子线程操作
     * @param meiziList
     * @return
     */
    public boolean insertMultMeizi(final List<GroceryGoods> meiziList) {
        boolean flag = false;
        try {
            mManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (GroceryGoods meizi : meiziList) {
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
    public boolean updateMeizi(GroceryGoods meizi){
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
    public boolean deleteMeizi(GroceryGoods meizi){
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
            mManager.getDaoSession().deleteAll(GroceryGoods.class);
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
    public List<GroceryGoods> queryAllMeizi(){
        return mManager.getDaoSession().loadAll(GroceryGoods.class);
    }

    /**
     * 根据主键id查询记录
     * @param key
     * @return
     */
    public GroceryGoods queryMeiziById(long key){
        return mManager.getDaoSession().load(GroceryGoods.class, key);
    }

    /**
     * 使用native sql进行查询操作
     */
    public List<GroceryGoods> queryMeiziByNativeSql(String sql, String[] conditions){
        return mManager.getDaoSession().queryRaw(GroceryGoods.class, sql, conditions);
    }


    /**
     * 使用queryBuilder进行查询
     * @return
     */
    public List<GroceryGoods> queryMeiziByQueryBuilder(long id){
        QueryBuilder<GroceryGoods> queryBuilder = mManager.getDaoSession().queryBuilder(GroceryGoods.class);
        return queryBuilder.where(GroceryGoodsDao.Properties.Id.eq(id)).list();
    }
}
