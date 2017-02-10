package com.example.laijie;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

import android.os.Environment;
import android.util.Log;

public class SaveDB {
	private static String urlNull = "原文件路径不存在";
	 private static String isFile = "原文件不是文件";
	 private static String canRead = "原文件不能读";
	 //private static String notWrite = "备份文件不能写入";
	 private static String message = "备份成功";
	 private static String cFromFile = "创建原文件出错:";
	 private static String ctoFile = "创建备份文件出错:";
	 private static String fromFileUrl="/data/data/com.example.laijie/databases/account.db";
	 private static String toFileUrl="";
	 /**
	  *
	  */
	 public static String Save( ) {
	  File fromFile = null;
	  File toFile = null;
	  if (Environment.getExternalStorageState().equals((Environment.MEDIA_MOUNTED))){
          File sdCardDir=Environment.getExternalStorageDirectory();
          toFileUrl=sdCardDir.getPath()+"//Zachay-";
      }else{
    	  return "没有sd卡";
      }
	  try {
		  fromFile = new File(fromFileUrl);
	  } catch (Exception e) {
		  return cFromFile + e.getMessage();
	  }
	  try {
	   toFile = new File(getToFileUrl());
	  } catch (Exception e) {
	   return ctoFile + e.getMessage();
	  }

	  if (!fromFile.exists()) {
	   return urlNull;
	  }
	  if (!fromFile.isFile()) {
	    return isFile;
	  }
	  if (!fromFile.canRead()) {
	    return canRead;
	  }

	  if (!toFile.getParentFile().exists()) {
	   toFile.getParentFile().mkdirs();
	  }
	  if (toFile.exists()) {
	   toFile.delete();
	  }
	  if (!toFile.canWrite()) {
	   // return notWrite;
	  }
	  try {
	   java.io.FileInputStream fosfrom = new java.io.FileInputStream(
	     fromFile);
	   java.io.FileOutputStream fosto = new FileOutputStream(toFile);
	   byte bt[] = new byte[1024];
	   int c;

	   while ((c = fosfrom.read(bt)) > 0) {
	    fosto.write(bt, 0, c);
	   }

	   fosfrom.close();
	   fosto.close();

	  } catch (Exception e) {
		  Log.i("Zachay",e.getMessage());  
		  message ="备份失败!"+e.toString();
	  }
	  return message;

	 }
	 
	 
	 
	 
	 /**
	  */
	 private static String getToFileUrl(){  
	  StringBuffer sb=new StringBuffer(); 
	        sb.append(toFileUrl); 
	        sb.append(getTime());    
	        sb.append(".db");    
	        return sb.toString();  
	 }
	 
	  /**
	  * 
	  */
	 private static String getTime() {
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  String nowTime = sdf.format(new java.util.Date());   
	  return nowTime.replaceAll(" ","");
	 }

}
