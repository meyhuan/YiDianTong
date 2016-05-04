package com.xiaoya.yidiantong;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iaraby.db.helper.Config;
import com.smartydroid.android.starter.kit.app.StarterActivity;
import com.smartydroid.android.starter.kit.utilities.SPUtils;
import com.smartydroid.android.starter.kit.utilities.Utils;
import com.xiaoya.yidiantong.database.DBAdapter;
import com.xiaoya.yidiantong.model.Question;
import com.xiaoya.yidiantong.model.QuestionCategory;
import com.xiaoya.yidiantong.ui.LoginActivity;
import com.xiaoya.yidiantong.ui.NewDriverActivity;
import com.xiaoya.yidiantong.ui.PreKnowledgeActivity;
import com.xiaoya.yidiantong.ui.SubjectIndexActivity;

import java.io.IOException;


public class MainActivity extends StarterActivity implements View.OnClickListener,
        NavigationView.OnNavigationItemSelectedListener{


    private static final String TAG = "MainActivity";

    private LinearLayout mainLayoutPreKnowledge;
    private LinearLayout mainLayoutSubject1;
    private LinearLayout mainLayoutSubject2;
    private LinearLayout mainLayoutSubject3;
    private LinearLayout mainLayoutSubject4;
    private LinearLayout mainLayoutGetLicence;
    private LinearLayout mainLayoutDriver;
    private DrawerLayout drawerLayout;
    private NavigationView navView;

    private ImageView mAvatarImageView;
    private TextView mNicknameTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isAddToolBar = true;
        setContentView(R.layout.activity_main);

        if(!(boolean) SPUtils.get(mContext, "database_loading_complete", false)){
            new LoadTask().execute();
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.nav_view);
        setSupportActionBar(toolbar);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navView.setNavigationItemSelectedListener(this);
        //需要加上这一句才能显示图片的正确颜色
        navView.setItemIconTintList(null);
        View headerView = navView.getHeaderView(0);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(mContext, LoginActivity.class));
            }
        });

    }
    @Override
    protected void setupViews() {
        mainLayoutPreKnowledge = (LinearLayout) findViewById(R.id.main_layout_pre_knowledge);
        mainLayoutSubject1 = (LinearLayout) findViewById(R.id.main_layout_subject1);
        mainLayoutSubject2 = (LinearLayout) findViewById(R.id.main_layout_subject2);
        mainLayoutSubject3 = (LinearLayout) findViewById(R.id.main_layout_subject3);
        mainLayoutSubject4 = (LinearLayout) findViewById(R.id.main_layout_subject4);
        mainLayoutGetLicence = (LinearLayout) findViewById(R.id.main_layout_get_licence);
        mainLayoutDriver = (LinearLayout) findViewById(R.id.main_layout_driver);

        mainLayoutPreKnowledge.setOnClickListener(this);
        mainLayoutSubject1.setOnClickListener(this);
        mainLayoutSubject2.setOnClickListener(this);
        mainLayoutSubject3.setOnClickListener(this);
        mainLayoutSubject4.setOnClickListener(this);
        mainLayoutGetLicence.setOnClickListener(this);
        mainLayoutDriver.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_layout_pre_knowledge:
                Intent intent = new Intent(mContext, PreKnowledgeActivity.class);
                intent.putExtra("sub_index", new PreKnowledgeActivity.SubIndex(
                        getResources().getStringArray(R.array.knowledge_title),
                        getResources().getStringArray(R.array.knowledge_content)));
                startActivity(intent);
                break;

            case R.id.main_layout_subject1:
                App.setCurrentSubject(1);
                startActivity(SubjectIndexActivity.class);
                break;

            case R.id.main_layout_subject2:

                break;

            case R.id.main_layout_subject3:

                break;

            case R.id.main_layout_subject4:
                App.setCurrentSubject(4);
                startActivity(SubjectIndexActivity.class);
                break;

            case R.id.main_layout_get_licence:
                Intent intentLicence = new Intent(mContext, PreKnowledgeActivity.class);
                intentLicence.putExtra("sub_index", new PreKnowledgeActivity.SubIndex(
                        getResources().getStringArray(R.array.get_licence_title),
                        getResources().getStringArray(R.array.get_licence_content)));
                startActivity(intentLicence);
                break;

            case R.id.main_layout_driver:
                startActivity(NewDriverActivity.class);
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_about_bdj) {
        } else if (id == R.id.nav_about_us) {
        } else if (id == R.id.nav_fb) {
        } else if (id == R.id.nav_comment) {
        } else if (id == R.id.nav_setting) {
        }else if (id == R.id.nav_invite) {
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    class LoadTask extends AsyncTask<Void, Integer, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Utils.showToast(mContext, "开始");
            showUnBackProgressLoading("loading");
        }

        @Override
        protected Void doInBackground(Void... params) {
//            Cursor c = DBAdapter.getInstance().getKeep();
//            if(c.moveToFirst()){
//                while (c.moveToNext()){
//                   addQuestion(c);
//                }
//            }
//            c.close();
//
//            Cursor quesCateCursor = DBAdapter.getInstance().getQuesCate();
//            if(quesCateCursor.moveToFirst()){
//                while (quesCateCursor.moveToNext()){
//                    addQuesCate(quesCateCursor);
//                }
//            }
//            quesCateCursor.close();
            //Initial
            //make sure the database is opened
            if (!DBAdapter.getInstance().isOpen()) {
                try {
                    Config config = new Config("jiaxiaolit.db", 1, mContext);
                    DBAdapter.getInstance().open(config);
                } catch (IOException e) {
                    Log.e(getString(R.string.app_name), "Error opening the database", e);
                } //try to open the database
            } //check if the data base is opened
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Utils.showToast(mContext, "结束");
            dismissUnBackProgressLoading();
            SPUtils.put(mContext,"database_loading_complete",true);
        }
    }

    private void addQuestion(Cursor c){

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

    private void addQuesCate(Cursor cursor){
        int id = cursor.getInt(cursor.getColumnIndex("id"));
        String categoryName = cursor.getString(cursor.getColumnIndex("categoryName"));
        String kem = cursor.getString(cursor.getColumnIndex("kem"));
        int num = cursor.getInt(cursor.getColumnIndex("num"));

        QuestionCategory questionCategory = new QuestionCategory();
        questionCategory.setId(id);
        questionCategory.setCategoryName(categoryName);
        questionCategory.setKem(kem);
        questionCategory.setNum(num);
        questionCategory.save();

    }

    @Override
    public void onCreateCustomToolBar(Toolbar toolbar) {
        super.onCreateCustomToolBar(toolbar);
        toolbar.setBackgroundResource(R.color.colorPrimary);
    }
}
