package com.example.recipeappclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.recipeappclone.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {

    private lateinit var rvAdapter:CategoryAdapter
    private lateinit var dataList: ArrayList<Recipe>
    private val binding by lazy {
        ActivityCategoryBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
       binding.headerTitle.text=intent.getStringExtra("Tittle")


        setUpRecyclerView()
        binding.goBackHome.setOnClickListener{
            finish()
        }

    }
    private fun setUpRecyclerView() {
        dataList= ArrayList();
        binding.rvCategory.layoutManager=
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        var db = Room.databaseBuilder(this@CategoryActivity, AppDatabase::class.java,"db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db").build()
        var daoObj=db.getdao()
        var recipe=daoObj.getAll()
        for (i in recipe!!.indices){
            if (recipe[i]!!.category.contains(intent.getStringExtra("Category")!!)){
                dataList.add(recipe[i]!!)
                println(recipe[i])
            }
            rvAdapter=CategoryAdapter(dataList,this)
            binding.rvCategory.adapter=rvAdapter;
        }
    }
}