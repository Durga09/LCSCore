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
 * [EditScoresFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [EditScoresFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditScoresFragment : Fragment() {
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

    lateinit var member1_editext: EditText
    lateinit var member2_editext: EditText
    lateinit var member3_editext: EditText
    lateinit var member4_editext: EditText
    lateinit var member5_editext: EditText
    lateinit var member6_editext: EditText
    lateinit var member7_editext: EditText
    lateinit var member8_editext: EditText
    private val sharedPrefFile = "lcShared"

    lateinit var score:ArrayList<UserModel>
    var  membe1TotalScore =0;
    var  membe2TotalScore =0;
    var  membe3TotalScore =0;
    var  membe4TotalScore =0;
    var  membe5TotalScore =0;
    var  membe6TotalScore =0;
    var  membe7TotalScore =0;
    var  membe8TotalScore =0;
    private var maxScore:Int=0
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

        val view = inflater!!.inflate(R.layout.fragment_edit_scores, container, false)
        val submit_values = view.findViewById(R.id.submit_values) as Button
        val view_values = view.findViewById(R.id.view_values) as Button
        val roundVal = arguments?.getInt("roundVal")

        member1_textview = view.findViewById(R.id.member1_name) as TextView
        member2_textview = view.findViewById(R.id.member2_name) as TextView
        member3_textview = view.findViewById(R.id.member3_name) as TextView
        member4_textview = view.findViewById(R.id.member4_name) as TextView
        member5_textview = view.findViewById(R.id.member5_name) as TextView
        member6_textview = view.findViewById(R.id.member6_name) as TextView
        member7_textview = view.findViewById(R.id.member7_name) as TextView
        member8_textview = view.findViewById(R.id.member8_name) as TextView

        member1_editext = view.findViewById(R.id.member1) as EditText
        member2_editext = view.findViewById(R.id.member2) as EditText
        member3_editext = view.findViewById(R.id.member3) as EditText
        member4_editext = view.findViewById(R.id.member4) as EditText
        member5_editext = view.findViewById(R.id.member5) as EditText
        member6_editext = view.findViewById(R.id.member6) as EditText
        member7_editext = view.findViewById(R.id.member7) as EditText
        member8_editext = view.findViewById(R.id.member8) as EditText


        val context: Context = context!!
        val databaseHandler: UsersDBHelper = UsersDBHelper(context)

        val currentCount=databaseHandler.getUsersCurrentCount()
        val currentRoundCount=databaseHandler.getScoreCurrentCount()
        val round: Int = roundVal!! // asserting and smart cast
        var userDatail=databaseHandler.readUser(""+currentCount)

        println("currentCount:: "+currentCount+" ROUND:: "+round)
         score=databaseHandler.readUserScores(""+currentCount,round)
        println("currentCount:: "+score.size)
        val sharedPreferences: SharedPreferences = activity!!.getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)
        maxScore = sharedPreferences.getInt(getString(R.string.max_val_key),0)
//        userScores = databaseHandler.readUserScores("" + currentCount)!!
//        println(userScores.size)
        indTotalScore()
        if(userDatail.size>0) {
            member1_textview.text = userDatail[0].member1
            member2_textview.text = userDatail[0].member2
            member3_textview.text = userDatail[0].member3
            member4_textview.text = userDatail[0].member4
            member5_textview.text = userDatail[0].member5
            member6_textview.text = userDatail[0].member6
            member7_textview.text = userDatail[0].member7
            member8_textview.text = userDatail[0].member8

            if (userDatail[0].member1 == null || userDatail[0].member1.equals("") || membe1TotalScore > maxScore) {
                member1_textview.visibility = View.GONE
                member1_editext.visibility = View.GONE
            }
            if (userDatail[0].member2 == null || userDatail[0].member2.equals("")|| membe2TotalScore > maxScore) {
                member2_textview.visibility = View.GONE
                member2_editext.visibility = View.GONE
            }
            if (userDatail[0].member3 == null || userDatail[0].member3.equals("")|| membe3TotalScore > maxScore) {
                member3_textview.visibility = View.GONE
                member3_editext.visibility = View.GONE
            }
            if (userDatail[0].member4 == null || userDatail[0].member4.equals("")|| membe4TotalScore > maxScore) {
                member4_textview.visibility = View.GONE
                member4_editext.visibility = View.GONE
            }
            if (userDatail[0].member5 == null || userDatail[0].member5.equals("")|| membe5TotalScore > maxScore) {
                member5_textview.visibility = View.GONE
                member5_editext.visibility = View.GONE
            }
            if (userDatail[0].member6 == null || userDatail[0].member6.equals("")|| membe6TotalScore > maxScore) {
                member6_textview.visibility = View.GONE
                member6_editext.visibility = View.GONE
            }
            if (userDatail[0].member7 == null || userDatail[0].member7.equals("")|| membe7TotalScore > maxScore) {
                member7_textview.visibility = View.GONE
                member7_editext.visibility = View.GONE
            }
            if (userDatail[0].member8 == null || userDatail[0].member8.equals("")|| membe8TotalScore > maxScore) {
                member8_textview.visibility = View.GONE
                member8_editext.visibility = View.GONE
            }
        }

        if(score.size>0) {
            member1_editext.setText(score[0].member1)
            member2_editext.setText(score[0].member2)
            member3_editext.setText(score[0].member3)
            member4_editext.setText(score[0].member4)
            member5_editext.setText(score[0].member5)
            member6_editext.setText(score[0].member6)
            member7_editext.setText(score[0].member7)
            member8_editext.setText(score[0].member8)
        }

        submit_values.setOnClickListener {
            var mem1Val= member1_editext.text.toString();
            var mem2Val= member2_editext.text.toString();
            var mem3Val= member3_editext.text.toString();
            var mem4Val= member4_editext.text.toString();
            var mem5Val= member5_editext.text.toString();
            var mem6Val= member6_editext.text.toString();
            var mem7Val= member7_editext.text.toString();
            var mem8Val= member8_editext.text.toString();
            if(mem1Val !=null && mem1Val.equals("")){
                mem1Val="0";
            }
            if(mem2Val !=null && mem2Val.equals("")){
                mem2Val="0";
            }
            if(mem3Val !=null && mem3Val.equals("")){
                mem3Val="0";
            }
            if(mem4Val !=null && mem4Val.equals("")){
                mem4Val="0";
            }
            if(mem5Val !=null && mem5Val.equals("")){
                mem5Val="0";
            }
            if(mem6Val !=null && mem6Val.equals("")){
                mem6Val="0";
            }
            if(mem7Val !=null && mem7Val.equals("")){
                mem7Val="0";
            }
            if(mem8Val !=null && mem8Val.equals("")){
                mem8Val="0";
            }
//            var roundNo=currentRoundCount+1
            val userScores= UserModel(""+currentCount,round,mem1Val,mem2Val,mem3Val,mem4Val,mem5Val,mem6Val,mem7Val,mem8Val)
            databaseHandler.updateScore(userScores,round)
            showCommonAlert(context,"Score updated successfully !")
        }

        return view;
    }
    fun showCommonAlert(mContext: Context, message: String) {
        val builder1 = AlertDialog.Builder(mContext)
        builder1.setMessage(message)
        builder1.setCancelable(false)
        builder1.setTitle(mContext.resources.getString(R.string.app_name))

        builder1.setPositiveButton(
            "OK"
        ) { dialog, id -> dialog.cancel()
           /* member1_editext.setText("")
            member2_editext.setText("")
            member3_editext.setText("")
            member4_editext.setText("")
            member5_editext.setText("")
            member6_editext.setText("")
            member7_editext.setText("")*/
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.frame_container,ViewScoreFragment())?.commit()


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
    fun indTotalScore(){
        for (i in score.indices) {

            var item = score[i]
//        for (i in userScores.size) {


            membe1TotalScore = membe1TotalScore + item.member1.toInt();
            membe2TotalScore = membe2TotalScore + item.member2.toInt();
            membe3TotalScore = membe3TotalScore + item.member3.toInt();
            membe4TotalScore = membe4TotalScore + item.member4.toInt();
            membe5TotalScore = membe5TotalScore + item.member5.toInt();
            membe6TotalScore = membe6TotalScore + item.member6.toInt();
            membe7TotalScore = membe7TotalScore + item.member7.toInt();
            membe8TotalScore = membe8TotalScore + item.member8.toInt();
        }
    }
}
