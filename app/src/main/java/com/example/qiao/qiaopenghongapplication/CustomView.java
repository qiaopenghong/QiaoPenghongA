package com.example.qiao.qiaopenghongapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by qiao on 2016/11/1.
 */
public class CustomView extends View{
    private static final int WIDTH = 90;//常量半径
    private Rect rect = new Rect(90,90,WIDTH,WIDTH);// 绘制矩形的区域
    private int deltaX, deltaY;//点击位置和图形边界的偏移量
    private static Paint paint = new Paint();// 画笔
    public CustomView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        paint.setColor(Color.BLUE);// 填充颜色
        paint.setAntiAlias(true);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context);
        // TODO Auto-generated constructor stub
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        // 画矩形'';
        //canvas.drawCircle(rect.centerX(),rect.centerY(),WIDTH/2, paint);
        //canvas.drawText("画圆：", 10, 20,paint);// 画文本
        //canvas.drawCircle(60, 20, 90,paint);// 小圆
        paint.setAntiAlias(true);// 设置画笔的锯齿效果。true是去除，大家一看效果就明白了
        canvas.drawCircle(120, 20, 20,paint);// 大圆
        //
        //canvas.drawText("画矩形:", 10, 80,paint);
        paint.setColor(Color.GRAY);// 设置灰色
        paint.setStyle(Paint.Style.FILL);//设置填满
       // canvas.drawRect(60, 60, 80, 80,paint);// 正方形
        //canvas.drawRect(60, 90, 160, 100,paint);// 长方形
        //
        //canvas.drawText("画三角形：", 10, 200,paint);
        // // 绘制这个三角形,你可以绘制任意多边形
        //
//         Path path = new Path();
//         path.moveTo(80, 200);// 此点为多边形的起点
//         path.lineTo(120, 250);
//         path.lineTo(80, 250);
//         path.close(); // 使这些点构成封闭的多边形
//         canvas.drawPath(path, paint);
        //
        // //画圆角矩形
//        paint.setStyle(Paint.Style.FILL);//充满
//        paint.setColor(Color.LTGRAY);
//        paint.setAntiAlias(true);// 设置画笔的锯齿效
//        canvas.drawText("画圆角矩形:", 10, 260,paint);
//        RectF oval3 = new RectF(80, 260, 200, 300);// 设置个新的长方形
//        canvas.drawRoundRect(oval3, 20, 15,paint);//第二个参数是x半径，第三个参数是y半径

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!rect.contains(x, y)) {
                    return false;// 没有在矩形上点击，不处理触摸消息
                }
                deltaX = x - rect.left;
                deltaY = y - rect.top;
                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                Rect old = new Rect(rect);
                // 更新矩形的位置
                rect.left = x - deltaX;
                rect.top = y - deltaY;
                rect.right = rect.left + WIDTH;
                rect.bottom = rect.top + WIDTH;
                old.union(rect);// 要刷新的区域，求新矩形区域与旧矩形区域的并集
                invalidate(old);// 出于效率考虑，设定脏区域，只进行局部刷新，不是刷新整个view
                break;
        }
        return true;// 处理了触摸消息，消息不再传递
    }
}
