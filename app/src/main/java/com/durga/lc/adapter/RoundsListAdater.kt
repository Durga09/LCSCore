package com.durga.lc.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.durga.lc.R
import com.durga.lc.db.UserModel
import java.util.ArrayList

class RoundsListAdater(private val mContext: Context, private val mList: ArrayList<UserModel>) :
    BaseAdapter() {

    override fun getCount(): Int {
        return mList.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

//    override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View{


        val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.common_list_adapter, null)
        val conentText = view.findViewById(R.id.content_item) as TextView
        var roundCount=position+1;
        conentText.setText(" Round No : "+roundCount)


        return view
    }
}
