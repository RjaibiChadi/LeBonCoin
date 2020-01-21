package noblur.com.exomindtest.photodetailcomponent

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
import noblur.com.exomindtest.albumdetailcomponent.AlbumAdapter
import noblur.com.exomindtest.albumdetailcomponent.AlbumDetailActivity
import noblur.com.exomindtest.albumdetailcomponent.AlbumDetailFragment
import noblur.com.exomindtest.albumdetailcomponent.AlbumDetailViewModel
import noblur.com.exomindtest.data.entities.Album
import noblur.com.exomindtest.data.entities.Photo

class PhotoDetailFragment : Fragment() {

    companion object {
        const val ARGUMENT_ALBUM_ID = "ALBUM_ID"


        fun newInstance(albumId:String) = PhotoDetailFragment().apply {

            arguments = Bundle().apply {
                putString(ARGUMENT_ALBUM_ID, albumId)
            }
        }
    }
    private lateinit var viewModel: PhotoDetailViewModel
    private lateinit var photAdapter: PhotoAdapter
    private lateinit var _photos: MutableList<Photo>
    private lateinit var albumId: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.photo_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = (activity as PhotoDetailActivity).obtainViewModel()


        _photos = mutableListOf()
        photAdapter = PhotoAdapter(context!!,_photos)

        albumId = arguments?.getString(ARGUMENT_ALBUM_ID).toString()

        /**
         * initialise recycle view
         */
        recyclerView.apply {

            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(activity)
            adapter = photAdapter

        }

        viewModel.run {


            photos.observe(this@PhotoDetailFragment, Observer { photos->

                showPhotos(photos)

            })

        }

    }

    private fun showPhotos(photos: List<Photo>?) {

        _photos.clear()
        _photos.addAll(photos!!)
        photAdapter.notifyDataSetChanged()
    }


    override fun onResume() {
        super.onResume()
        viewModel.start(albumId.toInt())
    }

}
