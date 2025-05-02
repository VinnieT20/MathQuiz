package com.example.quiz.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.quiz.Activities.QuestionActivity3


import com.example.quiz.databinding.ItemSets3Binding
import com.example.quiz.models.SetModel3


class SetAdapter3 (private val context:Context,private val list: ArrayList<SetModel3>):
    RecyclerView.Adapter<SetAdapter3.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding3 = ItemSets3Binding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding3)
    }

    override fun onBindViewHolder(holder:ViewHolder,position:Int){
        val model = list[position]
        holder.binding3.setName3.text = model.setNmae3

        holder.itemView.setOnClickListener{
            val intent = Intent(context, QuestionActivity3::class.java)
            intent.putExtra("set3",model.setNmae3)
            context.startActivity(intent)
        }
    }
    override fun getItemCount():Int=list.size
    class ViewHolder(val binding3: ItemSets3Binding):RecyclerView.ViewHolder(binding3.root)
}