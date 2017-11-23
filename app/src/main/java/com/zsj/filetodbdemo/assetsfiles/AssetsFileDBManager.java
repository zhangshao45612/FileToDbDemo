package com.zsj.filetodbdemo.assetsfiles;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class AssetsFileDBManager {
	private static final String TAG = "AssetsFileDBManager";
	private static final String EXCEPTION = "exception";
	private AssetsFileDBHelper mDBHelper = null;
	private static AssetsFileDBManager instance = null;

	public static AssetsFileDBManager getInstance(Context context) {
		if (instance == null) {
			instance = new AssetsFileDBManager(context.getApplicationContext());
		}
		return instance;
	}

	private AssetsFileDBManager(Context context) {
		mDBHelper = AssetsFileDBHelper.getInstance(context);
		readAssetsFileToDB(context);
	}

	private void readAssetsFileToDB(Context context) {
		try {
			String assetFilePath = "testdata";
			String[] h5Files = context.getAssets().list(assetFilePath);
			
			int sums = h5Files.length;
			AssetsFileInfo info = null;
			InputStream is = null;
			for (int i = 0; i < sums; ++i) {
				String fileName = h5Files[i];
				
				is = context.getAssets().open(assetFilePath + File.separator + fileName);
				byte[] contents = input2byte(is);
				info = new AssetsFileInfo(fileName, contents);
				saveInfoToDataBase(info);
				Log.d("zsj", "cur position:" + i);
			}
			Log.d("zsj", "save over");
		} catch (Exception e) {
			Log.e(TAG, EXCEPTION, e);
		}
	}

	private void saveInfoToDataBase(AssetsFileInfo info) {
		if (mDBHelper == null) {
			return;
		}
		SQLiteDatabase db = mDBHelper.getWritableDatabase();
		try {
			ContentValues values = new ContentValues();
			values.put("fileName", info.getFileName());
			values.put("content", info.getFileBuffer());
			db.insert(AssetsFileDBHelper.TABLE_ASSETS_FILE, null, values);
		} catch (SQLiteException e) {
			Log.e(TAG, EXCEPTION, e);
		} catch (Exception e) {
			Log.e(TAG, EXCEPTION, e);
		} finally {
			if (db != null) {
				db.close();
			}
		}
	}

	private byte[] input2byte(InputStream inStream){
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[100];
		byte[] in2b = null;
		int rc = 0;
		try {
			while ((rc = inStream.read(buff, 0, 100)) > 0) {
				swapStream.write(buff, 0, rc);
			}
			in2b = swapStream.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return in2b;
	}

}
