package com.example.domain;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.face.DisplayView;
import com.example.planee.GameView;

public class Pang implements DisplayView{
	//����
	private float x;
	private float y;
	//��Դ
	private Bitmap[] bz = new Bitmap[5];
	//��Դ��С����
	private float scale;
	//��ǰ֡
	private int flash = 0;
	//��Ч������
	private Audio audio;
	//��ը�Ƿ����
	private boolean pangOk = false;
	
	public Pang(GameView gameView , float x, float y, float scale){
		//�ɻ���ը��ʼ��
		this.x = x;
		this.y = y;
		this.scale = scale;
		bz[0] = gameView.getAssets().bz_1;
		bz[1] = gameView.getAssets().bz_2;
		bz[2] = gameView.getAssets().bz_3;
		bz[3] = gameView.getAssets().bz_4;
		bz[4] = gameView.getAssets().bz_5;
		audio = gameView.getAudio();
	}
	
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		if(flash < 5 && y > -81 && y < GameView.GAME_HEIGHT){
			canvas.save();
			canvas.scale(scale, scale, x, y);
			canvas.drawBitmap(bz[flash], x, y, null);
			canvas.restore();
		}
	}

	@Override
	public void onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logic() {
		// TODO Auto-generated method stub
		if(flash < 5){
			if(flash == 0 && y > -81 && y < GameView.GAME_HEIGHT){
				audio.playSound(Audio.effcet_sfx_zhongbaozha);
			}
			flash++;
		}
		//���֡Ϊ5����ɱ�ը
		if(flash == 5)
			pangOk = true;
	}
	
	public boolean isPangOk(){
		return pangOk;
	}
	
	public void setLocation(float x, float y){
		this.x = x;
		this.y = y;
	}
}
