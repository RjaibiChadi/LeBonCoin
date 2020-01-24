package noblur.com.leboncoin.homecomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.home_page_fragment.*

import noblur.com.leboncoin.R
import noblur.com.leboncoin.data.entities.Album

class HomePageFragment : Fragment() {

    companion object {
        lateinit var _albums: MutableList<Album>

        fun newInstance() = HomePageFragment()
    }

    private lateinit var viewModel: HomePageViewModel
    private lateinit var albumAdapter: AlbumAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_page_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = (activity as HomePageActivity).obtainViewModel()


        _albums = mutableListOf()
        albumAdapter = AlbumAdapter(context!!,_albums)

        /**
         * initialise recycle view
         */
        recyclerView.apply {

            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(activity)
            adapter = albumAdapter

        }


        viewModel.run {


            albums.observe(this@HomePageFragment, Observer { albums->

                showAlbums(albums)

            })

        }


    }

    private fun showAlbums(users: List<Album>?) {

        _albums.clear()
        _albums.addAll(users!!)

        albumAdapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()

        viewModel.start()
    }




}
