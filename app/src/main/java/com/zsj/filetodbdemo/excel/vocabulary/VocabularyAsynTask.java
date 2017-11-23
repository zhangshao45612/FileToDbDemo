package com.zsj.filetodbdemo.excel.vocabulary;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class VocabularyAsynTask extends AsyncTask<Void, Void, Void> {

	private Context mContext = null;
	private VocabularyDBManager dbManager = null;

	public VocabularyAsynTask(Context context) {
		this.mContext = context;
	}

	@Override
	protected Void doInBackground(Void... params) {
		dbManager = VocabularyDBManager.getInstance(mContext);
		return null;
	}

	@Override
	protected void onPostExecute(Void aVoid) {
		super.onPostExecute(aVoid);
		Toast.makeText(mContext.getApplicationContext(), "excel保存到数据库完成", Toast.LENGTH_SHORT).show();
	}

}
