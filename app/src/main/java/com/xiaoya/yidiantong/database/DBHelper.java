package com.xiaoya.yidiantong.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.util.Log;

import com.apkfuns.logutils.LogUtils;
import com.smartydroid.android.starter.kit.utilities.ACache;
import com.xiaoya.yidiantong.model.Question;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Author: meyu
 * Date:   16/4/23
 * Email:  627655140@qq.com
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String LOG_TAG = "DataHelper";

    private SQLiteDatabase mDataBase;
    private final Context mContext;
    private static DBHelper instance = null;
    private static String DATABASE_PATH = "";
    private static final String DATABASE_NAME = "jiaxiao.db";
    private static final int DATABASE_VERSION = 1;

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;

    }

    private DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
        this.mContext = context;
    }

    public static DBHelper getInstance(Context context){
        if(instance == null){
            instance = new DBHelper(context, getDatabasePath(context), null, DATABASE_VERSION);
        }
        return instance;
    }

    private static String getDatabasePath(Context context){
        return  DATABASE_PATH = context.getDatabasePath(DATABASE_NAME).getPath();
    }

    /**
     * Creates a empty database on the system and rewrites it with your own
     * database.
     */
    public void createDataBase(){

        boolean dbExist = checkDataBase();

        Log.d(LOG_TAG, "dbExist: " + dbExist);

        if (dbExist) {
            // do nothing - database already exist
        } else {
            // By calling this method and empty database will be created into
            // the default system path
            // of your application so we are gonna be able to overwrite that
            // database with our database.
            this.getReadableDatabase();
            copyDataBase();
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each
     * time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {
        Log.d(LOG_TAG, "checkDataBase");
        SQLiteDatabase checkDB = null;

        try {
            String myPath = DATABASE_PATH;
            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            // database does't exist yet.
        }

        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null;
    }

    /**
     * Copies your database from your local assets-folder to the just created
     * empty database in the system folder, from where it can be accessed and
     * handled. This is done by transfering bytestream.
     */
    private void copyDataBase() {

        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();

        Log.d(LOG_TAG, "copyDataBase");
        // Open your local db as the input stream
        InputStream myInput = null;
        OutputStream myOutput = null;
        try {
            myInput = mContext.getAssets().open(DATABASE_NAME);


            // Path to the just created empty db
            String outFileName = DATABASE_PATH;
            LogUtils.d("outFileName",outFileName);
            // Open the empty db as the output stream
            myOutput = new FileOutputStream(outFileName);

            // transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close the streams
            try {
                if (myOutput != null) {
                    myOutput.flush();
                }
                if (myOutput != null) {
                    myOutput.close();
                }
                if (myInput != null) {
                    myInput.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 把数据写入到另一个数据库里面去
     */
    public void initLitPalDatabase(){
        openDataBase();
        Cursor c = getReadableDatabase().rawQuery("",new String[]{});
        if(c.moveToFirst()){
            while (c.moveToNext()){
                String analysis = c.getString(c.getColumnIndex("analysis"));
                String difficylty = c.getString(c.getColumnIndex("difficylty"));
                int id = c.getInt(c.getColumnIndex("id"));
                int kem = c.getInt(c.getColumnIndex("kem"));
                int media_type = c.getInt(c.getColumnIndex("media_type"));
                int question_type = c.getInt(c.getColumnIndex("question_type"));
                String down = c.getString(c.getColumnIndex("down"));
                String cx = c.getString(c.getColumnIndex("cx"));
                String media_content = c.getString(c.getColumnIndex("media_content"));
                String option_a = c.getString(c.getColumnIndex("option_a"));
                String option_b = c.getString(c.getColumnIndex("option_b"));
                String option_c = c.getString(c.getColumnIndex("option_c"));
                String option_d = c.getString(c.getColumnIndex("option_d"));
                String probability = c.getString(c.getColumnIndex("probability"));
                String question = c.getString(c.getColumnIndex("question"));
                String question_category = c.getString(c.getColumnIndex("question_category"));
                String rightOption = c.getString(c.getColumnIndex("rightOption"));
                String your_small_answer = c.getString(c.getColumnIndex("your_small_answer"));
                String your_bus_answer = c.getString(c.getColumnIndex("your_bus_answer"));
                String your_truck_answer = c.getString(c.getColumnIndex("your_truck_answer"));
                Question ques = new Question();
                ques.setAnalysis(analysis);
                ques.setDifficylty(difficylty);
                ques.setId(id);
                ques.setKem(kem);
                ques.setMedia_type(media_type);
                ques.setQuestion_type(question_type);
                ques.setDown(down);
                ques.setCx(cx);
                ques.setMedia_content(media_content);
                ques.setOption_a(option_a);
                ques.setOption_b(option_b);
                ques.setOption_c(option_c);
                ques.setOption_d(option_d);
                ques.setProbability(probability);
                ques.setQuestion(question);
                ques.setQuestion_category(question_category);
                ques.setRightOption(rightOption);
                ques.setYour_small_answer(your_small_answer);
                ques.setYour_bus_answer(your_bus_answer);
                ques.setYour_truck_answer(your_truck_answer);
                ques.save();
            }
        }
        mDataBase.close();

    }

    public SQLiteDatabase openDataBase() {
        Log.d(LOG_TAG, "openDataBase");
        // Open the database
        String myPath = DATABASE_PATH;
        return SQLiteDatabase.openDatabase(myPath, null,
                SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }

}