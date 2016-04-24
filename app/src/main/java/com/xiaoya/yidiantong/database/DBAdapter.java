/*
The MIT License (MIT)

Copyright (c) 2014 Ahmad Barqawi (github.com/Barqawiz)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package com.xiaoya.yidiantong.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.iaraby.db.helper.DatabaseAdapter;

/**
 * Example on best practice how to use DatabaseAdapter in the library
 * 
 * @author Ahmad Barqawi
 * 
 */
public class DBAdapter extends DatabaseAdapter {

	public static DBAdapter instance;

	public static DBAdapter getInstance() {
		if (instance == null)
			instance = new DBAdapter();
		return instance;
	}

	public void addKeep(String data, String color) {
		ContentValues rowValues = KEEP_TABLE.createContentValues(data,color,
				KEEP_TABLE.ACTIVE);
		insert(KEEP_TABLE.TABLE_NAME, rowValues);
	}

	public void removeKeep(long id) {
		String slection = KEEP_TABLE.COL_ID + "= ?";
		String where[] = new String[] { String.valueOf(id) };
		delete(KEEP_TABLE.TABLE_NAME, slection, where);
	}
	public Cursor getKeep() {
		return fetchData(KEEP_TABLE.TABLE_NAME, KEEP_TABLE.ANALYSIS,KEEP_TABLE.DIFFICYLTY,KEEP_TABLE.ID,
				KEEP_TABLE.KEM,KEEP_TABLE.DOWN,KEEP_TABLE.CX,KEEP_TABLE.MEDIA_TYPE, KEEP_TABLE.MEDIA_CONTENT,
				KEEP_TABLE.OPTION_A,KEEP_TABLE.OPTION_B,KEEP_TABLE.OPTION_C,KEEP_TABLE.OPTION_D,KEEP_TABLE.QUESTION_TYPE,
				KEEP_TABLE.PROBABILITY,KEEP_TABLE.QUESTION,KEEP_TABLE.QUESTION_CATEGORY,KEEP_TABLE.RIGHTOPTION,
				KEEP_TABLE.YOUR_SMALL_ANSWER,KEEP_TABLE.YOUR_BUS_ANSWER,KEEP_TABLE.YOUR_TRUCK_ANSWER);
	}
	/* Table Information to unify using the fields from once place 
	 * You can use the library without implementing this but make sure you always send the right 
	 * table name and columns names*/
	public static class KEEP_TABLE {
		public final static String TABLE_NAME = "question";
		public final static String COL_ID = "id";
		public final static String COL_DATA = "analysis";
		public final static String COL_COLOR = "difficylty";
		public final static String COL_ACTIVE = "kem";

		public final static int ACTIVE = 1;
		public final static int NOT_ACTIVE = 0;

		public final static String ANALYSIS = "analysis";
		public final static String DIFFICYLTY = "difficylty";
		public final static String ID = "id";
		public final static String KEM = "kem";
		public final static String DOWN = "down";
		public final static String CX = "cx";
		public final static String MEDIA_TYPE = "media_type";
		public final static String MEDIA_CONTENT = "media_content";
		public final static String OPTION_A = "option_a";
		public final static String OPTION_B = "option_b";
		public final static String OPTION_C = "option_c";
		public final static String OPTION_D = "option_d";
		public final static String QUESTION_TYPE = "question_type";
		public final static String PROBABILITY = "probability";
		public final static String QUESTION = "question";
		public final static String QUESTION_CATEGORY = "question_category";
		public final static String RIGHTOPTION = "rightOption";
		public final static String YOUR_SMALL_ANSWER = "your_small_answer";
		public final static String YOUR_BUS_ANSWER = "your_bus_answer";
		public final static String YOUR_TRUCK_ANSWER = "your_truck_answer";

		public static ContentValues createContentValues(String data, String color, int active) {
			ContentValues values = new ContentValues();
			values.put(COL_DATA, data);
			values.put(COL_COLOR, color);
			values.put(COL_ACTIVE, active);
			return values;
		} // method: create content values
	} // class: keep table info

	public static class QUES_CATE_TABLE {
		public final static String TABLE_NAME = "question_category";
		public final static String ID = "id";
		public final static String CATEGORYNAME = "categoryName";
		public final static String KEM = "kem";
		public final static String num = "num";
	}

	public Cursor getQuesCate(){
		return fetchData(QUES_CATE_TABLE.TABLE_NAME, QUES_CATE_TABLE.ID,QUES_CATE_TABLE.CATEGORYNAME,
				QUES_CATE_TABLE.KEM,QUES_CATE_TABLE.num);
	}

} // class: Database adapter class
