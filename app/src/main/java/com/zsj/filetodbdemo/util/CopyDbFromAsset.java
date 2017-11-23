package com.zsj.filetodbdemo.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyDbFromAsset {
	private static String tag = "AssetsDatabase"; // for LogCat
	@SuppressLint("SdCardPath")
	private static String databasepath = "/data/data/%s/databases"; // %s is packageName
	
	public static void copyDbFromAssetToMySelf(Context context, String dbfile){
		String spath = getDatabaseFilepath(context);
		String sfile = getDatabaseFile(context,dbfile);
		File file = new File(sfile);
		boolean flag = PreferencesUtils.getBoolean(context, dbfile, false);
		if(!flag && !file.exists()){
			file = new File(spath);
			if(!file.exists() && !file.mkdirs()){
				Log.i(tag, "Create \""+spath+"\" fail!");
				return ;
			}
			if(!copyAssetsToFilesystem(context,dbfile, sfile)){
				Log.i(tag, String.format("Copy %s to %s fail!", dbfile, sfile));
				return ;
			}
			PreferencesUtils.putBoolean(context, dbfile, true);
		}
	}
	
	private static String getDatabaseFilepath(Context context){
		return String.format(databasepath, context.getApplicationInfo().packageName);
	}
	
	private static String getDatabaseFile(Context context, String dbfile){
		return getDatabaseFilepath(context)+"/"+dbfile;
	}
	
	private static boolean copyAssetsToFilesystem(Context context, String assetsSrc, String des){
		Log.i(tag, "Copy "+assetsSrc+" to "+des);
		InputStream istream = null;
		OutputStream ostream = null;
		try{
			AssetManager am = context.getAssets();
			istream = am.open(assetsSrc);
			ostream = new FileOutputStream(des);
			byte[] buffer = new byte[1024];
	    	int length;
	    	while ((length = istream.read(buffer))>0){
	    		ostream.write(buffer, 0, length);
	    	}
	    	istream.close();
	    	ostream.close();
		}
		catch(Exception e){
			e.printStackTrace();
			try{
				if(istream!=null)
			    	istream.close();
				if(ostream!=null)
			    	ostream.close();
			}
			catch(Exception ee){
				ee.printStackTrace();
			}
			return false;
		}
		return true;
	}
	
}
