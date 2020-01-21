package noblur.com.exomindtest.photodetailcomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import noblur.com.exomindtest.R
import noblur.com.exomindtest.utils.obtainViewModel
import noblur.com.exomindtest.utils.replaceFragmentInActivity
import noblur.com.exomindtest.utils.setupActionBar

class PhotoDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ALBUM_ID = "ALBUM_ID"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_detail)

        setupActionBar(R.id.toolbar){
            title = getString(R.string.photo_detail)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        setupViewFragment()
    }

    private fun setupViewFragment() {

        supportFragmentManager.findFragmentById(R.id.contentFrame)?:
        replaceFragmentInActivity(
            PhotoDetailFragment.newInstance(
                intent.getStringExtra(
                    EXTRA_ALBUM_ID
                )),
            R.id.contentFrame)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun obtainViewModel(): PhotoDetailViewModel = obtainViewModel(PhotoDetailViewModel::class.java)
}
