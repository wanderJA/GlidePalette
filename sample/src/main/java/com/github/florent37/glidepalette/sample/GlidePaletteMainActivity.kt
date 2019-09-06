package com.github.florent37.glidepalette.sample

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import kotlinx.android.synthetic.main.glide_palette_activity_main.*

class GlidePaletteMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.glide_palette_activity_main)


        loadPalette()

        initView()
    }

    private fun loadPalette() {
        val glidePalette = GlidePalette.with(URL)
            .use(BitmapPalette.Profile.VIBRANT)
            .intoBackground(textVibrant, BitmapPalette.Swatch.RGB)
            .intoTextColor(textVibrant, BitmapPalette.Swatch.BODY_TEXT_COLOR)
            .crossfade(true)
            .use(BitmapPalette.Profile.VIBRANT_DARK)
            .intoBackground(textVibrantDark, BitmapPalette.Swatch.RGB)
            .intoTextColor(textVibrantDark, BitmapPalette.Swatch.BODY_TEXT_COLOR)
            .crossfade(false)
            .use(BitmapPalette.Profile.VIBRANT_LIGHT)
            .intoBackground(textVibrantLight, BitmapPalette.Swatch.RGB)
            .intoTextColor(textVibrantLight, BitmapPalette.Swatch.BODY_TEXT_COLOR)
            .crossfade(true, 1000)
            .use(BitmapPalette.Profile.MUTED)
            .intoBackground(textMuted, BitmapPalette.Swatch.RGB)
            .intoTextColor(textMuted, BitmapPalette.Swatch.BODY_TEXT_COLOR)
            .use(BitmapPalette.Profile.MUTED_DARK)
            .intoBackground(textMutedDark, BitmapPalette.Swatch.RGB)
            .intoTextColor(textMutedDark, BitmapPalette.Swatch.BODY_TEXT_COLOR)
            .crossfade(true, 2000)
            .use(BitmapPalette.Profile.MUTED_LIGHT)
            .intoBackground(textMutedLight, BitmapPalette.Swatch.RGB)
            .intoTextColor(textMutedLight, BitmapPalette.Swatch.BODY_TEXT_COLOR)
        Glide.with(this)
            .load(URL)
            .listener(glidePalette

                // optional
                .intoCallBack {
                    //specific task
                }

                // optional
                .setGlideListener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                        return false
                    }

                    override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                        return false
                    }
                })

                // optional: do stuff with the builder
                .setPaletteBuilderInterceptor { builder -> builder.resizeBitmapArea(300 * 100) })
            .into(image)
    }

    private fun initView() {
        textVibrant.setOnClickListener {
            paletteBg.background = it.background
        }
        textMuted.setOnClickListener {
            paletteBg.background = it.background
        }
        textMutedDark.setOnClickListener {
            paletteBg.background = it.background
        }
        textMutedLight.setOnClickListener {
            paletteBg.background = it.background
        }
        textVibrantDark.setOnClickListener {
            paletteBg.background = it.background
        }
        textVibrantLight.setOnClickListener {
            paletteBg.background = it.background
        }

        changeImage.setOnClickListener {
            val s = imageUrlInput.text.toString()
            if (s.isNullOrEmpty()) {
                return@setOnClickListener
            }
            URL = s
            loadPalette()

        }
        randomChange.setOnClickListener {
            URL = imageUrls.random()
            loadPalette()
        }
    }

    companion object {

        var URL = "http://pic1.iqiyipic.com/image/20190807/cb/42/bk_100062746_r_601_m11.jpg"

        val imageUrls = listOf("http://pic8.iqiyipic.com/image/20190701/a4/69/bk_100201722_r_601_m1.jpg",
                URL,
                "http://pic0.iqiyipic.com/image/20190717/8a/72/bk_100177386_r_601_m2.jpg",
                "http://pic6.iqiyipic.com/image/20190806/66/ac/bk_100158983_r_601_m8.jpg",
                "http://pic0.iqiyipic.com/image/20190820/47/10/bk_100167028_r_601_m4.jpg",
                "http://pic9.iqiyipic.com/image/20190308/f9/83/bk_100068722_r_601_m8.jpg")
    }
}