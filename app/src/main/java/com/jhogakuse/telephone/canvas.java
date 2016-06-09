package com.jhogakuse.telephone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.Random;

public class canvas extends AppCompatActivity {

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
    }*/

    Lienzo grafica;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        grafica = new Lienzo(this);
        setContentView(grafica);
    }

    private class Lienzo extends View {
        public Lienzo(Context mcontext) {
            super(mcontext);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            // custom drawing code here
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);

            // make the entire canvas white
            paint.setColor(Color.WHITE);
            canvas.drawPaint(paint);


            // draw blue circle with anti aliasing turned off
            //paint.setAntiAlias(false);
            //paint.setColor(Color.rgb(115, 130, 150));
            //canvas.drawCircle(20, 20, 15, paint);

            // draw green circle with anti aliasing turned on
            //paint.setAntiAlias(true);
            //paint.setColor(Color.GRAY);
            //canvas.drawCircle(60, 20, 15, paint);

            // draw red rectangle with anti aliasing turned off
            paint.setAntiAlias(false);
            paint.setARGB(118, 97, 240, 112);
            canvas.drawRect(5, 5, 300, 205, paint);

            // draw red rectangle with anti aliasing turned off
            /*paint.setColor(Color.rgb(180,83,140));
            canvas.drawRect(100, 5, 195, 150, paint);*/

            // draw red rectangle with anti aliasing turned off
            /*paint.setAntiAlias(false);
            paint.setColor(Color.RED);
            canvas.drawRect(200,5,295,30,paint);*/

            // draw the rotated text
            //canvas.rotate(-40);
            //paint.setStyle(Paint.Style.FILL);
            //canvas.drawText("Graphics Rotation", 45, 185, paint);
            // undo the rotate
            //canvas.restore();

            for (int i=5;i<=205;i+=10){
                float puntos[]={5,i,300,i};
                //paint.setStyle(Paint.Style.STROKE);
                //paint.setStrokeWidth(1);
                paint.setColor(Color.GRAY);
                canvas.drawLines(puntos, paint);
            }

            int puntoa = Integer.parseInt(Muro.RES_A);
            int puntob = Integer.parseInt(Muro.RES_B);
            int puntoc = Integer.parseInt(Muro.RES_C);
            int puntod = Integer.parseInt(Muro.RES_D);
            float puntos[]={5,205,60,205-(puntoa*5),60,205-(puntoa*5),120,205-(puntob*5),120,205-(puntob*5),180,205-(puntoc*5),180,205-(puntoc*5),240,205-(puntod*5),240,205-(puntod*5),300,205};
            float point[]={60,205-(puntoa*5),120,205-(puntob*5),180,205-(puntoc*5),240,205-(puntod*5)};
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(3);
            paint.setAntiAlias(true);
            paint.setColor(Color.rgb(181, 63, 104));
            canvas.drawLines(puntos, paint);
            paint.setStrokeWidth(5);
            paint.setColor(Color.rgb(245,7,63));
            canvas.drawPoints(point,paint);

            //Lineas de fondo
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.BLACK);
            canvas.drawText("S= "+puntoa, 60-10, 225, paint);
            canvas.drawText("CS= "+puntob, 120-10, 225, paint);
            canvas.drawText("AV= "+puntoc, 180-10, 225, paint);
            canvas.drawText("N= "+puntod, 240-10, 225, paint);
        }
    }

    /*
    InternalView myView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        myView = new InternalView(this);
        setContentView(myView);
    }

    private class InternalView extends View{
        public InternalView(Context context){
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.BLUE);
            canvas.drawPaint(paint);
            paint.setColor(Color.BLACK);
            paint.setAntiAlias(true);
            canvas.drawRect(16, 16, getWidth()-16, getHeight()-16, paint);

        }
    }*/
}
