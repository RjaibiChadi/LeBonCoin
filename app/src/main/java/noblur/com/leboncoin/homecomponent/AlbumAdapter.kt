package noblur.com.leboncoin.homecomponent

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.album_item.view.*
import noblur.com.leboncoin.R
import noblur.com.leboncoin.data.entities.Album


class AlbumAdapter (val context: Context, val albums: MutableList<Album>)
    : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){



        val deviceContainer = itemView.container_user

        val userName = itemView.name_user
        val albumPhoto = itemView.photo_album

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val viewItem = LayoutInflater.from(parent.context).inflate(R.layout.album_item,parent,false)

        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val album = albums[position]

        with(holder){


            deviceContainer.tag = album
            userName.text = album.title
            Picasso.get().load(album.url).into(albumPhoto)

        }




    }
    override fun getItemCount(): Int {
        return albums.size
    }


}