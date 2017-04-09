package hu.cai.ait.minesweeper.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import hu.cai.ait.minesweeper.R;

import java.util.PriorityQueue;

import hu.cai.ait.minesweeper.Cell;
import hu.cai.ait.minesweeper.MainActivity;
import hu.cai.ait.minesweeper.MineModel;

/**
 * TODO: document your custom view class.
 */
public class GameView extends View {
    private Paint paintBg;
    private Paint paintGrid;
    private Paint paintNum;

    private int numRows;
    private int numColumns;

    private Bitmap hundredsBomb;
    private Bitmap whiteFlag;



    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paintBg = new Paint();
        paintBg.setColor(Color.BLACK);
        paintBg.setStyle(Paint.Style.FILL);

        paintGrid = new Paint();
        paintGrid.setColor(Color.WHITE);
        paintGrid.setStyle(Paint.Style.STROKE);

        numRows = MineModel.getInstance().numRows;
        numColumns = MineModel.getInstance().numCols;

        paintNum = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintNum.setColor(Color.RED);

        hundredsBomb = BitmapFactory.decodeResource(getResources(), R.drawable.white_hundreds_bomb);
        whiteFlag = BitmapFactory.decodeResource(getResources(), R.drawable.wavy_black_flag);

    }

    protected void drawGrid(Canvas canvas){
        canvas.drawRect(0,0,getWidth(),getHeight(), paintGrid);

        for (int i = 1; i < numColumns; i++) {
            canvas.drawLine(i*(getWidth()/numColumns),0,i*(getWidth()/numColumns),getHeight(),paintGrid);
        }
        for (int j =1; j < numRows; j++) {
            canvas.drawLine(0, j*(getHeight()/numRows), getWidth(),j*(getHeight()/numRows), paintGrid);
        }

    }

    protected void drawNumber(Cell c, Canvas canvas){
        Rect bounds = new Rect();
        paintNum.getTextBounds(Integer.toString(c.getNumBombs()), 0,1, bounds);
        paintNum.setColor(Color.WHITE);

        float hPad = (getWidth()/numColumns-bounds.width())/2;
        float vPad = (getHeight()/numRows-bounds.height())/2;
        if(c.getNumBombs()>0) {
            canvas.drawText(Integer.toString(c.getNumBombs()),
                    hPad + c.x * (getWidth() / numColumns), (c.y + 1) * (getHeight() / numRows) - vPad,
                    paintNum);
        }else{
            paintNum.setColor(Color.GRAY);
            canvas.drawRect(c.x * (getWidth() / numColumns),(c.y) * (getHeight() / numRows),
                    (c.x+1) * (getWidth() / numColumns),(c.y+1) * (getHeight() / numRows),paintNum);
        }
    }

    protected void drawFlag(Cell c, Canvas canvas){
        Rect dest = new Rect(c.x*(getWidth()/numColumns), c.y*getHeight()/numRows,
                (c.x+1)*getWidth()/numColumns, (c.y+1)*getHeight()/numRows);
        canvas.drawBitmap(
                whiteFlag,
                null,
                dest,
                null);
    }

    protected void drawBomb(Cell c, Canvas canvas){
        Rect dest = new Rect(c.x*(getWidth()/numColumns), c.y*getHeight()/numRows,
                (c.x+1)*getWidth()/numColumns, (c.y+1)*getHeight()/numRows);
        canvas.drawBitmap(
                hundredsBomb,
                null,
                dest,
                null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        int d = w == 0 ? h : h == 0 ? w : w < h ? w : h;
        setMeasuredDimension(d, d);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        paintNum.setTextSize(getHeight()/(numRows*2));
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0,0,getWidth(),getHeight(),paintBg);

        drawGrid(canvas);


        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                Cell cell = MineModel.getInstance().getCell(i,j);
                if(cell.isFlagged()){
                    drawFlag(cell,canvas);
                }else if(cell.isShown()){
                    drawNumber(cell,canvas);
                }else if(cell.isBomb()&&MineModel.status==MineModel.LOST){
                    drawBomb(cell,canvas);
                }
            }
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            int tx = (int) event.getX() / (getWidth() / numColumns);
            int ty = (int) event.getY() / (getHeight() / numRows);

            Cell touchedCell = MineModel.getInstance().getCell(tx,ty);
            if(MineModel.status==MineModel.PLAYING) {
                if (!MineModel.flag) {
                    MineModel.getInstance().revealCell(touchedCell);
                } else {
                    MineModel.getInstance().flagCell(touchedCell);
                }
            }
            if(MineModel.status==MineModel.LOST){
                Toast.makeText(getContext(), "You Lost!", Toast.LENGTH_LONG).show();
            }if(MineModel.status==MineModel.WON){
                Toast.makeText(getContext(), "You Won!", Toast.LENGTH_LONG).show();
            }


            invalidate();
        }
        return true;
    }

}
