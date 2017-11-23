package com.zsj.filetodbdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.zsj.filetodbdemo.assetsfiles.AssetsFileAsynTask;
import com.zsj.filetodbdemo.excel.extravoice.ExtraVoiceAsynTask;
import com.zsj.filetodbdemo.assetsdb.GetWordInfoAsynTask;
import com.zsj.filetodbdemo.excel.vocabulary.VocabularyDBHelper;
import com.zsj.filetodbdemo.util.CopyDbFromAsset;

public class MainActivity extends Activity implements View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public void onClick(View view) {
		int vId = view.getId();
		switch (vId) {
			case R.id.btn_excel_to_db_id:
				clickExcelToDb();
				break;
			case R.id.btn_assets_db_to_db_id:
				clickAssetsDbToDb();
				break;
			case R.id.btn_assets_files_to_db_id:
				clickAssetsFilesToDb();
				break;
			case R.id.btn_get_assets_db_id:
				clickGetAssetsDb();
				break;
			default:
				break;
		}
	}

	private void clickGetAssetsDb() {
		GetWordInfoAsynTask getWordInfoTask = new GetWordInfoAsynTask(this);
		getWordInfoTask.execute("2012版人教精通小学英语三年级上册（三年级起点）");
	}

	private void clickAssetsFilesToDb() {
		AssetsFileAsynTask task = new AssetsFileAsynTask(this);
		task.execute();
	}

	private void clickAssetsDbToDb() {
		//从assets里拷贝db文件到自己数据库
		CopyDbFromAsset.copyDbFromAssetToMySelf(getApplicationContext(), VocabularyDBHelper.DATA_BASE_NAME);
	}

	private void clickExcelToDb() {
		ExtraVoiceAsynTask extraTask = new ExtraVoiceAsynTask(this);
		extraTask.execute();
//		VocabularyAsynTask vocabularyTask = new VocabularyAsynTask(this);
//		vocabularyTask.execute();
	}
}
