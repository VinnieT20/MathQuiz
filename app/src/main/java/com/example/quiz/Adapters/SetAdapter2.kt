package com.example.quiz.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.Activities.QuestionActivity2


import com.example.quiz.databinding.ItemSets2Binding
import com.example.quiz.models.SetModel2


class SetAdapter2 (private val context: Context,private val list:ArrayList<SetModel2>):
    RecyclerView.Adapter<SetAdapter2.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding2 =ItemSets2Binding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding2)
    }
    override fun onBindViewHolder(holder:ViewHolder,position:Int){
        val model = list[position]
        holder.binding2.setName2.text = model.setNmae2

        holder.itemView.setOnClickListener{
            val intent = Intent(context,QuestionActivity2::class.java)
            intent.putExtra("set2",model.setNmae2)
            context.startActivity(intent)
        }
    }
    override fun getItemCount():Int=list.size
    class ViewHolder(val binding2:ItemSets2Binding):RecyclerView.ViewHolder(binding2.root)
}