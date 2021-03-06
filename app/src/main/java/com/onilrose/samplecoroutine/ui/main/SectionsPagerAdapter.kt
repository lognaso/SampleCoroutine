package com.onilrose.samplecoroutine.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.onilrose.samplecoroutine.R
import com.onilrose.samplecoroutine.ui.animation.AnimatedFragment
import com.onilrose.samplecoroutine.ui.docs.DocsFragment
import com.onilrose.samplecoroutine.ui.sortlist.SortListFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2,
    R.string.tab_text_3

)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                SortListFragment.newInstance()
            }
            1 -> {
                DocsFragment.newInstance()
            }
            2 -> {
                AnimatedFragment.newInstance()
            }
            else -> {
                PlaceholderFragment.newInstance(position)
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 3
    }
}