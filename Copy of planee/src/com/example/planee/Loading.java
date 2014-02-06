package com.example.planee;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.domain.Assets;
import com.example.domain.Audio;
import com.example.face.DisplayView;

public class Loading implements DisplayView{
	//����ͼ
	private Bitmap backgound;
	//��ʼ��ťͼ�����ꡢ�Ƿ���
	private Bitmap start_button;
	private float start_button_x, start_button_y;
	private boolean start_button_touch = false;
	//�ɻ����ͼ������
	private Bitmap face;
	private float face_x, face_y;
	//��ϷLOGOͼ������
	private Bitmap logo;
	private float logo_x, logo_y;
	//��������
	private float scanX = 1f;
	private float scanY = 1f;
	//GameView����
	private GameView gameView;
	//�õ�GameView�е���Դʵ��
	private Assets assets;
	//�õ�GameView�е����ֲ�����ʵ��
	private Audio audio;
	
	/*
	 * ���췽��
	 * */
	public Loading(GameView gameView){
		this.gameView = gameView;
		
		assets = gameView.getAssets();
		audio = gameView.getAudio();
		
		backgound = assets.backgound;
		start_button = assets.start_button;
		face = assets.face;
		logo = assets.logo;
		
		face_x = 0;
		face_y = scanY * (854- face.getHeight());
		//GameView.GAME_WIDTH GameView.GAME_HEIGHT
		start_button_x = Math.abs(480 - start_button.getWidth()) / 2;
		start_button_y = scanY * (854 - 145);
		//GameView.GAME_WIDTH GameView.GAME_HEIGHT
		logo_x = Math.abs(480 - logo.getWidth()) / 2;
		logo_y = scanX * 75;
		//��ʼ������Ϸ�����ɱ�����
		audio.setMusic("bgm_zhuxuanlv.ogg");
		audio.setLoop(true);
		audio.start();
	}
	
	/*
	 * ���Ʒ���
	 * */
	public void draw(Canvas canvas){
		canvas.drawBitmap(backgound, 0, 0, null);
		canvas.drawBitmap(face, face_x, face_y, null);
		canvas.drawBitmap(logo, logo_x, logo_y, null);
		//�жϰ�ť�Ƿ���,�������,��С��ť
		if(start_button_touch){
			canvas.save();
			canvas.scale(0.85f,0.85f, start_button_x + start_button.getWidth()/2, start_button_y + start_button.getHeight()/2);
			canvas.drawBitmap(start_button, start_button_x, start_button_y, null);
			canvas.restore();
		}else
			canvas.drawBitmap(start_button, start_button_x, start_button_y, null);
	}
	
	/*
	 * �¼�����
	 * */
	public void onTouchEvent(MotionEvent event){
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			//�����Button�����ڰ���,��button���±�ʶ����Ϊtrue
			if( (start_button_x < event.getX()/GameView.SCALE_X && event.getX()/GameView.SCALE_X < (start_button_x+start_button.getWidth()))
					&& (start_button_y < event.getY()/GameView.SCALE_Y && event.getY()/GameView.SCALE_Y < (start_button_y+start_button.getHeight())) ){
				start_button_touch = true;
				audio.playSound(Audio.effcet_ui_ananniu);
			}
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:
			//ÿ��̧�𶼻Ὣstart_button_touch����Ϊfalse
			start_button_touch = false;
			//�����button������̧��,��Ϊ�����button,������Ϸ�˵�
			if( (start_button_x < event.getX()/GameView.SCALE_X && event.getX()/GameView.SCALE_X < (start_button_x+start_button.getWidth()))
					&& (start_button_y < event.getY()/GameView.SCALE_Y && event.getY()/GameView.SCALE_Y < (start_button_y+start_button.getHeight())) ){
				//����д��ʼ
				gameView.setGameState(GameView.GAME_GAMEING);
				//����������
				audio.setMusic("bgm_zhandou2.ogg");
				audio.setLoop(true);
				audio.start();
			}
			break;
		}
		
	}

	@Override
	public void logic() {
		// TODO Auto-generated method stub
		
	}
	
}
