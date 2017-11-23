package com.zsj.filetodbdemo.assetsdb;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.zsj.filetodbdemo.excel.vocabulary.VocabularyInfo;
import com.zsj.filetodbdemo.util.AssetsDatabaseManager;
import com.zsj.filetodbdemo.util.ListUtils;

public class GetWordInfoAsynTask extends AsyncTask<String, Void, List<VocabularyInfo>> {
	private static final String TAG = "GetWordInfoAsynTask";
	private static final String EXCEPTION = "exception";
	private Context mContext = null;
	private AssetsDatabaseManager dbManager = null;

	public GetWordInfoAsynTask(Context context) {
		this.mContext = context;
		// 初始化，只需要调用一次
		AssetsDatabaseManager.initManager(context);
	}

	@Override
	protected void onPostExecute(List<VocabularyInfo> vocabularyInfos) {
		super.onPostExecute(vocabularyInfos);
		if (ListUtils.isEmpty(vocabularyInfos)){
			Log.d("zsj_getwordinfo","vocabularyInfos is null");
		}else {
			Log.d("zsj_getwordinfo","vocabularyInfos :" + vocabularyInfos);
		}
	}

	@Override
	protected List<VocabularyInfo> doInBackground(String... params) {
		// 获取管理对象，因为数据库需要通过管理对象才能够获取
		dbManager = AssetsDatabaseManager.getManager();
		// 通过管理对象获取数据库
		SQLiteDatabase db = dbManager.getDatabase("VocabularyInfoCfg.db");
		List<VocabularyInfo> wordList = getWordInfo(db,params[0]);
		return wordList;
	}

	private List<VocabularyInfo> getWordInfo(SQLiteDatabase db, String bookNameStr) {
		List<VocabularyInfo> wordList = new ArrayList<VocabularyInfo>();
		if (db == null || TextUtils.isEmpty(bookNameStr)) {
			return wordList;
		}

		VocabularyInfo info = null;
		Cursor cursor = null;
		try {
			cursor = db.rawQuery("select * from VocabularyInfo where bookname LIKE ?", new String[] { "%" + bookNameStr + "%" });
			if (cursor != null && cursor.moveToFirst()) {
				do {
					String bookName = cursor.getString(cursor.getColumnIndex("bookname"));
					int startPage = cursor.getInt(cursor.getColumnIndex("startpage"));
					int endPage = cursor.getInt(cursor.getColumnIndex("endpage"));
					int curUnit = cursor.getInt(cursor.getColumnIndex("curunit"));
					String unitName = cursor.getString(cursor.getColumnIndex("unitname"));
					int wordNums = cursor.getInt(cursor.getColumnIndex("wordnums"));

					info = new VocabularyInfo(bookName, startPage, endPage, curUnit, unitName, wordNums);
					if (info != null) {
						wordList.add(info);
					}
				} while (cursor.moveToNext());
			}

		} catch (SQLiteException e) {
			Log.e(TAG, EXCEPTION, e);
		} catch (Exception e) {
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

		return wordList;
	}

}
