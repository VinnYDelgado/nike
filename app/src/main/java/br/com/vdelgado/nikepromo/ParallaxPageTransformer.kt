package br.com.vdelgado.nikepromo

import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs
import kotlin.math.max

class ParallaxPageTransformer : ViewPager.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val pageWidth = page.width
        val pageHeight = page.height
        if (position >= -1 && position <= 1) {
            page.findViewById<AppCompatImageView>(R.id.header_imageview).translationX = -position * page.width / 2
            page.findViewById<ConstraintLayout>(R.id.header_details).translationX = -position * page.width / 2
            page.findViewById<AppCompatButton>(R.id.appCompatButton).translationX = -position * page.width / 2

            val scaleFactor = max(MIN_SCALE, 1 - abs(position))
            val vertMargin = pageHeight * (1 - scaleFactor) / 2
            val horzMargin = pageWidth * (1 - scaleFactor) / 2
            if (position < 0) {
                page.translationX = horzMargin - vertMargin / 2
            } else {
                page.translationX = -horzMargin + vertMargin / 2
            }

            // Scale the page down (between MIN_SCALE and 1)
            page.scaleX = scaleFactor
            page.scaleY = scaleFactor

            // Fade the page relative to its size.
            page.alpha = MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA)
        } else {
            page.alpha = 1.0f
        }
    }

    companion object {
        private const val MIN_SCALE = 0.85f
        private const val MIN_ALPHA = 0.5f
    }
}
