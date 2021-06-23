package com.eugene.app.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.eugene.app.R
import com.eugene.domain.model.PasswordModel
import java.text.SimpleDateFormat
import java.util.*

class PassListAdapter(private val colorsArray : IntArray) : RecyclerView.Adapter<PassListAdapter.PassViewHolder>() {

    var onItemClick : ((PasswordModel) -> Unit)? = null
    var onItemLongClick : ((PasswordModel) -> Unit)? = null

    val formatter = SimpleDateFormat("hh:mm", Locale.getDefault())

    var dataList : List<PasswordModel>? = null
    set(value){
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PassViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_password_entity, parent, false)
        return PassViewHolder(view, colorsArray)
    }

    override fun onBindViewHolder(holder: PassViewHolder, position: Int) {
        holder.setPasswordModel(dataList!![position])
    }

    override fun getItemCount(): Int = dataList?.size ?: 0

    inner class PassViewHolder(v : View, private val colorsArray: IntArray) : RecyclerView.ViewHolder(v){

        init{
            v.setOnClickListener{
                onItemClick?.invoke(dataList!![adapterPosition])
            }
            v.setOnLongClickListener{
                if(onItemLongClick?.invoke(dataList!![adapterPosition]) != null) true
                else true
            }
        }

        private val cvIcon : CardView = v.findViewById(R.id.cvIcon)
        private val tvFirstLetter : TextView = v.findViewById(R.id.tvFirstLetter)
        private val tvPassModelName : TextView = v.findViewById(R.id.tvPassModelName)
        private val tvPassModelDescription : TextView = v.findViewById(R.id.tvPassModelDescription)
        private val tvPassModelTime : TextView = v.findViewById(R.id.tvPassModelTime)

        fun setPasswordModel(passwordModel: PasswordModel){

            val color = colorsArray[Math.abs(passwordModel.name.hashCode()) % colorsArray.size]
            cvIcon.setCardBackgroundColor(color)

            tvFirstLetter.text = passwordModel.name.first().toString()

            tvPassModelName.text = passwordModel.name

            tvPassModelDescription.text = passwordModel.description

            tvPassModelTime.text = formatter.format(Date(passwordModel.time))
        }

    }

}