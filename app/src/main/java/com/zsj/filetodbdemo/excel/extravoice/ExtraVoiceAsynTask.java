package com.zsj.filetodbdemo.excel.extravoice;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class ExtraVoiceAsynTask extends AsyncTask<Void, Void, Void> {
	
	private Context mContext = null;
	private ExtraVoiceDBManager dbManager = null;
	
	public ExtraVoiceAsynTask(Context context) {
		this.mContext = context;
	}

	@Override
	protected Void doInBackground(Void... params) {
		dbManager = ExtraVoiceDBManager.getInstance(mContext);
		return null;
	}

	@Override
	protected void onPostExecute(Void aVoid) {
		super.onPostExecute(aVoid);
		Toast.makeText(mContext.getApplicationContext(),"excel保存到数据库完成",Toast.LENGTH_SHORT).show();
	}
}
