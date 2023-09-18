package com.example.recipeappclone

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.recipeappclone.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var rvAdapter:PopularAdapter
    private lateinit var dataList: ArrayList<Recipe>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
        binding.search.setOnClickListener{
            startActivity(Intent(this,SearchActivity::class.java))
        }
        binding.salad.setOnClickListener {
            var intent=Intent(this@HomeActivity,CategoryActivity::class.java)
            intent.putExtra("Tittle","Salad")
            intent.putExtra("Category","Salad")
            startActivity(intent)
        }
        binding.mainDish.setOnClickListener {
            var intent=Intent(this@HomeActivity,CategoryActivity::class.java)
            intent.putExtra("Tittle","main dish")
            intent.putExtra("Category","Dish")
            startActivity(intent)
        }
        binding.Drinks.setOnClickListener {
            var intent=Intent(this@HomeActivity,CategoryActivity::class.java)
            intent.putExtra("Tittle","Drinks")
            intent.putExtra("Category","Drinks")
            startActivity(intent)
        }
        binding.Dessert.setOnClickListener {
            var intent=Intent(this@HomeActivity,CategoryActivity::class.java)
            intent.putExtra("Tittle","Desserts")
            intent.putExtra("Category","Desserts")
            startActivity(intent)
        }
        binding.more.setOnClickListener {
            var dialog=Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.bottom_sheet)


            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialog.window!!.setBackgroundDrawable(ColorDrawable(
                Color.TRANSPARENT
            ))
           dialog.window!!.setGravity(Gravity.BOTTOM)
            dialog.show()

        }
    }

    private fun setUpRecyclerView() {
        dataList= ArrayList();
        binding.rvPopular.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        var db = Room.databaseBuilder(this@HomeActivity, AppDatabase::class.java,"db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db").build()
        var daoObj=db.getdao()
        var recipe=daoObj.getAll()
        for (i in recipe!!.indices){
            if (recipe[i]!!.category.contains("Popular")){
                dataList.add(recipe[i]!!)
            }
            rvAdapter=PopularAdapter(dataList,this)
            binding.rvPopular.adapter=rvAdapter;
        }
    }
}