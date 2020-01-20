package noblur.com.exomindtest.albumdetailcomponent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.user_item.view.*
import noblur.com.exomindtest.R
import noblur.com.exomindtest.data.entities.Album
import noblur.com.exomindtest.data.entities.User


class AlbumAdapter (val albums: List<Album>, val listenerItem: AlbumItemActionsListener )
    : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val deviceContainer = itemView.container_user

        val deviceName = itemView.name_user
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val viewItem = LayoutInflater.from(parent.context).inflate(R.layout.album_item,parent,false)

        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val album = albums[position]

        with(holder){


            deviceContainer.tag = album
            deviceName.text = album.title
            deviceContainer.setOnClickListener{
                listenerItem.onAlbumClicked(album)
            }
        }




    }
    override fun getItemCount(): Int {
        return albums.size
    }


}