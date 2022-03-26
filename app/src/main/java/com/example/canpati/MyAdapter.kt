package com.example.canpati

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val restaurantList : ArrayList<Restaurant>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_restaurant,
        parent,false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {

        val restaurant: Restaurant = restaurantList[position]
        holder.name.text = restaurant.name
        holder.address.text = restaurant.address
        holder.phone.text = restaurant.phone
        holder.city.text = restaurant.city

    }

    override fun getItemCount(): Int {
        return restaurantList.size
    }


    public class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val name : TextView = itemView.findViewById(R.id.tvRestaurantName)
        val address : TextView = itemView.findViewById(R.id.tvRestaurantAddress)
        val phone : TextView = itemView.findViewById(R.id.tvRestaurantPhone)
        val city : TextView = itemView.findViewById(R.id.tvRestaurantCity)

    }
}