package noblur.com.leboncoin.homecomponent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import noblur.com.leboncoin.R
import noblur.com.leboncoin.data.entities.Event
import noblur.com.leboncoin.utils.obtainViewModel
import noblur.com.leboncoin.utils.replaceFragmentInActivity
import noblur.com.leboncoin.utils.setupActionBar

class HomePageActivity : AppCompatActivity() {


    private lateinit var viewModel: HomePageViewModel


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
