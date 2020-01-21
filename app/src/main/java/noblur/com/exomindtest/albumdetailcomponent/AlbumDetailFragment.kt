package noblur.com.exomindtest.albumdetailcomponent

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.home_page_fragment.*

import noblur.com.exomindtest.R
import noblur.com.exomindtest.data.entities.Album
import noblur.com.exomindtest.data.entities.User
import noblur.com.exomindtest.homecomponent.UserAdapter

class AlbumDetailFragment : Fragment(), AlbumItemActionsListener {

    companion object {
        const val ARGUMENT_USER_ID = "USER_ID"


        fun newInstance(userId:String) = AlbumDetailFragment().apply {

            arguments = Bundle().apply {
                putString(ARGUMENT_USER_ID, userId)
            }
        }
    }

    private lateinit var viewModel: AlbumDetailViewModel
    private lateinit var albumAdapter: AlbumAdapter
    private lateinit var _albums: MutableList<Album>
    private lateinit var userId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.album_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        _albums = mutableListOf()
        albumAdapter = AlbumAdapter(_albums,this)


        viewModel = (activity as AlbumDetailActivity).obtainViewModel()

        userId = arguments?.getString(ARGUMENT_USER_ID).toString()

        /**
         * initialise recycle view
         */
        recyclerView.apply {

            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(activity)
            adapter = albumAdapter

        }


        viewModel.run {


            albums.observe(this@AlbumDetailFragment, Observer { albums->

                showAlbums(albums)

            })

        }

    }

    private fun showAlbums(albums: List<Album>?) {
        _albums.clear()
        _albums.addAll(albums!!)
        albumAdapter.notifyDataSetChanged()

    }

    override fun onResume() {
        super.onResume()
        viewModel.start(userId.toInt())
    }

    override fun onAlbumClicked(album: Album) {

        viewModel.showPhotos(album.id)

    }

}
