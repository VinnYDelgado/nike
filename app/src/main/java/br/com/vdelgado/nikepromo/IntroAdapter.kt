package br.com.vdelgado.nikepromo

import android.graphics.Color
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter


class IntroAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> IntroFragment.newInstance(Color.parseColor("#8850ff"), position) // blue
            1 -> IntroFragment.newInstance(Color.parseColor("#ffba00"), position)
            else -> IntroFragment.newInstance(Color.parseColor("#4054ff"), position) // green
        }
    }

    override fun getCount(): Int {
        return 3
    }

}