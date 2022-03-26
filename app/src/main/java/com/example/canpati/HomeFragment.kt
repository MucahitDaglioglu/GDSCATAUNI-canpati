package com.example.canpati

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.canpati.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var navController: NavController

    //private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""

    private lateinit var recyclerView: RecyclerView
    private lateinit var restaurantArrayList: ArrayList<Restaurant>
    private lateinit var myAdapter: MyAdapter
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        restaurantArrayList = arrayListOf()

        myAdapter = MyAdapter(restaurantArrayList)

        binding.recyclerView.adapter = myAdapter

        eventChangeListener()*/

        /*var firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        //handle click, logout
        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }*/

        loadAllData()

    }

    private fun eventChangeListener() {

        db = FirebaseFirestore.getInstance()
        db.collection("Restaurants").orderBy("name",Query.Direction.ASCENDING).
                addSnapshotListener(object : EventListener<QuerySnapshot>{
                    override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?
                    ) {

                        if (error != null){
                            Log.e("Firestore Error", error.message.toString())
                            return
                        }

                        for (dc : DocumentChange in value?.documentChanges!!){
                            if (dc.type == DocumentChange.Type.ADDED){
                                restaurantArrayList.add(dc.document.toObject(Restaurant::class.java))

                            }
                        }

                        myAdapter.notifyDataSetChanged()
                    }

                })


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        restaurantArrayList = arrayListOf()

        myAdapter = MyAdapter(restaurantArrayList)

        binding.recyclerView.adapter = myAdapter

        eventChangeListener()*/


        ///////////////////

        var firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        //handle click, logout
        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }

        //go to add fragment
        /*binding.addRestaurantBtn.setOnClickListener {
            //navController.navigate(R.id.action_homeFragment_to_addRestaurantFragment)
            //Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_addRestaurantFragment)
            NavHostFragment.findNavController(this).navigate(R.id.action_homeFragment_to_addRestaurantFragment)
        }*/

    }

    private fun loadAllData() {

        val taskList = ArrayList<Restaurant>()
        db = FirebaseFirestore.getInstance()
        var ref = db.collection("restaurant")
        ref.get().addOnSuccessListener {
            for (doc in it){
                val taskModel = doc.toObject(Restaurant::class.java)
                taskList.add(taskModel)
            }

            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                adapter = MyAdapter(taskList)
            }
        }
    }

    override fun onStart() {
        super.onStart()

        binding.addRestaurantBtn.setOnClickListener {
            //navController.navigate(R.id.action_homeFragment_to_addRestaurantFragment)
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_addRestaurantFragment)
            binding.frameLayout.removeAllViews()
        }

    }

    private fun checkUser() {
        //check user is logged in or not
        var firebaseAuth = FirebaseAuth.getInstance()
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null){
            //user not null, user is logged in, get user info
            val email = firebaseUser!!.email
            //set to text view
            binding.emailTv.text = email

            //listRestaurant()
        }
        else{
            //user is null, user is not loggedin, goto login activity
            startActivity(Intent(activity, LoginActivity::class.java))
            //finish()
        }
    }

    /*private fun listRestaurant() {
        TODO("Not yet implemented")
    }*/

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

}