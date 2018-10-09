package com.example.kotlincleancode4android.homescreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.kotlincleancode4android.FlightViewModel
import com.example.kotlincleancode4android.R
import java.util.ArrayList

/**
 * Created by Mohanraj Karatadipalayam on 08/10/18.
 */
// TODO - check the context, should it be a WeakReference
class FlightListAdapter(context: Context,
                        private val listOfVMFlights: ArrayList<FlightViewModel>) : BaseAdapter() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return listOfVMFlights.size
    }

    override fun getItem(position: Int): Any {
        return listOfVMFlights[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView

        if (view == null) {
            view = layoutInflater.inflate(R.layout.cell_trip_list, null)
            view?.let {
                val viewHolder = ViewHolder()
                viewHolder.flightNameTextView = view.findViewById(R.id.tv_flightNumberValue) as TextView
                viewHolder.startTimeTextView = view.findViewById(R.id.tv_flightTimeDescription) as TextView
                view.tag = viewHolder
            }
        }
        val viewHolder = view?.tag as ViewHolder
        viewHolder.flightNameTextView?.text = listOfVMFlights[position].flightName
        viewHolder.startTimeTextView?.text = listOfVMFlights[position].noOfDaysToFly
        return view
    }
}

class ViewHolder {
    var flightNameTextView: TextView? = null
    var startTimeTextView: TextView? = null
}