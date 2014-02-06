package com.example.domain;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.face.DisplayView;
import com.example.planee.GameView;

public class Backgound implements DisplayView{
	
	/*��Ϸ����*/
	private Bitmap gameBackgound;
	/*��Ϸ��������*/
	private float gameBackgoundX_1;	
	private float gameBackgoundY_1;	//��ʼ��ʱ�ڵ�
	private float gameBackgoundX_2;
	private float gameBackgoundY_2;
	/*��Ϸ�����ٶ�*/
	private int speed = 3;
	
	public Backgound(Bitmap gameBackgound){
		this.gameBackgound = gameBackgound;
		
		gameBackgoundX_1 = 0f;
		gameBackgoundY_1 = 854 - gameBackgound.getHeight();
		gameBackgoundX_2 = 0f;
		gameBackgoundY_2 = gameBackgoundY_1 - gameBackgound.getHeight();
	}
	
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawBitmap(gameBackgound, gameBackgoundX_1, gameBackgoundY_1, null);
		canvas.drawBitmap(gameBackgound, gameBackgoundX_2, gameBackgoundY_2, null);
	}

	@Override
	public void onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void logic() {
		// TODO Auto-generated method stub
		gameBackgoundY_1 += speed;
		gameBackgoundY_2 += speed;
		if(gameBackgoundY_1 >= 854){
			gameBackgoundY_1 = gameBackgoundY_2 - gameBackgound.getHeight();
		}
		if(gameBackgoundY_2 >= 854){
			gameBackgoundY_2 = gameBackgoundY_1 - gameBackgound.getHeight();
		}
	}

}
