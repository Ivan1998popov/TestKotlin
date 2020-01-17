package com.monitoringplus.testkotlin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class HobbiesAdapter(val context: Context, private val hobbies: List<Hobby>) :
    RecyclerView.Adapter<HobbiesAdapter.MyViewHolder>() {


    companion object {
        val TAG: String = HobbiesAdapter::class.java.simpleName
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(view)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var currentHobby: Hobby? = null
        var currentPosition: Int = 0

        init {
            itemView.setOnClickListener {
                currentHobby?.let {
                    context.showToast(currentHobby!!.title + " Clicked !")
                }
            }

            itemView.imgShare.setOnClickListener {
                val message: String = "My hobby is: " + currentHobby!!.title

                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT, message)
                intent.type = "text/plain"

                context.startActivity(Intent.createChooser(intent, "Please select app: "))
            }

        }

        fun setData(hobby: Hobby?, pos: Int) {
            hobby?.let {
                itemView.txtItem.text = hobby.title
            }

            this.currentHobby = hobby
            this.currentPosition = pos
        }
    }

    override fun getItemCount(): Int {
        return hobbies.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val hobby = hobbies[position]
        holder.setData(hobby, position)
    }
}