package com.zsj.filetodbdemo.excel.vocabulary;

import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.zsj.filetodbdemo.util.PreferencesUtils;

public class VocabularyDBManager {
	public static final String IS_READED_EXTRA_VOCABULARY_DATA = "is_readed_extra_vocabulary_data";

	private static final String TAG = "VocabularyDBManager";
	private static final String EXCEPTION = "exception";
	private VocabularyDBHelper mDBHelper = null;
	private static VocabularyDBManager instance = null;

	public static VocabularyDBManager getInstance(Context context) {
		if (instance == null) {
			instance = new VocabularyDBManager(context.getApplicationContext());
		}
		return instance;
	}

	private VocabularyDBManager(Context context) {
		mDBHelper = VocabularyDBHelper.getInstance(context);
		if (!PreferencesUtils.getBoolean(context, IS_READED_EXTRA_VOCABULARY_DATA, false)) {
			readExcelToDB(context);
		}
	}

	private void readExcelToDB(Context context) {
		try {
			InputStream is = context.getAssets().open("wordlist.xls");
			Workbook book = Workbook.getWorkbook(is);
			book.getNumberOfSheets();
			// 获得第一个工作表对象
			Sheet sheet = book.getSheet(0);
			int Rows = sheet.getRows();
			VocabularyInfo info = null;
			for (int i = 1; i < Rows; ++i) {
				String bookName = (sheet.getCell(0, i)).getContents();
				String startPage = (sheet.getCell(1, i)).getContents();
				String endPage = (sheet.getCell(2, i)).getContents();
				String curUnit = (sheet.getCell(3, i)).getContents();
				String unitName = (sheet.getCell(4, i)).getContents();
				String wordNums = (sheet.getCell(5, i)).getContents();

				info = new VocabularyInfo(bookName, Integer.parseInt(startPage), Integer.parseInt(endPage),
						Integer.parseInt(curUnit), unitName, Integer.parseInt(wordNums));
				saveInfoToDataBase(info);
				Log.d("zsj", "cur position:" + i);
			}
			Log.d("zsj", "save over");
			book.close();
			PreferencesUtils.putBoolean(context, IS_READED_EXTRA_VOCABULARY_DATA, true);
		} catch (Exception e) {
			PreferencesUtils.putBoolean(context, IS_READED_EXTRA_VOCABULARY_DATA, false);
			Log.e(TAG, EXCEPTION, e);
		}
	}

	private void saveInfoToDataBase(VocabularyInfo info) {
		if (mDBHelper == null) {
			return;
		}
		SQLiteDatabase db = mDBHelper.getWritableDatabase();
		try {
			ContentValues values = new ContentValues();
			values.put("bookname", info.getBookName());
			values.put("startpage", info.getStartPage());
			values.put("endpage", info.getEndPage());
			values.put("curunit", info.getCurUnit());
			values.put("unitname", info.getCurUnitName());
			values.put("wordnums", info.getWordNums());
			db.insert(VocabularyDBHelper.TABLE_VOCABULARY_INFO, null, values);
		} catch (SQLiteException e) {
			Log.e(TAG, EXCEPTION, e);
		} catch (Exception e){
			Log.e(TAG, EXCEPTION, e);			
		} finally {
			if (db != null) {
				db.close();
			}
		}
	}

}
