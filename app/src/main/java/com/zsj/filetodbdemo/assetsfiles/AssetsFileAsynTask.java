package com.zsj.filetodbdemo.assetsfiles;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class AssetsFileAsynTask extends AsyncTask<Void, Void, Void> {

	private Context mContext = null;
	private AssetsFileDBManager dbManager = null;

	public AssetsFileAsynTask(Context context) {
		this.mContext = context;
	}

	@Override
	protected Void doInBackground(Void... params) {
		dbManager = AssetsFileDBManager.getInstance(mContext);
		return null;
	}

	@Override
	protected void onPostExecute(Void aVoid) {
		super.onPostExecute(aVoid);
		Toast.makeText(mContext.getApplicationContext(),"assets file保存到数据库完成",Toast.LENGTH_SHORT).show();
	}

}
