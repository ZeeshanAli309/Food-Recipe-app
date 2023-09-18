package com.example.recipeappclone

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeappclone.databinding.CategoryRvBinding

class CategoryAdapter(var dataList: ArrayList<Recipe>, var context: Context ):RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: CategoryRvBinding ):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       var view = CategoryRvBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(dataList.get(position).img).into(holder.binding.img)
        holder.binding.tittle.text=dataList.get(position).tittle
        var temp=dataList.get(position).ing.split("\n".toRegex()).dropLastWhile { it.isEmpty() }
        holder.binding.time.text=temp[0]
        holder.binding.next.setOnClickListener {
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