package com.example.recipeappclone

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.recipeappclone.databinding.ActivityRecipeBinding

class RecipeActivity : AppCompatActivity() {
    private lateinit var binding:ActivityRecipeBinding
    private var imgCrop=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImg)
        binding.tittle.text=intent.getStringExtra("tittle")

        binding.stepsData.text=intent.getStringExtra("des")
        var ing=intent.getStringExtra("ing")?.split("\n".toRegex())?.dropLastWhile { it.isEmpty() }?.toTypedArray()
        binding.time.text=ing!!.get(0)
        for (i in 1 until ing!!.size){
            binding.ingData.text=
                """${binding.ingData.text} 🟢 ${ing[i]}
                    
                """.trimIndent()
        }

        binding.steps.background=null
        binding.steps.setTextColor(getColor(R.color.black))

        binding.ing.setOnClickListener {
            binding.ing.setBackgroundResource(R.drawable.btn_ing)
            binding.ing.setTextColor(getColor(R.color.white))
            binding.steps.setTextColor(getColor(R.color.black))
            binding.steps.background=null
            binding.ingScroll.visibility=View.VISIBLE
            binding.stepsScrol.visibility=View.GONE

        }
        binding.steps.setOnClickListener {
            binding.steps.setBackgroundResource(R.drawable.btn_ing)
            binding.steps.setTextColor(getColor(R.color.white))
            binding.ing.setTextColor(getColor(R.color.black))
            binding.ing.background=null
            binding.ingScroll.visibility=View.GONE
            binding.stepsScrol.visibility=View.VISIBLE

        }

        binding.fullScreen.setOnClickListener {
            if (imgCrop){
                binding.itemImg.scaleType=ImageView.ScaleType.FIT_CENTER;
                Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImg)
                binding.fullScreen.setColorFilter(Color.BLACK,PorterDuff.Mode.SRC_ATOP)
                binding.shadow.visibility= View.GONE
                imgCrop=!imgCrop
            }else{
                binding.itemImg.scaleType=ImageView.ScaleType.CENTER_CROP;
                Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImg)
                binding.fullScreen.setColorFilter(null)
                imgCrop=!imgCrop
            }
        }
        binding.backBtn.setOnClickListener {
            finish()
        }

    }
}