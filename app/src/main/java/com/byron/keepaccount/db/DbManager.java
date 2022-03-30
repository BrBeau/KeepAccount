package com.byron.keepaccount.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.byron.keepaccount.bean.AccountBean;
import com.byron.keepaccount.bean.TypeBean;
import com.byron.keepaccount.constant.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 负责管理数据库的类
 * @author Byron
 * @date 220302
 */
public class DbManager {

    private static SQLiteDatabase db;

    public static String TAG = DbManager.class.getSimpleName();

    /**
     * 初始化数据库
     * @param context
     */
    public static void initDb(Context context){
        DbOpenHelper dbOpenHelper = new DbOpenHelper(context);
        //获取数据库对象
        //gerWritableDatabase会检查写入的数据库是否已经存在，如果不存在会自动调用onCreate的方法进行创建
        db= dbOpenHelper.getWritableDatabase();
    }

    /**
     * 获取数据库里的数据存放到list集合里
     * @param kind 获取的类型，1：收入 0：支出
     * @return
     */
    public static List<TypeBean> getTypeList(int kind){
        List<TypeBean> typeList = new ArrayList<>();
        //读取typeDb里的数据
        String sql = "select * from " + Constant.TYPE_DB_NAME + " where kind = " + kind;
        //rawQuery是用来执行sql的select查询语句，第二个参数是用来设置select语句的占位符
        Cursor cursor = db.rawQuery(sql, null);
        //获取游标里的内容，并将各个字段的内容进行存储之后统一放到list集合里
        Log.d(TAG, "typeDb数据项大小： " + cursor.getCount());
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String typeName = cursor.getString(cursor.getColumnIndex("typeName"));
            int selectImageId = cursor.getInt(cursor.getColumnIndex("selectImageId"));
            int selectedImageId = cursor.getInt(cursor.getColumnIndex("selectedImageId"));
            int kinds = cursor.getInt(cursor.getColumnIndex("kind"));
            TypeBean typeBean = new TypeBean(id, typeName, selectImageId, selectedImageId, kind);
            typeList.add(typeBean);

        }
        return typeList;

    }

    /**
     * 将数据插入到accountDb
     * @param mAccountBean
     */
    public static void insertAccountBeanToDb(AccountBean mAccountBean){
        ContentValues contentValues = new ContentValues();
        contentValues.put("typeName", mAccountBean.getTypeName());
        contentValues.put("beiZhu", mAccountBean.getBeiZhu());
        contentValues.put("time", mAccountBean.getTime());
        contentValues.put("money", mAccountBean.getMoney());
        contentValues.put("year", mAccountBean.getYear());
        contentValues.put("month", mAccountBean.getMonth());
        contentValues.put("day", mAccountBean.getDay());
        contentValues.put("imageId", mAccountBean.getImageId());
        contentValues.put("kind", mAccountBean.getKind());

        db.insert(Constant.ACCOUNT_DB_NAME, null, contentValues);
        Log.d(TAG, "insertAccountBeanToDb money: " + mAccountBean.getMoney());
    }


    /**
     * 从sqlLite中获取相匹配日期的account数据
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static List<AccountBean> getAccountData(int year, int month, int day){
        List<AccountBean> accountList = new ArrayList<>();
        String sql = "select * from " + Constant.ACCOUNT_DB_NAME + " where year=" +year + " and month=" + month +
                " and day=" + day + " order by id desc";
        Log.d(TAG, " 查询语句sql： " + sql);
        Cursor cursor = db.rawQuery(sql, null);
        Log.d(TAG, " 查询后数据的大小： " + cursor.getCount());
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String typeName = cursor.getString(cursor.getColumnIndex("typeName"));
            String beiZhu = cursor.getString(cursor.getColumnIndex("beiZhu"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            int imageId = cursor.getInt(cursor.getColumnIndex("imageId"));
            float money = cursor.getFloat(cursor.getColumnIndex("money"));
            int kind = cursor.getInt(cursor.getColumnIndex("kind"));
            AccountBean accountBean = new AccountBean(id, typeName, beiZhu, time, money, year, month, day, imageId, kind);
            accountList.add(accountBean);
        }

        return accountList;
    }

    /**
     * 获取某一天的收入或支出金额
     * @param year
     * @param month
     * @param day
     * @param kind   1:收入     0：支出
     * @return
     */
    public static float getSumMoneyByDay(int year, int month, int day, int kind){
        float sum = 0.0f;
        String sql = "select sum(money) from " + Constant.ACCOUNT_DB_NAME + " where year=" + year +
                " and month=" + month + " and day=" + day + " and kind=" + kind;
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            float money = cursor.getFloat(cursor.getColumnIndex("sum(money)"));
            sum = money;
        }

        return sum;

    }

    /**
     * 获取某个月的收入或支出的金额
     * @param year
     * @param month
     * @param kind 1：收入    0：支出
     * @return
     */
    public static float getSumMoneyByMonth(int year, int month, int kind){
        float sum = 0.0f;
        String sql = "select sum(money) from " + Constant.ACCOUNT_DB_NAME + " where year=" + year +
                " and month=" + month + " and kind=" + kind;
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            float money = cursor.getFloat(cursor.getColumnIndex("sum(money)"));
            sum = money;
        }

        return sum;
    }

}
