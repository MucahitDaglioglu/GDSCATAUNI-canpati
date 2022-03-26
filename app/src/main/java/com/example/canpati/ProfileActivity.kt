package com.example.canpati

import android.annotation.SuppressLint
import android.app.Fragment
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.canpati.databinding.ActivityProfileBinding
import com.google.android.material.internal.ContextUtils.getActivity
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    //ActionBar
    //private lateinit var actionBar: ActionBar
    //ProgressDialog
    private lateinit var progressDialog: ProgressDialog
    //FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //configure action bar
        //actionBar = supportActionBar!!
        //actionBar.title = "Profile"

        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        //handle click, logout
        /*binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }*/
    }

    private fun checkUser() {
        //check user is logged in or not
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null){
            //user not null, user is logged in, get user info
            val email = firebaseUser!!.email
            //set to text view
            //binding.emailTv.text = email

            //binding.listRestaurantBtn.setOnClickListener {
                listRestaurant()
            //}

        }
        else{
            //user is null, user is not loggedin, goto login activity
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }

    private fun listRestaurant(){

        supportFragmentManager.commit {
            replace(R.id.fragment_container, HomeFragment())
        }


        /*binding.txtV.visibility = View.GONE
        binding.emailTv.visibility = View.GONE
        binding.logoutBtn.visibility = View.GONE
        binding.listRestaurantBtn.visibility = View.GONE*/
        //binding.fragmentContainer.visibility = View.VISIBLE
    }

}