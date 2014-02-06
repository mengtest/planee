package com.example.face;

import android.graphics.Canvas;
import android.view.MotionEvent;

public interface DisplayView {
	/*
	 * ��ͼ����
	 */
	public void draw(Canvas canvas);
	/*
	 * �¼�����
	 */
	public void onTouchEvent(MotionEvent event);
	/*
	 * �߼�����
	 */
	public void logic();
}
