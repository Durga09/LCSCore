package com.durga.lc.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView

import com.durga.lc.R
import com.durga.lc.adapter.RoundsListAdater
import com.durga.lc.db.UsersDBHelper
import android.content.Intent.getIntent
import android.content.Intent.getIntent





// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [RoundsListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [RoundsListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RoundsListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var rounds_list: ListView
    lateinit var databaseHandler: UsersDBHelper;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_rounds_list, container, false)
        rounds_list = view.findViewById(R.id.rounds_list) as ListView
        val context: Context = context!!
        databaseHandler = UsersDBHelper(context)
        val currentCount=databaseHandler.getUsersCurrentCount()

        val userScores= databaseHandler.readUserScores(""+currentCount)
        var adapter = RoundsListAdater(context,userScores)
        rounds_list.adapter = adapter

        rounds_list.setOnItemClickListener { adapterView, view, i, l ->
            val bundle = Bundle()
            var roundVal=i+1;
            val frag2 = EditScoresFragment()
            frag2.arguments = bundle
            bundle.putInt("roundVal",roundVal)
            println(userScores[i])
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.frame_container,frag2)?.commit()

        }
        return view
    }


}
