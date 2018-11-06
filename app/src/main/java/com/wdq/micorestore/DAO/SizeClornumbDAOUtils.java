package com.wdq.micorestore.DAO;

import android.content.Context;
import android.util.Log;

import com.wdq.micorestore.bean.SizeClorNumbBean;
import com.wdq.micorestore.bean.SizeClorNumbBeanDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by sinosoft_wan on 2018/11/1.
 */

public class SizeClornumbDAOUtils {
    private static final String TAG = SizeClornumbDAOUtils.class.getSimpleName();
    private DaoManager mManager;

    public SizeClornumbDAOUtils(Context context){
        mManager = DaoManager.getInstance();
        mManager.init(context);
    }

    /**
     * 完成meizi记录的插入，如果表未创建，先创建Meizi表
     * @param goodsBean
     * @return
     */
    public boolean insertGoods(SizeClorNumbBean goodsBean){
        boolean flag = false;
        flag = mManager.getDaoSession().getSizeClorNumbBeanDao().insert(goodsBean)
                == -1 ? false : true;//.getMeiziDao().insert(meizi) == -1 ? false : true;
        Log.i(TAG, "insert goodsBean :" + flag + "-->" + goodsBean.toString());
        return flag;
    }

    /**
     * 插入多条数据，在子线程操作
     * @param meiziList
     * @return
     */
    public boolean insertMultMeizi(final List<SizeClorNumbBean> meiziList) {
        boolean flag = false;
        try {
            mManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (SizeClorNumbBean meizi : meiziList) {
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
    public boolean updateMeizi(SizeClorNumbBean meizi){
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
    public boolean deleteMeizi(SizeClorNumbBean meizi){
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
            mManager.getDaoSession().deleteAll(SizeClorNumbBean.class);
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
    public List<SizeClorNumbBean> queryAllMeizi(){
        return mManager.getDaoSession().loadAll(SizeClorNumbBean.class);
    }

    /**
     * 根据主键id查询记录
     * @param key
     * @return
     */
    public SizeClorNumbBean queryMeiziById(long key){
        return mManager.getDaoSession().load(SizeClorNumbBean.class, key);
    }

    /**
     * 使用native sql进行查询操作
     */
    public List<SizeClorNumbBean> queryMeiziByNativeSql(String sql, String[] conditions){
        return mManager.getDaoSession().queryRaw(SizeClorNumbBean.class, sql, conditions);
    }


    /**
     * 使用queryBuilder进行查询
     * @return
     */
    public List<SizeClorNumbBean> queryMeiziByQueryBuilder(long id){
        QueryBuilder<SizeClorNumbBean> queryBuilder = mManager.getDaoSession().queryBuilder(SizeClorNumbBean.class);
        return queryBuilder.where(SizeClorNumbBeanDao.Properties.Id.eq(id)).list();
    }
}
