package noblur.com.exomindtest.photodetailcomponent

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.photo_item.view.*
import kotlinx.android.synthetic.main.user_item.view.*
import kotlinx.android.synthetic.main.user_item.view.container_user
import noblur.com.exomindtest.R
import noblur.com.exomindtest.data.entities.Album
import noblur.com.exomindtest.data.entities.Photo
import noblur.com.exomindtest.data.entities.User
import java.util.*


class PhotoAdapter (val context: Context,val photos: MutableList<Photo> )
    : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val deviceContainer = itemView.container_user

        val thumbnailPhoto = itemView.thumbnail_photo

        val photoName = itemView.title_photo
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val viewItem = LayoutInflater.from(parent.context).inflate(R.layout.photo_item,parent,false)

        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val photo = photos[position]

        with(holder){


            deviceContainer.tag = photo
            photoName.text = photo.title
            Glide.with(context).load("http://via.placeholder.com/150/771796.png").into(thumbnailPhoto)
            Log.i("PhotoAdapter",photo.thumbnailUrl)
        }


    }
    override fun getItemCount(): Int {
        return photos.size
    }



}