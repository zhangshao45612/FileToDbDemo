package com.zsj.filetodbdemo.assetsfiles;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AssetsFileDBHelper extends SQLiteOpenHelper {
	public static final String TABLE_ASSETS_FILE = "AssetsFile";
	public static String DATA_BASE_NAME = "AssetsFileList.db";
	private static final int VERSION = 1;
	private static AssetsFileDBHelper instance;

	public static AssetsFileDBHelper getInstance(Context context) {
		if (instance == null) {
			instance = new AssetsFileDBHelper(context);
		}
		return instance;
	}

	private AssetsFileDBHelper(Context context) {
		super(context, DATA_BASE_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		createTableAssetsFile(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		updateTableAssetsFile(db, oldVersion, newVersion);
	}

	/**
	 * 创建用户表
	 */
	public void createTableAssetsFile(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_ASSETS_FILE + " (_id INTEGER PRIMARY KEY AUTOINCREMENT"
				+ ",fileName TEXT NOT NULL, " + "content BLOB )");
	}

	/**
	 * 更新用户表
	 */
	public void updateTableAssetsFile(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (oldVersion != newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSETS_FILE);
			createTableAssetsFile(db);
		}
	}

}
