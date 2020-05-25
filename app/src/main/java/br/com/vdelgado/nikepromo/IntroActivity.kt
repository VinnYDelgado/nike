package br.com.vdelgado.nikepromo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager

class IntroActivity: AppCompatActivity(){

    private lateinit var mViewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.promo_intro)
        mViewPager = findViewById<View>(R.id.viewpager) as ViewPager

        // Set an Adapter on the ViewPager
        mViewPager.adapter = IntroAdapter(supportFragmentManager)

        mViewPager.setPageTransformer(false,ParallaxPageTransformer())
    }
}