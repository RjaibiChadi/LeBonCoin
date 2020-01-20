package noblur.com.exomindtest.homecomponent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.user_item.view.*
import noblur.com.exomindtest.R
import noblur.com.exomindtest.data.entities.User


class UserAdapter (val users: MutableList<User>, val listenerItem: UserItemActionsListener )
    : RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val deviceContainer = itemView.container_user

        val deviceName = itemView.name_user
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val viewItem = LayoutInflater.from(parent.context).inflate(R.layout.user_item,parent,false)

        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val user = users[position]

        with(holder){


            deviceContainer.tag = user
            deviceName.text = user.name
            deviceContainer.setOnClickListener{
                listenerItem.onDeviceClicked(user)
            }
        }




    }
    override fun getItemCount(): Int {
        return users.size
    }


}