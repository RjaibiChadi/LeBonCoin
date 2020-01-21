package noblur.com.exomindtest.homecomponent

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.user_item.view.*
import noblur.com.exomindtest.R
import noblur.com.exomindtest.data.entities.User
import java.util.*


class UserAdapter (val users: MutableList<User>,val usersSearch: MutableList<User>, val listenerItem: UserItemActionsListener )
    : RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){



        val deviceContainer = itemView.container_user

        val userName = itemView.name_user
        val userPseudo = itemView.username
        val userEmail = itemView.email_user
        val userPhone = itemView.phone_user
        val userWebsite = itemView.website_user
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val viewItem = LayoutInflater.from(parent.context).inflate(R.layout.user_item,parent,false)

        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val user = usersSearch[position]

        with(holder){


            deviceContainer.tag = user
            userName.text = user.name
            userPseudo.text = user.username
            userEmail.text = user.email
            userPhone.text = user.phone
            userWebsite.text = user.website
            deviceContainer.setOnClickListener{
                listenerItem.onDeviceClicked(user)
            }
        }




    }
    override fun getItemCount(): Int {
        return usersSearch.size
    }

    fun filter(text: String) {
        var text = text

        if (text.isEmpty()) {
            usersSearch.clear()
            usersSearch.addAll(users)

        } else {
            val result: ArrayList<User> = ArrayList()
            text = text.toLowerCase()
            for (item in users) { //match by name or phone
                if (item.name.toLowerCase().contains(text) || item.username.toLowerCase().contains(text)) {
                    result.add(item)
                }
            }
            usersSearch.clear()
            usersSearch.addAll(result)
        }
        notifyDataSetChanged()
    }

}