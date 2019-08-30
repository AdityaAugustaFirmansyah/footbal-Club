package com.example.aditya.matchscheduller.league


import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.*
import android.widget.SearchView
import com.example.aditya.matchscheduller.R
import com.example.aditya.matchscheduller.search.SearchMatch
import kotlinx.android.synthetic.main.fragment_list_league.view.*
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.startActivity

class ListLeagueFragment : Fragment() {

    val listLeague:ArrayList<League> = arrayListOf()
    lateinit var searchView:SearchView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_list_league, container, false)
        if (checkNetwork(context)){
            (activity as AppCompatActivity).supportActionBar?.show()
            fetch_data()
            val rvLeague = rootView.rv_list_league
            val adapter = LeagueAdapter(listLeague,context!!)
            adapter.listLeague = listLeague
            rvLeague.layoutManager = GridLayoutManager(context,2)
            rvLeague.adapter = adapter

        }else{
            alert("no connection").show()
        }
        return rootView
    }

    fun fetch_data(){
        val idLeague = resources.getStringArray(R.array.id_league)
        val nameLeague = resources.getStringArray(R.array.league)
        val imageLeague = resources.getStringArray(R.array.image_league)
        listLeague.clear()
        for (i in idLeague.indices){
            listLeague.add(League(imageLeague[i],nameLeague[i],idLeague[i]))
        }
    }

    companion object {
        fun newInstance(): ListLeagueFragment =
            ListLeagueFragment()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_search, menu)
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem = menu?.findItem(R.id.search_match)
        searchView = searchItem?.actionView as SearchView
        searchView.isIconified = false

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                startActivity<SearchMatch>("keySearch" to query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    fun checkNetwork(context:Context?):Boolean{
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
    }
}
