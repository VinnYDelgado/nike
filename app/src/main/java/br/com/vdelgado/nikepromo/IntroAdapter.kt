package br.com.vdelgado.nikepromo

import android.graphics.Color
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class IntroAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> IntroFragment.newInstance(Color.parseColor("#8850ff"), position) // blue
            1 -> IntroFragment.newInstance(Color.parseColor("#ffba00"), position)
            else -> IntroFragment.newInstance(Color.parseColor("#4054ff"), position) // green
        }
    }

    override fun getCount(): Int {
        return shoesCount
    }

    companion object {
        const val shoesCount = 3
    }
}
