package com.example.fragments.update

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.Model.User
import com.example.myroomdatabaseexample.R
import com.example.myroomdatabaseexample.databinding.FragmentAddBinding
import com.example.myroomdatabaseexample.databinding.FragmentUpdateBinding
import com.example.viewmodel.UserViewModel


class UpdateFragment : Fragment() {


    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var binding: FragmentUpdateBinding
    private lateinit var mUserViewModel: UserViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


        binding = FragmentUpdateBinding.inflate(layoutInflater)

        val view = binding.root


        binding.updateFirstNameEt.setText(args.currentUser.firstName)
        binding.UpdateLastNameEt.setText(args.currentUser.lastName)
        binding.updateAgeEt.setText(args.currentUser.age.toString())


        binding.updateButton.setOnClickListener {
            updateItem()

        }


        return view

    }

    private fun updateItem() {
        val firstName = binding.updateFirstNameEt.text.toString()
        val lastName = binding.UpdateLastNameEt.text.toString()
        val age = Integer.parseInt(binding.updateAgeEt.text.toString())

        if (inputCheck(firstName, lastName, binding.updateAgeEt.text)) {
            //create user object
            val updatedUser = User(args.currentUser.id, firstName, lastName, age)
            //update current user
            mUserViewModel.updateUser(updatedUser)
            Toast.makeText(requireContext(), "Updated Sucessfully!", Toast.LENGTH_LONG).show()
            //Navigate back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Fill all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable):Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

}