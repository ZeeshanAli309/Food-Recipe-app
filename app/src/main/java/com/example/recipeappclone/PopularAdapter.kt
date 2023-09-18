package com.example.recipeappclone

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeappclone.databinding.PopularRvItemBinding

class PopularAdapter(var dataList: ArrayList<Recipe>,var context: Context):RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: PopularRvItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       var binding=PopularRvItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
      return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var time=dataList.get(position).ing.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
       holder.binding.popularTime.text=time.get(0)
        holder.binding.popularTxt.text=dataList.get(position).tittle
        Glide.with(context).load(dataList.get(position).img).into(holder.binding.popularImg)
        holder.itemView.setOnClickListener {
            var myIntent=Intent(context,RecipeActivity::class.java)
            myIntent.putExtra("img",dataList.get(position).img)
            myIntent.putExtra("tittle",dataList.get(position).tittle)
            myIntent.putExtra("des",dataList.get(position).des)
            myIntent.putExtra("ing",dataList.get(position).ing)
            myIntent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(myIntent)
        }
    }
}