package com.durga.lc.fragments

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.TableLayout
import android.widget.TableRow
import com.durga.lc.R
import com.durga.lc.db.UsersDBHelper
import android.app.ActionBar
import android.content.SharedPreferences
import com.durga.lc.db.UserModel
import android.speech.tts.TextToSpeech
import android.R.attr.button
import android.widget.Toast
import java.util.*
import kotlin.collections.ArrayList


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ViewScoreFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ViewScoreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewScoreFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var stk: TableLayout
    lateinit var databaseHandler: UsersDBHelper;
    val list: ArrayList<Int> = ArrayList()
    lateinit var largest_textview: TextView
    lateinit var least_textview: TextView
    private val sharedPrefFile = "lcShared"
    private var maxScore:Int=0
    var  membe1TotalScore =0;
    var  membe2TotalScore =0;
    var  membe3TotalScore =0;
    var  membe4TotalScore =0;
    var  membe5TotalScore =0;
    var  membe6TotalScore =0;
    var  membe7TotalScore =0;
    var  membe8TotalScore =0;
     var currentCount:Int = 0
    lateinit var userScores:ArrayList<UserModel>
    lateinit var  user:ArrayList<UserModel>
    var textToSpeech: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    fun TextToSpeechFunction() {

//        val textholder = editText.getText().toString()

        textToSpeech?.speak("Prasad Score 100", TextToSpeech.QUEUE_FLUSH, null)

//        Toast.makeText(context, textholder, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {

        textToSpeech?.shutdown()

        super.onDestroy()
    }

    fun onInit(Text2SpeechCurrentStatus: Int) {

        if (Text2SpeechCurrentStatus == TextToSpeech.SUCCESS) {

            textToSpeech?.setLanguage(Locale.US)


            TextToSpeechFunction()
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_view_score, container, false)
         stk = view.findViewById(R.id.table_main) as TableLayout
        val edit_score = view.findViewById(R.id.edit_score) as Button

        largest_textview = view.findViewById(R.id.hiegh) as TextView
        least_textview = view.findViewById(R.id.low) as TextView
//        textToSpeech = TextToSpeech(context,this)

        val context: Context = context!!
         databaseHandler = UsersDBHelper(context)
        val sharedPreferences: SharedPreferences = activity!!.getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)
         maxScore = sharedPreferences.getInt(getString(R.string.max_val_key),0)

        edit_score.setOnClickListener {
            fun newInstance() = RoundsListFragment()

//            childFragmentManager.beginTransaction().replace(R.id.container,newInstance()).commit()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.frame_container,RoundsListFragment())?.commit()

        }
        currentCount = databaseHandler.getUsersCurrentCount()

         user = databaseHandler.readUser("" + currentCount)

        if(user!=null && user.size>0) {
            init()
        }
        return view
    }
    fun spaceVal(): String {

        var sp= "   ";
        return  sp;
    }
    fun addVal(): String {

        var sp= "   ";
        return  sp;
    }
    fun init() {

        userScores = databaseHandler.readUserScores("" + currentCount)!!
        println(userScores.size)
        indTotalScore()

        val tbrow0 = TableRow(activity)
        val tv0 = TextView(activity)
        tv0.text = "  R.No " + spaceVal()
        tv0.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);
        tv0.setTextColor(Color.WHITE)
        tbrow0.addView(tv0)
        val tv1 = TextView(activity)
        tv1.text = user[0].member1 + spaceVal()
        tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);

        tv1.setTextColor(Color.WHITE)
        if (user[0].member1 != null && !user[0].member1.equals("") && membe1TotalScore <= maxScore) {

            tbrow0.addView(tv1)
        }
        val tv2 = TextView(activity)
        tv2.text = user[0].member2 + spaceVal()
        tv2.setTextColor(Color.WHITE)
        tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);
        if (user[0].member2 != null && !user[0].member2.equals("") && membe2TotalScore <= maxScore) {
            tbrow0.addView(tv2)
        }
        val tv3 = TextView(activity)
        tv3.text = user[0].member3 + spaceVal()
        tv3.setTextColor(Color.WHITE)
        tv3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);
        if (user[0].member3 != null && !user[0].member3.equals("") && membe3TotalScore <= maxScore) {

            tbrow0.addView(tv3)
        }

        val tv4 = TextView(activity)
        tv4.text = user[0].member4 + spaceVal()
        tv4.setTextColor(Color.WHITE)
        tv4.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);
        if (user[0].member4 != null && !user[0].member4.equals("") && membe4TotalScore <= maxScore) {

            tbrow0.addView(tv4)
        }
        val tv5 = TextView(activity)
        tv5.text = user[0].member5 + spaceVal()
        tv5.setTextColor(Color.WHITE)
        tv5.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);
        if (user[0].member5 != null && !user[0].member5.equals("") && membe5TotalScore <= maxScore) {

            tbrow0.addView(tv5)
        }
        val tv6 = TextView(activity)
        tv6.text = user[0].member6 + spaceVal()
        tv6.setTextColor(Color.WHITE)
        tv6.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);
        if (user[0].member6 != null && !user[0].member6.equals("") && membe6TotalScore <= maxScore) {

            tbrow0.addView(tv6)
        }
        val tv7 = TextView(activity)
        tv7.text = user[0].member7 + spaceVal()
        tv7.setTextColor(Color.WHITE)
        tv7.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);
        if (user[0].member7 != null && !user[0].member7.equals("") && membe7TotalScore <= maxScore) {

            tbrow0.addView(tv7)
        }
        val tv8 = TextView(activity)
        tv8.text = user[0].member8 + spaceVal()
        tv8.setTextColor(Color.WHITE)
        tv8.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);
        if (user[0].member8 != null && !user[0].member8.equals("") && membe8TotalScore <= maxScore) {

            tbrow0.addView(tv8)
        }
        stk.addView(tbrow0)

        var roundNo = 0;

        val tbrowLine = TableRow(activity)
        val tableRowParamsline = TableLayout.LayoutParams(
            TableLayout.LayoutParams.FILL_PARENT,
            TableLayout.LayoutParams.WRAP_CONTENT
        )
        val lineView = View(activity)
        lineView.setLayoutParams(ActionBar.LayoutParams(5, ActionBar.LayoutParams.MATCH_PARENT))

        val leftMarginl = 0
        val topMarginl = 20
        val rightMarginl = 0
        val bottomMarginl = 10
        lineView.setBackgroundColor(Color.WHITE)

        tableRowParamsline.setMargins(leftMarginl, topMarginl, rightMarginl, bottomMarginl)
        tbrowLine.setLayoutParams(tableRowParamsline)
        tbrowLine.addView(lineView)
        stk.addView(tbrowLine)
        for (i in userScores.indices) {

            var item = userScores[i]
//        for (i in userScores.size) {
            roundNo = item.roundNo;

            println("roundNo:: " + roundNo)

            /* membe1TotalScore= membe1TotalScore+item.member1.toInt();
            membe2TotalScore= membe2TotalScore+item.member2.toInt();
            membe3TotalScore= membe3TotalScore+item.member3.toInt();
            membe4TotalScore= membe4TotalScore+item.member4.toInt();
            membe5TotalScore= membe5TotalScore+item.member5.toInt();
            membe6TotalScore= membe6TotalScore+item.member6.toInt();
            membe7TotalScore= membe7TotalScore+item.member7.toInt();
            membe8TotalScore= membe8TotalScore+item.member8.toInt();*/
            val tbrow = TableRow(activity)
            val tableRowParams = TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT
            )

            val leftMargin = 0
            val topMargin = 2
            val rightMargin = 0
            val bottomMargin = 2

            tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin)
            tbrow.setLayoutParams(tableRowParams)

            var roundNo = i + 1;
            val t1v = TextView(activity)
            t1v.text = "" + roundNo + spaceVal()
            t1v.setTextColor(Color.WHITE)
            t1v.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);

            t1v.gravity = Gravity.CENTER
            tbrow.addView(t1v)
            val t2v = TextView(activity)
            t2v.text = item.member1 + spaceVal()
            t2v.setTextColor(Color.WHITE)
            t2v.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);
            t2v.gravity = Gravity.CENTER
            if (user[0].member1 != null && !user[0].member1.equals("") && membe1TotalScore <= maxScore) {

                tbrow.addView(t2v)
            }
            val t3v = TextView(activity)
            t3v.text = item.member2 + spaceVal()
            t3v.setTextColor(Color.WHITE)
            t3v.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);

            t3v.gravity = Gravity.CENTER
            if (user[0].member2 != null && !user[0].member2.equals("") && membe2TotalScore <= maxScore) {

                tbrow.addView(t3v)
            }
            val t4v = TextView(activity)
            t4v.text = item.member3 + spaceVal()
            t4v.setTextColor(Color.WHITE)
            t4v.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);

            t4v.gravity = Gravity.CENTER
            if (user[0].member3 != null && !user[0].member3.equals("") && membe3TotalScore <= maxScore) {

                tbrow.addView(t4v)
            }
            val t5v = TextView(activity)
            t5v.text = item.member4 + spaceVal()
            t5v.setTextColor(Color.WHITE)
            t5v.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);

            t5v.gravity = Gravity.CENTER
            if (user[0].member4 != null && !user[0].member4.equals("") && membe4TotalScore <= maxScore) {

                tbrow.addView(t5v)
            }

            val t6v = TextView(activity)
            t6v.text = item.member5 + spaceVal()
            t6v.setTextColor(Color.WHITE)
            t6v.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);

            t6v.gravity = Gravity.CENTER
            if (user[0].member5 != null && !user[0].member5.equals("") && membe5TotalScore <= maxScore) {

                tbrow.addView(t6v)
            }

            val t7v = TextView(activity)
            t7v.text = item.member6 + spaceVal()
            t7v.setTextColor(Color.WHITE)
            t7v.gravity = Gravity.CENTER
            t7v.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);
            if (user[0].member6 != null && !user[0].member6.equals("") && membe6TotalScore <= maxScore) {

                tbrow.addView(t7v)
            }
            val t8v = TextView(activity)
            t8v.text = item.member7 + spaceVal()
            t8v.setTextColor(Color.WHITE)
            t8v.gravity = Gravity.CENTER
            t8v.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);
            if (user[0].member7 != null && !user[0].member7.equals("") && membe7TotalScore <= maxScore) {

                tbrow.addView(t8v)
            }
            val t9v = TextView(activity)
            t9v.text = item.member8 + spaceVal()
            t9v.setTextColor(Color.WHITE)
            t9v.gravity = Gravity.CENTER
            t9v.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);
            if (user[0].member8 != null && !user[0].member8.equals("") && membe8TotalScore <= maxScore) {

                tbrow.addView(t9v)
            }

            stk.addView(tbrow)
        }
        val tbrow1 = TableRow(activity)
        val tableRowParams = TableLayout.LayoutParams(
            TableLayout.LayoutParams.FILL_PARENT,
            TableLayout.LayoutParams.WRAP_CONTENT
        )

        val leftMargin = 0
        val topMargin = 70
        val rightMargin = 0
        val bottomMargin = 10

        tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin)
        if (user[0].member1 != null && !user[0].member1.equals("") && membe1TotalScore <= maxScore) {

            list.add(membe1TotalScore)
        }
        if (user[0].member2 != null && !user[0].member2.equals("") && membe2TotalScore <= maxScore) {

            list.add(membe2TotalScore)
        }
        if (user[0].member3 != null && !user[0].member3.equals("") && membe3TotalScore <= maxScore) {

            list.add(membe3TotalScore)
        }
        if (user[0].member4 != null && !user[0].member4.equals("") && membe4TotalScore <= maxScore) {

            list.add(membe4TotalScore)
        }
        if (user[0].member5 != null && !user[0].member5.equals("") && membe5TotalScore <= maxScore) {

            list.add(membe5TotalScore)
        }
        if (user[0].member6 != null && !user[0].member6.equals("") && membe6TotalScore <= maxScore) {

            list.add(membe6TotalScore)
        }
        if (user[0].member7 != null && !user[0].member7.equals("") && membe7TotalScore <= maxScore) {

            list.add(membe7TotalScore)
        }
        if (user[0].member8 != null && !user[0].member8.equals("") && membe8TotalScore <= maxScore) {

            list.add(membe8TotalScore)
        }

        if (list != null && list.size > 0) {
            var largestNum = largest()
            var leastVal = getMin()

            if (largestNum != null && !largestNum.equals("")) {
                if (largestNum <= 150)
                    largest_textview.text = "Highest : " + largestNum
            }
            if (leastVal != null && !leastVal.equals("")) {

                least_textview.text = "Least : " + leastVal
            }
        }

        tbrow1.setLayoutParams(tableRowParams)
        val addtv0 = TextView(activity)
        addtv0.text = "  Total"+spaceVal()
        addtv0.setTextSize(TypedValue.COMPLEX_UNIT_SP,16f);
        addtv0.setTextColor(Color.WHITE)
        tbrow1.addView(addtv0)

        val addtv1 = TextView(activity)
        addtv1.text = ""+membe1TotalScore+spaceVal()
        addtv1.setTextSize(TypedValue.COMPLEX_UNIT_SP,18f);
        addtv1.setTextColor(Color.WHITE)
        if(user[0].member1!=null && !user[0].member1.equals("")&& membe1TotalScore<=maxScore) {

            tbrow1.addView(addtv1)
        }
        val addtv2 = TextView(activity)
        addtv2.text = ""+membe2TotalScore+spaceVal()
        addtv2.setTextColor(Color.WHITE)
        addtv2.setTextSize(TypedValue.COMPLEX_UNIT_SP,18f);
        if(user[0].member2!=null && !user[0].member2.equals("")&& membe2TotalScore<=maxScore) {

            tbrow1.addView(addtv2)
        }
        val addtv3 = TextView(activity)
        addtv3.text = ""+membe3TotalScore+spaceVal()
        addtv3.setTextColor(Color.WHITE)
        addtv3.setTextSize(TypedValue.COMPLEX_UNIT_SP,18f);
        if(user[0].member3!=null && !user[0].member3.equals("")&& membe3TotalScore<=maxScore) {

            tbrow1.addView(addtv3)
        }
        val addtv4 = TextView(activity)
        addtv4.text = ""+membe4TotalScore+spaceVal()
        addtv4.setTextColor(Color.WHITE)
        addtv4.setTextSize(TypedValue.COMPLEX_UNIT_SP,18f);
        if(user[0].member4!=null && !user[0].member4.equals("")&& membe4TotalScore<=maxScore) {

            tbrow1.addView(addtv4)
        }
        val addtv5 = TextView(activity)
        addtv5.text = ""+membe5TotalScore+spaceVal()
        addtv5.setTextColor(Color.WHITE)
        addtv5.setTextSize(TypedValue.COMPLEX_UNIT_SP,18f);
        if(user[0].member5!=null && !user[0].member5.equals("")&& membe5TotalScore<=maxScore) {

            tbrow1.addView(addtv5)
        }
        val addtv6 = TextView(activity)
        addtv6.text = ""+membe6TotalScore+spaceVal()
        addtv6.setTextColor(Color.WHITE)
        addtv6.setTextSize(TypedValue.COMPLEX_UNIT_SP,18f);
        if(user[0].member6!=null && !user[0].member6.equals("")&& membe6TotalScore<=maxScore) {

            tbrow1.addView(addtv6)
        }
        val addtv7 = TextView(activity)
        addtv7.text = ""+membe7TotalScore+spaceVal()
        addtv7.setTextColor(Color.WHITE)
        addtv7.setTextSize(TypedValue.COMPLEX_UNIT_SP,18f);
        if(user[0].member7!=null && !user[0].member7.equals("")&& membe7TotalScore<=maxScore) {

            tbrow1.addView(addtv7)
        }
        val addtv8 = TextView(activity)
        addtv8.text = ""+membe8TotalScore+spaceVal()
        addtv8.setTextColor(Color.WHITE)
        addtv8.setTextSize(TypedValue.COMPLEX_UNIT_SP,18f);
        if(user[0].member8!=null && !user[0].member8.equals("")&& membe8TotalScore<=maxScore) {

            tbrow1.addView(addtv8)
        }
        stk.addView(tbrow1)
    }
    /*fun maxScore(): Boolean{
        && maxScore<=150

    }*/
    fun indTotalScore(){
        for (i in userScores.indices) {

            var item = userScores[i]
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
    fun largest(): Int {
        var i: Int

        // Initialize maximum element
        var max = list[0]

        // Traverse array elements from second and
        // compare every element with current max
        i = 1
        while (i < list.size) {
            if (list[i] > max)
                max = list[i]
            i++
        }

        return max
    }
    fun getMin(): Int {
        var minValue = list[0]
        for (i in 1 until list.size) {
            if (list[i] < minValue) {
                minValue = list[i]
            }
        }
        return minValue
    }
}
