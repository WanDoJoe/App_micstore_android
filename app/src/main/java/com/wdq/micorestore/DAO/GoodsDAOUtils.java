package com.wdq.micorestore.DAO;

import android.content.Context;
import android.util.Log;

import com.wdq.micorestore.bean.GoodsBean;
import com.wdq.micorestore.bean.GoodsBeanDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by sinosoft_wan on 2018/11/1.
 */

public class GoodsDAOUtils {
    private static final String TAG = GoodsDAOUtils.class.getSimpleName();
    private DaoManager mManager;

    public GoodsDAOUtils(Context context){
        mManager = DaoManager.getInstance();
        mManager.init(context);
    }

    /**
     * 完成meizi记录的插入，如果表未创建，先创建Meizi表
     * @param goodsBean
     * @return
     */
    public boolean insertGoods(GoodsBean goodsBean){
        boolean flag = false;
        flag = mManager.getDaoSession().getGoodsBeanDao().insert(goodsBean)== -1 ? false : true;//.getMeiziDao().insert(meizi) == -1 ? false : true;
        Log.i(TAG, "insert goodsBean :" + flag + "-->" + goodsBean.toString());
        return flag;
    }

    /**
     * 插入多条数据，在子线程操作
     * @param meiziList
     * @return
     */
    public boolean insertMultMeizi(final List<GoodsBean> meiziList) {
        boolean flag = false;
        try {
            mManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (GoodsBean meizi : meiziList) {
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
    public boolean updateMeizi(GoodsBean meizi){
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
    public boolean deleteMeizi(GoodsBean meizi){
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
            mManager.getDaoSession().deleteAll(GoodsBean.class);
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
    public List<GoodsBean> queryAllMeizi(){
        return mManager.getDaoSession().loadAll(GoodsBean.class);
    }

    /**
     * 根据主键id查询记录
     * @param key
     * @return
     */
    public GoodsBean queryMeiziById(long key){
        return mManager.getDaoSession().load(GoodsBean.class, key);
    }

    /**
     * 使用native sql进行查询操作
     */
    public List<GoodsBean> queryMeiziByNativeSql(String sql, String[] conditions){
        return mManager.getDaoSession().queryRaw(GoodsBean.class, sql, conditions);
    }


    /**
     * 使用queryBuilder进行查询
     * @return
     */
    public List<GoodsBean> queryMeiziByQueryBuilder(long id){
        QueryBuilder<GoodsBean> queryBuilder = mManager.getDaoSession().queryBuilder(GoodsBean.class);
        return queryBuilder.where(GoodsBeanDao.Properties.Id.eq(id)).list();
    }
}
