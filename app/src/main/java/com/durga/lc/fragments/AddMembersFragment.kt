package com.durga.lc.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import com.durga.lc.R
import com.durga.lc.db.UserModel
import com.durga.lc.db.UsersDBHelper

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AddMembersFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AddMembersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddMembersFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var member1_textview: TextView
    lateinit var member2_textview: TextView
    lateinit var member3_textview: TextView
    lateinit var member4_textview: TextView
    lateinit var member5_textview: TextView
    lateinit var member6_textview: TextView
    lateinit var member7_textview: TextView
    lateinit var member8_textview: TextView
    lateinit var max_score: EditText
    private val sharedPrefFile = "lcShared"
    lateinit var sharedPreferences:SharedPreferences
    lateinit var databaseHandler: UsersDBHelper
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
        sharedPreferences = activity!!.getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)

        // Inflate the layout for this fragment
//        Val view = inflater.inflate(R.layout.fragment_add_members, container, false)
        val view = inflater!!.inflate(R.layout.fragment_add_members, container, false)
        val add_members_btn = view.findViewById(R.id.add_members) as Button
        max_score = view.findViewById(R.id.max_score) as EditText

        member1_textview = view.findViewById(R.id.member1) as TextView
        member2_textview = view.findViewById(R.id.member2) as TextView
        member3_textview = view.findViewById(R.id.membe3) as TextView
        member4_textview = view.findViewById(R.id.member4) as TextView
        member5_textview = view.findViewById(R.id.member5) as TextView
        member6_textview = view.findViewById(R.id.member6) as TextView
        member7_textview = view.findViewById(R.id.member7) as TextView
        member8_textview = view.findViewById(R.id.member8) as TextView
        val context: Context = context!!

         databaseHandler= UsersDBHelper(context)
        val currentCount=databaseHandler.getUsersCurrentCount()
        var userDatail=databaseHandler.readUser(""+currentCount)

        val maxScore = sharedPreferences.getInt(getString(R.string.max_val_key),100)
        max_score.setText(""+maxScore)
        if(userDatail.size>0) {
            member1_textview.text = userDatail[0].member1
            member2_textview.text = userDatail[0].member2
            member3_textview.text = userDatail[0].member3
            member4_textview.text = userDatail[0].member4
            member5_textview.text = userDatail[0].member5
            member6_textview.text = userDatail[0].member6
            member7_textview.text = userDatail[0].member7
            member8_textview.text = userDatail[0].member8
        }

        add_members_btn.setOnClickListener {
            showAddMemberAlert(context,"Are you sure ! do you want to add members ? ")

        }
        return view;
    }
    fun showAddMemberAlert(mContext: Context, message: String) {
        val builder1 = AlertDialog.Builder(mContext)
        builder1.setMessage(message)
        builder1.setCancelable(true)
        builder1.setTitle(mContext.resources.getString(R.string.app_name))

        builder1.setPositiveButton(
            "Yes"
        ) { dialog, id -> dialog.cancel()
            var maxScore= max_score.text.toString();

            var mem1= member1_textview.text.toString();
            var mem2= member2_textview.text.toString();
            var mem3= member3_textview.text.toString();
            var mem4= member4_textview.text.toString();
            var mem5= member5_textview.text.toString();
            var mem6= member6_textview.text.toString();
            var mem7= member7_textview.text.toString();
            var mem8= member8_textview.text.toString();

            val editor:SharedPreferences.Editor =  sharedPreferences.edit()

            if((mem1==null || mem1.equals("")) && (mem2==null || mem2.equals(""))){
                showErrorCommonAlert(mContext,"Please enter member1 & memeber2")
            }else if(maxScore==null || maxScore.equals("")){
                showErrorCommonAlert(mContext,"Please enter Max Score")

            }else {
                editor.putInt(getString(R.string.max_val_key), maxScore.toInt())
                editor.apply()
                editor.commit()
                val currentCount = databaseHandler.getUsersCurrentCount()
                val id = currentCount + 1;
                val user = UserModel("" + id, 0, mem1, mem2, mem3, mem4, mem5, mem6, mem7, mem8)
                databaseHandler.deleteUser("" + id);
                databaseHandler.deleteScores();

                databaseHandler.insertUser(user)
                showCommonAlert(mContext, "Members are added successfully !")
            }

        }
        builder1.setNegativeButton(
            "No"
        ) { dialog, id -> dialog.cancel()


        }


        val alert11 = builder1.create()
        alert11.show()
    }
    fun showCommonAlert(mContext: Context, message: String) {
        val builder1 = AlertDialog.Builder(mContext)
        builder1.setMessage(message)
        builder1.setCancelable(true)
        builder1.setTitle(mContext.resources.getString(R.string.app_name))

        builder1.setPositiveButton(
            "OK"
        ) { dialog, id -> dialog.cancel()

            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.frame_container,EnterScoreFragment())?.commit()

        }

        /* builder1.setNegativeButton(
                     "No",
                     new DialogInterface.OnClickListener() {
                         public void onClick(DialogInterface dialog, int id) {
                             dialog.cancel();
                         }
                     });
     */
        val alert11 = builder1.create()
        alert11.show()
    }
    fun showErrorCommonAlert(mContext: Context, message: String) {
        val builder1 = AlertDialog.Builder(mContext)
        builder1.setMessage(message)
        builder1.setCancelable(true)
        builder1.setTitle(mContext.resources.getString(R.string.app_name))

        builder1.setPositiveButton(
            "OK"
        ) { dialog, id -> dialog.cancel()


        }

        /* builder1.setNegativeButton(
                     "No",
                     new DialogInterface.OnClickListener() {
                         public void onClick(DialogInterface dialog, int id) {
                             dialog.cancel();
                         }
                     });
     */
        val alert11 = builder1.create()
        alert11.show()
    }
}
