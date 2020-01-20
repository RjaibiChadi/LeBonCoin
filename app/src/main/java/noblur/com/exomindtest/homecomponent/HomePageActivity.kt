package noblur.com.exomindtest.homecomponent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import noblur.com.exomindtest.R
import noblur.com.exomindtest.albumdetailcomponent.AlbumDetailActivity
import noblur.com.exomindtest.data.entities.Event
import noblur.com.exomindtest.utils.obtainViewModel
import noblur.com.exomindtest.utils.replaceFragmentInActivity
import noblur.com.exomindtest.utils.setupActionBar

class HomePageActivity : AppCompatActivity() {


    private lateinit var viewModel: HomePageViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBar(R.id.toolbar){
            title = getString(R.string.home_page)

        }

        setupViewFragment()


        viewModel =obtainViewModel().apply {

            openAlbumEvent.observe(this@HomePageActivity, Observer<Event<Int>> { event->

                event?.getContentIfNotHandled()?.let {userId->


                    this@HomePageActivity.openAlbumDetail(userId)
                } })


        }
    }

    private fun openAlbumDetail(userId: Int) {

        val intent = Intent(this, AlbumDetailActivity::class.java).apply {
            putExtra(AlbumDetailActivity.EXTRA_USER_ID, userId.toString())
        }
        startActivity(intent)
    }


    private fun setupViewFragment() {

        supportFragmentManager.findFragmentById(R.id.contentFrame)?:
        replaceFragmentInActivity(HomePageFragment.newInstance(),R.id.contentFrame)

    }


    fun obtainViewModel(): HomePageViewModel = obtainViewModel(HomePageViewModel::class.java)

}
