package com.example.domain;

import java.io.IOException;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class Assets {
	//asset������
	AssetManager assetManager;
	//ͼƬ��Դ
	public static Bitmap backgound;
	public static Bitmap start_button;
	public static Bitmap face;
	public static Bitmap logo;
	/*����*/
	public static Bitmap playerBitmap;
	public static Bitmap playerHp;
	public static Bitmap playerHpLine;
	public static Bitmap playerHpLineBack;
	/*�л�*/
	public static Bitmap enemyEasy;
	public static Bitmap enemyHard;
	public static Bitmap enemyPlain;
	/*��Ϸ����*/
	public static Bitmap gameBackgound;
	/*��ը*/
	public static Bitmap bz_1;
	public static Bitmap bz_2;
	public static Bitmap bz_3;
	public static Bitmap bz_4;
	public static Bitmap bz_5;
	public static Bitmap bz_6;
	/*�ӵ�*/
	public static Bitmap bullet;
	
	public Assets(Context context){
		//�õ�asset������
		assetManager = context.getAssets();
		//player
		playerHp = getBitmap("player/playerHp.png");
		playerHpLine = getBitmap("player/playerHpLine.png");
		playerBitmap = getBitmap("player/playerBitmap.png");
		playerHpLineBack = getBitmap("player/playerHpLineBack.png");
		//loading
		backgound = getBitmap("loading/backgound.png");
		start_button = getBitmap("loading/start_button.png");
		face = getBitmap("loading/face.png");
		logo = getBitmap("loading/logo.png");
		//enemy
		enemyEasy = getBitmap("enemy/enemyEasy.png");
		enemyHard = getBitmap("enemy/enemyHard.png");
		enemyPlain = getBitmap("enemy/enemyPlain.png");
		//playing
		gameBackgound = getBitmap("playing/backgound.jpg");
		//pang
		bz_1 = getBitmap("pang/bz/1.png");
		bz_2 = getBitmap("pang/bz/2.png");
		bz_3 = getBitmap("pang/bz/3.png");
		bz_4 = getBitmap("pang/bz/4.png");
		bz_5 = getBitmap("pang/bz/5.png");
		bz_6 = getBitmap("pang/bz/6.png");
		//bullet
		bullet = getBitmap("bullet/img_bullet1.png");
	}
	
	public Bitmap getBitmap(String src){
		try {
			return BitmapFactory.decodeStream(assetManager.open(src));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("��Ϸ��Դ�����쳣:" + e.toString(), "--->" + src);
			return null;
		}
	}
}
