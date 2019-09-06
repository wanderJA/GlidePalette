package com.github.florent37.glidepalette.sample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Build
import android.util.AttributeSet
import android.view.View

/**
 * author wander
 * date 2019/6/20
 *
 */
class ReaderShadowView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr) {
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    /**
     * 阴影的大小范围
     */
    private var mShadowRadius = dpToPx(7f)
    var effect = mShadowRadius + dpToPx(3f)
        private set
    /**
     * 阴影的颜色
     */
    var shadowColor = Color.parseColor("#B9B9B9")
        set(value) {
            field = value
            invalidate()
        }
    private val mRectF = RectF()
    private val myTag = "ReaderShadowView"

    init {
        val typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ReaderShadowView)
        if (typedArray != null) {
            effect = typedArray.getDimension(R.styleable.ReaderShadowView_shadowHeight, effect)
            mShadowRadius = typedArray.getDimension(R.styleable.ReaderShadowView_shadowColorHeight, mShadowRadius)
            typedArray.recycle()
        }

        if (Build.VERSION.SDK_INT < 28) {
            setLayerType(LAYER_TYPE_SOFTWARE, null)  // 关闭硬件加速
        }
        mPaint.color = Color.TRANSPARENT
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(
                widthMeasureSpec,
                MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(effect.toInt()), MeasureSpec.EXACTLY)
        )
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        updateShadow()
    }

    private fun updateShadow() {
        mRectF.left = effect
        mRectF.top = -mShadowRadius
        mRectF.right = width - effect
        mRectF.bottom = 0f
    }

    /**
     * 真正绘制阴影的方法
     */
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        setUpShadowPaint()
        canvas.drawRect(mRectF, mPaint)
    }

    private fun setUpShadowPaint() {
        mPaint.setShadowLayer(mShadowRadius, 0f, 0f, shadowColor)
    }

}