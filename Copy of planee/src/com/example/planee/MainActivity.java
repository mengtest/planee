package com.example.planee;

import net.youmi.android.AdManager;
import net.youmi.android.diy.banner.DiyAdSize;
import net.youmi.android.diy.banner.DiyBanner;
import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class MainActivity extends Activity {
	GameView gameView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ��ʼ��Ӧ�õķ���ID����Կ���Լ����ò���ģʽ
        AdManager.getInstance(this).init("dd0a51a288f2f519","2e2cbf77f1160ec0", false); 
        
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        
        gameView = new GameView(this);
		setContentView(gameView);
		
		//ʵ����LayoutParams(��Ҫ)
	    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
	        FrameLayout.LayoutParams.FILL_PARENT,
	        FrameLayout.LayoutParams.WRAP_CONTENT);     
	    //��������Banner������λ��
	    layoutParams.gravity = Gravity.BOTTOM | Gravity.RIGHT; // ����ʾ��Ϊ���½�  
	    //ʵ��������Banner
	    DiyBanner banner = new DiyBanner(this, DiyAdSize.SIZE_MATCH_SCREENx32);//����߶�Ϊ32dp��DiyAdSize����������Banner
	    //����Activity��addContentView����
	    this.addContentView(banner, layoutParams);
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
//		gameView.startThread();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		gameView.stopThread();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
