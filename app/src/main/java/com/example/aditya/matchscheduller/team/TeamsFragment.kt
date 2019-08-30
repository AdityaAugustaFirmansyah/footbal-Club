package com.example.aditya.matchscheduller.team


import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.*
import com.example.aditya.matchscheduller.R
import com.example.aditya.matchscheduller.api.ApiRepositery
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import utils.invisible
import utils.visible


class TeamsFragment : Fragment(), AnkoComponent<Context>, TeamsView {
    override fun showError(error: String) {
        swipeRefresh.isRefreshing = false
        view?.snackbar(error)
    }

    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: TeamsPresenter
    private lateinit var adapter: TeamsAdapter
    private lateinit var spinner: Spinner
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var leagueName: String
    private lateinit var searchView: SearchView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        setHasOptionsMenu(true)
        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter
        adapter = TeamsAdapter(teams) {
            startActivity<TeamDetail>("id" to "${it.teamId}","desc" to "${it.teamDescription}","name" to "${it.teamName}")
        }
        val request = ApiRepositery()
        val gson = Gson()
        presenter = TeamsPresenter(this, request, gson)
        if(checkNetwork(context)){
            listTeam.adapter = adapter
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    leagueName = spinner.selectedItem.toString()
                    presenter.getTeamList(leagueName)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }

            swipeRefresh.onRefresh {
                presenter.getTeamList(leagueName)
            }

        }else{
            alert("No Connection").show()
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            spinner = spinner {
                id = R.id.spinner
            }
            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)

                relativeLayout{
                    lparams (width = matchParent, height = wrapContent)

                    listTeam = recyclerView {
                        id = R.id.list_team
                        lparams (width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                    }.lparams{
                        centerHorizontally()
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {

        super.onCreateOptionsMenu(menu, inflater)

        inflater?.inflate(R.menu.menu_search,menu)
        val searchItem = menu?.findItem(R.id.search_match)
        searchView = searchItem?.actionView as SearchView
        searchView.isIconified = false
        if(checkNetwork(context)){
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        if (!query.toString().isEmpty()) {
                            if (checkNetwork(context)) {
                                presenter.getSearchTeam(query.toString())
                            } else {
                                alert("No Connextion").show()
                            }
                        }
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        if (!newText.toString().isEmpty()){
                            if(checkNetwork(context)){
                                Log.d("INIKEYSEARCH",newText)
                                presenter.getSearchTeam(newText.toString())
                            }else{
                                alert("No Connextion").show()
                            }
                        }
                        return true
                    }

                })
        }else{
            alert("no connection").show()
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    fun checkNetwork(context:Context?):Boolean{
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
    }

    override fun showTeamList(data: List<Team>) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance():TeamsFragment = TeamsFragment()
    }

}
