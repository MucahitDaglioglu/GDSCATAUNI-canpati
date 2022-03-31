package com.example.canpati

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.canpati.databinding.FragmentAddRestaurantBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*


class AddRestaurantFragment : Fragment() {

    private var _binding: FragmentAddRestaurantBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddRestaurantBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addBtn.setOnClickListener {
            addRestaurant()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private var name = ""
    private var address = ""
    private var phone = ""
    private var city = ""
    private lateinit var db: FirebaseFirestore

    private fun addRestaurant(){

        name = binding.restaurantNameEt.text.toString()
        address = binding.restaurantAddressEt.text.toString()
        phone = binding.restaurantPhoneEt.text.toString()
        city = binding.restaurantCityEt.text.toString()

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(address) ||
            TextUtils.isEmpty(phone) || TextUtils.isEmpty(city)) {
                Toast.makeText(requireContext(),"Please enter information",Toast.LENGTH_SHORT).show()
                Log.d("tg","1${TextUtils.isEmpty(name)}")
                Log.d("tg","2${TextUtils.isEmpty(address)}")
                Log.d("tg","3${TextUtils.isEmpty(phone)}")
                Log.d("tg","4${TextUtils.isEmpty(city)}")
        }
        else {
            Toast.makeText(requireContext(),"snc: ${Patterns.PHONE.matcher(phone).matches()}",Toast.LENGTH_SHORT).show()

            db = FirebaseFirestore.getInstance()
            val data = Restaurant(name, address, phone, city)
            /*val restaurant: MutableMap<String, Any> = HashMap()
            restaurant["Name"] = name
            restaurant["Address"] = address
            restaurant["Phone"] = phone
            restaurant["City"] = city*/

            db.collection("restaurant")
                .add(data)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "Successful", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
                }

        }


    }

}