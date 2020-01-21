package noblur.com.exomindtest.homecomponent

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.home_page_fragment.*

import noblur.com.exomindtest.R
import noblur.com.exomindtest.data.entities.User

class HomePageFragment : Fragment(),UserItemActionsListener, SearchView.OnQueryTextListener {

    companion object {
        lateinit var _users: MutableList<User>
        lateinit var _usersSearch: MutableList<User>

        fun newInstance() = HomePageFragment()
    }

    private lateinit var viewModel: HomePageViewModel
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_page_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = (activity as HomePageActivity).obtainViewModel()


        _users = mutableListOf()
        _usersSearch = mutableListOf()
        userAdapter = UserAdapter(_users,_usersSearch,this@HomePageFragment)

        /**
         * initialise recycle view
         */
        recyclerView.apply {

            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(activity)
            adapter = userAdapter

        }


        viewModel.run {


            users.observe(this@HomePageFragment, Observer { users->

                showUsers(users)

            })

        }

        searchView_user!!.setOnQueryTextListener(this)

    }

    private fun showUsers(users: List<User>?) {

        _users.clear()
        _users.addAll(users!!)
        _usersSearch.clear()
        _usersSearch.addAll(users)
        userAdapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()

        viewModel.start()
    }

    override fun onDeviceClicked(user: User) {

        viewModel.showAlbum(user.id)

    }

    override fun onQueryTextSubmit(query: String?): Boolean {

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {

        userAdapter.filter(newText!!)

        return true
    }

}
