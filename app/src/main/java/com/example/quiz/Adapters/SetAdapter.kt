package com.example.quiz.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.Activities.QuestionActivity
import com.example.quiz.databinding.ItemSetsBinding
import com.example.quiz.models.SetModel


class SetAdapter(private val context: Context, private val list: ArrayList<SetModel>) :
    RecyclerView.Adapter<SetAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSetsBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]
        holder.binding.setName.text = model.setNmae

       holder.itemView.setOnClickListener{
           val intent= Intent(context,QuestionActivity::class.java)
           intent.putExtra("set",model.setNmae)
           context.startActivity(intent)
       }
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(val binding: ItemSetsBinding) : RecyclerView.ViewHolder(binding.root)
}
