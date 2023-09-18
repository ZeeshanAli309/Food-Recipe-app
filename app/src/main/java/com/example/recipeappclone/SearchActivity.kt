package com.example.recipeappclone

import android.annotation.SuppressLint
import android.inputmethodservice.InputMethodService
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.content.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.recipeappclone.databinding.ActivitySearchBinding
import java.util.Objects

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var rvAdapter:SearchAdapter
    private lateinit var dataList: ArrayList<Recipe>
    private lateinit var recipe:List<Recipe?>
    @SuppressLint("ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySearchBinding.inflate(layoutInflater);
        setContentView(binding.root)
        binding.search.requestFocus()
        var db = Room.databaseBuilder(this@SearchActivity, AppDatabase::class.java,"db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db").build()
        var daoObj=db.getdao()
        recipe=daoObj.getAll()

        setUpRecyclerView()


        binding.search.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(s.toString()!=""){
                    filter(s.toString())
                }
                else{
                    setUpRecyclerView()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        binding.goBackHome.setOnClickListener {
            finish()
        }

    }

    private fun filter(filterText: String) {
        var filterData=ArrayList<Recipe>();
        for (i in recipe.indices) {
            if (recipe[i]!!.tittle.lowercase().contains(filterText.lowercase())) {
filterData.add(recipe[i]!!)
            }
            rvAdapter.filterList(filterList = filterData)
        }
    }

    private fun setUpRecyclerView() {
        dataList= ArrayList();
        binding.rvSearch.layoutManager=
            LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        for (i in recipe!!.indices){
            if (recipe[i]!!.category.contains("Popular")){
                dataList.add(recipe[i]!!)
            }
            rvAdapter= SearchAdapter(dataList,this)
            binding.rvSearch.adapter=rvAdapter;
        }
    }
}