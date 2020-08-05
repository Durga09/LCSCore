package com.durga.lc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.appcompat.app.ActionBar
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.R.attr.name
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.R.attr.fragment
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.durga.lc.fragments.AddMembersFragment
import com.durga.lc.fragments.EnterScoreFragment
import com.durga.lc.fragments.ViewScoreFragment


class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navigation = findViewById(R.id.navigation) as BottomNavigationView
        loadFragment(AddMembersFragment());
        navigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.getItemId()) {
                R.id.add -> loadFragment(AddMembersFragment());

                R.id.enter -> loadFragment(EnterScoreFragment())

                R.id.view -> loadFragment(ViewScoreFragment())
                else-> super.onOptionsItemSelected(menuItem)

            }


        }
    }
    private fun loadFragment(fragment: Fragment?): Boolean {
        //switching fragment
        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_container, fragment)
                .commit()
            return true
        }
        return false
    }

}
