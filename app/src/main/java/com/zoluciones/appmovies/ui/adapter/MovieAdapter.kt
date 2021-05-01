package com.zoluciones.appmovies.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zoluciones.appmovies.R
import com.zoluciones.appmovies.base.BaseViewHolder
import com.zoluciones.appmovies.databinding.RowMovieBinding
import com.zoluciones.appmovies.model.Movie
import com.zoluciones.appmovies.model.MoviePage

class MovieAdapter(
    private var list: List<Movie>,
    private val listener: OnMovieAdapterListener
)  : RecyclerView.Adapter<BaseViewHolder<*>>()  {

    interface OnMovieAdapterListener{
        fun itemClick(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_movie,parent,false)
        )
    }

    fun update(newList: List<Movie>){
        this.list = newList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MovieViewHolder -> holder.bind(list[position], position)
        }
    }

    override fun getItemCount(): Int {
       return list.size
    }

    inner class MovieViewHolder(itemView: View): BaseViewHolder<Movie>(itemView){
        private val binding = RowMovieBinding.bind(itemView)
        override fun bind(item: Movie, position: Int) {
            binding.tvTitle.text = item.title
            Glide.with(itemView.context).load(item.imgUrl).into(binding.imgMovie)
            binding.root.setOnClickListener {
                listener.itemClick(item)
            }
        }
    }
}