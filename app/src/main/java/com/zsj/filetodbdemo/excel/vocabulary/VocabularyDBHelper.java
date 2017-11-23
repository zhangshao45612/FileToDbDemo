package com.zsj.filetodbdemo.excel.vocabulary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 配置数据库操作帮助类
 * 
 * */
public class VocabularyDBHelper extends SQLiteOpenHelper {
	
	public static final String TABLE_VOCABULARY_INFO = "VocabularyInfo";
	public static String DATA_BASE_NAME = "VocabularyInfoCfg.db";
	private static final int VERSION = 1;
	private static VocabularyDBHelper instance;
		
	public static VocabularyDBHelper getInstance( Context context ) {
		if (instance == null) {
			instance = new VocabularyDBHelper( context );
		}
		return instance;
	}
	
	public VocabularyDBHelper(Context context){
		super(context, DATA_BASE_NAME, null, VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		createTableUser(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		updateTableUser(db, oldVersion, newVersion);
	}
		
	/**
	 * 创建用户表
	 * 
	 * */
	public void createTableUser(SQLiteDatabase db){
		db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_VOCABULARY_INFO + " (_id INTEGER PRIMARY KEY AUTOINCREMENT"
				+ ",bookname TEXT NOT NULL, " +
				"startpage INTEGER ," +
				"endpage INTEGER, " +
				"curunit INTEGER ," +
				"unitname TEXT NOT NULL, " +
				"wordnums INTEGER)" );
	}
	
	/**
	 * 更新用户表
	 * 
	 * */
	public void updateTableUser(SQLiteDatabase db, int oldVersion, int newVersion){
		if ( oldVersion != newVersion ){
            db.execSQL( "DROP TABLE IF EXISTS " + TABLE_VOCABULARY_INFO );
            createTableUser(db);
        }
	}
}
