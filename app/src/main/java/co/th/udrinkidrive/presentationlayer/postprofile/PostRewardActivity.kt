package co.th.udrinkidrive.presentationlayer.postprofile

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import co.th.udrinkidrive.R
import co.th.udrinkidrive.presentationlayer.postprofile.ui.postprofile.PostChangeRewardFragment
import com.flyco.tablayout.listener.OnTabSelectListener
import kotlinx.android.synthetic.main.activity_post_reward.*

class PostRewardActivity : AppCompatActivity() , View.OnClickListener {

    var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    lateinit var list: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_reward)

        list = arrayOf(getString(R.string.reward_change_reward),getString(R.string.reward_history_reward))

        ViewAndEvent()

        setViewpager()

        ActionView()

     }

    fun ViewAndEvent(){
        image_back.setOnClickListener(this)
    }

    fun ActionView(){

        donut_progress.progress = 40.toFloat()

        mViewPager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }
            override fun onPageSelected(position: Int) {
                tl_2.currentTab = position
            }

        })

        tl_2.setOnTabSelectListener(object : OnTabSelectListener{
            override fun onTabSelect(position: Int) {
                mViewPager.currentItem = position
             }

            override fun onTabReselect(position: Int) {
             }

        })

        image_back.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.image_back -> {
                onBackPressed()
            }
        }
    }

    private fun setViewpager(){
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        mViewPager.adapter = mSectionsPagerAdapter
        mViewPager.offscreenPageLimit = 2
         tl_2.setTabData(list)
    }

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            when (position) {
                0 -> {
                    return PostChangeRewardFragment()
                }
                1 -> {
                    return PostChangeRewardFragment()
                }

                else -> return null
            }
        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence {
            val tabTitles = list
            return tabTitles[position]

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(R.anim.slide_out_left,R.anim.slide_out_right)
    }
}