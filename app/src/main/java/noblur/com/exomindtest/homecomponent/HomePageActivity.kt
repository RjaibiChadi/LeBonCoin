package noblur.com.exomindtest.homecomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import noblur.com.exomindtest.R
import noblur.com.exomindtest.utils.obtainViewModel
import noblur.com.exomindtest.utils.replaceFragmentInActivity
import noblur.com.exomindtest.utils.setupActionBar

class HomePageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBar(R.id.toolbar){
            title = getString(R.string.home_page)

        }

        setupViewFragment()
    }


    private fun setupViewFragment() {

        supportFragmentManager.findFragmentById(R.id.contentFrame)?:
        replaceFragmentInActivity(HomePageFragment.newInstance(),R.id.contentFrame)

    }


    fun obtainViewModel(): HomePageViewModel = obtainViewModel(HomePageViewModel::class.java)

}
