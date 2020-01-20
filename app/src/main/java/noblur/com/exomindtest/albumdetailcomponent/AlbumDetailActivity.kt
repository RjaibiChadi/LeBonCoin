package noblur.com.exomindtest.albumdetailcomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import noblur.com.exomindtest.R
import noblur.com.exomindtest.utils.obtainViewModel
import noblur.com.exomindtest.utils.replaceFragmentInActivity
import noblur.com.exomindtest.utils.setupActionBar

class AlbumDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER_ID = "USER_ID"

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_detail)

        setupActionBar(R.id.toolbar){
            title = getString(R.string.album_detail)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        setupViewFragment()
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
