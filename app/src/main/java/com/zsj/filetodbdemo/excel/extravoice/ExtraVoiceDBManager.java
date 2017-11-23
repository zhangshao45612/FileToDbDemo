package com.zsj.filetodbdemo.excel.extravoice;

import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.zsj.filetodbdemo.util.PreferencesUtils;

public class ExtraVoiceDBManager {
	/**
	 * 是否读取过补录语音数据
	 */
	public static final String IS_READED_EXTRA_SOUND_DATA = "is_readed_extra_sound_data";

	private static final String TAG = "ExtraVoiceDBManager";
	private static final String EXCEPTION = "exception";
	private ExtraVoiceDBHelper mDBHelper = null;
	private static ExtraVoiceDBManager instance = null;

	public static ExtraVoiceDBManager getInstance(Context context) {
		if (instance == null) {
			instance = new ExtraVoiceDBManager(context.getApplicationContext());
		}
		return instance;
	}

	private ExtraVoiceDBManager(Context context) {
		mDBHelper = ExtraVoiceDBHelper.getInstance(context);
		if (!PreferencesUtils.getBoolean(context, IS_READED_EXTRA_SOUND_DATA, false)) {
			readExcelToDB(context);
		}
	}

	private void readExcelToDB(Context context) {
		try {
			InputStream is = context.getAssets().open("extra_voice_data.xls");
			Workbook book = Workbook.getWorkbook(is);
			book.getNumberOfSheets();
			// 获得第一个工作表对象
			Sheet sheet = book.getSheet(0);
			int Rows = sheet.getRows();
			ExtraVoiceInfo info = null;
			for (int i = 1; i < Rows; ++i) {
				String content = (sheet.getCell(0, i)).getContents();
				String phonetic = (sheet.getCell(1, i)).getContents();
				String property = (sheet.getCell(2, i)).getContents();
				String paraphrase = (sheet.getCell(3, i)).getContents();
				String usVoiceName = (sheet.getCell(4, i)).getContents();
				String ukVoiceName = (sheet.getCell(5, i)).getContents();

				info = new ExtraVoiceInfo(content, phonetic, property, paraphrase, usVoiceName, ukVoiceName);
				saveInfoToDataBase(info);
			}
			book.close();
			Log.d("zsj_excel","excel读取完成");
			PreferencesUtils.putBoolean(context, IS_READED_EXTRA_SOUND_DATA, true);
		} catch (Exception e) {
			Log.e(TAG, EXCEPTION, e);
			PreferencesUtils.putBoolean(context, IS_READED_EXTRA_SOUND_DATA, false);
		}
	}

	private void saveInfoToDataBase(ExtraVoiceInfo info) {
		if (mDBHelper == null) {
			return;
		}
		SQLiteDatabase db = mDBHelper.getWritableDatabase();
		try {
			ContentValues values = new ContentValues();
			values.put("content", info.getContent());
			values.put("phonetic", info.getPhonetic());
			values.put("property", info.getProperty());
			values.put("paraphrase", info.getParaphrase());
			values.put("usVoiceName", info.getUsVoiceName());
			values.put("ukVoiceName", info.getUkVoiceName());
			db.insert(ExtraVoiceDBHelper.TABLE_EXTRA_VOICE_INFO, null, values);
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

	public ExtraVoiceInfo getExtraVoiceInfo(String contentStr) {
		ExtraVoiceInfo info = null;
		if (mDBHelper == null) {
			return info;
		}

		SQLiteDatabase db = mDBHelper.getReadableDatabase();

		if (db == null) {
			return info;
		}

		Cursor cursor = db.rawQuery("select * from ExtraVoiceInfo where content = ?", new String[] { contentStr });

		try {
			if (cursor != null && cursor.moveToFirst()) {
				do {
					String content = cursor.getString(cursor.getColumnIndex("content"));
					String phonetic = cursor.getString(cursor.getColumnIndex("phonetic"));
					String property = cursor.getString(cursor.getColumnIndex("property"));
					String paraphrase = cursor.getString(cursor.getColumnIndex("paraphrase"));
					String usVoiceName = cursor.getString(cursor.getColumnIndex("usVoiceName"));
					String ukVoiceName = cursor.getString(cursor.getColumnIndex("ukVoiceName"));

					info = new ExtraVoiceInfo(content, phonetic, property, paraphrase, usVoiceName, ukVoiceName);
				} while (cursor.moveToNext());
			}

		} catch (SQLiteException e) {
			Log.e(TAG, EXCEPTION, e);
		}  catch (Exception e){
			Log.e(TAG, EXCEPTION, e);			
		} finally {
			if (cursor != null) {
				cursor.close();
				cursor = null;
			}
			if (db != null) {
				db.close();
			}
		}
		return info;
	}
}
