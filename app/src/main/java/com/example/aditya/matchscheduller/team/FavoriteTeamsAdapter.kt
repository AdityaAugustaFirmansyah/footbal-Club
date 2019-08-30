package com.example.aditya.matchscheduller.team

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.aditya.matchscheduller.R
import com.example.aditya.matchscheduller.sqlite.TeamFavorite
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

/**
 * Created by root on 1/16/18.
 */
class FavoriteTeamsAdapter(private val favorite: List<TeamFavorite>, private val listener: (TeamFavorite) -> Unit)
    : RecyclerView.Adapter<TeamFavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamFavoriteViewHolder {
        return TeamFavoriteViewHolder(
            TeamUI1().createView(
                AnkoContext.create(parent.context, parent)
            )
        )
    }

    override fun onBindViewHolder(holder: TeamFavoriteViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

    override fun getItemCount(): Int = favorite.size

}

class TeamUI1 : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
          linearLayout{
              lparams(width = matchParent, height = wrapContent)
              padding = dip(16)
              orientation = LinearLayout.HORIZONTAL

              imageView {
                  id = R.id.team_badge_favorite
              }.lparams{
                  height = dip(50)
                  width = dip(50)
              }

              textView {
                  id = R.id.team_name_favorite
                  textSize = 16f
              }.lparams{
                  margin = dip(15)
              }

          }
        }
    }

}

class TeamFavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val teamBadge: ImageView = view.find(R.id.team_badge_favorite)
    private val teamName: TextView = view.find(R.id.team_name_favorite)

    fun bindItem(favorite: TeamFavorite, listener: (TeamFavorite) -> Unit) {
        Picasso.get().load(favorite.teamBadge).into(teamBadge)
        teamName.text = favorite.teamName
        itemView.setOnClickListener { listener(favorite) }
    }
}