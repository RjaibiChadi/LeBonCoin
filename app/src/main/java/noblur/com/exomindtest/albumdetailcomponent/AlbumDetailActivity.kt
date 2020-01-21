package noblur.com.exomindtest.albumdetailcomponent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import noblur.com.exomindtest.R
import noblur.com.exomindtest.data.entities.Event
import noblur.com.exomindtest.homecomponent.HomePageViewModel
import noblur.com.exomindtest.photodetailcomponent.PhotoDetailActivity
import noblur.com.exomindtest.utils.obtainViewModel
import noblur.com.exomindtest.utils.replaceFragmentInActivity
import noblur.com.exomindtest.utils.setupActionBar

class AlbumDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER_ID = "USER_ID"

    }

    private lateinit var viewModel: AlbumDetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_detail)

        setupActionBar(R.id.toolbar){
            title = getString(R.string.album_detail)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        setupViewFragment()

        viewModel =obtainViewModel().apply {

            openPhotoEvent.observe(this@AlbumDetailActivity, Observer<Event<Int>> { event->

                event?.getContentIfNotHandled()?.let {albumId->


                    this@AlbumDetailActivity.openPhotoDetail(albumId)
                } })


        }
    }

    private fun openPhotoDetail(albumId: Int) {

        val intent = Intent(this, PhotoDetailActivity::class.java).apply {
            putExtra(PhotoDetailActivity.EXTRA_ALBUM_ID, albumId.toString())
        }
        startActivity(intent)
    }

    private fun setupViewFragment() {

        supportFragmentManager.findFragmentById(R.id.contentFrame)?:
        replaceFragmentInActivity(AlbumDetailFragment.newInstance(
            intent.getStringExtra(
            EXTRA_USER_ID)),
            R.id.contentFrame)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun obtainViewModel(): AlbumDetailViewModel = obtainViewModel(AlbumDetailViewModel::class.java)
}
