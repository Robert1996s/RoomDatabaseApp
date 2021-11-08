package com.example.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.Model.User
import com.example.viewmodel.UserViewModel
import com.example.myroomdatabaseexample.R
import com.example.myroomdatabaseexample.databinding.FragmentAddBinding
import com.example.myroomdatabaseexample.databinding.FragmentListBinding

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding

    private lateinit var mUserViewModel: UserViewModel

    var  _binding: FragmentListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding = FragmentAddBinding.inflate(layoutInflater)

        val view = binding.root


        binding.buttonAdd.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val firstName = binding.firstNameEt.text.toString()
        val lastName = binding.lastNameEt.text.toString()
        val age = binding.ageEt.text

        if (inputCheck(firstName, lastName, age)){
            //Create user  object
            val user = User(0, firstName, lastName, Integer.parseInt(age.toString()))

            //Add data to database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Sucessfully added!", Toast.LENGTH_LONG).show()

            //navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Fill all the fields!", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable):Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}