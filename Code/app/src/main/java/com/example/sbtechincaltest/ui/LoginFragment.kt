package com.example.sbtechincaltest.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sbtechincaltest.R
import com.example.sbtechincaltest.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            validateFields()
        }
        binding.emailInput.addTextChangedListener(textWatcher)
        binding.passwordInput.addTextChangedListener(textWatcher)
    }

    // text changed listener for email and password input fields
    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            setBackgroundOfInputFields()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // do nothing
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // do nothing
        }
    }

    // validates user's credentials when login button is clicked checking they're not empty
    private fun validateFields() {
        val username = binding.emailInput.text.toString()
        val password = binding.passwordInput.text.toString()

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(
                requireContext(), "Please enter your credentials", Toast.LENGTH_SHORT
            )
                .show()
        } else {
            // navigates to the photos fragment once validated
            findNavController().navigate(R.id.action_loginFragment_to_photosFragment)
        }
    }

    // sets background of input fields based on their contents
    private fun setBackgroundOfInputFields() {
        val username = binding.emailInput.text.toString()
        val password = binding.passwordInput.text.toString()

        val emailInputBackground =
            if (username.isEmpty()) R.drawable.login_shape else R.drawable.textbox_input
        val passwordInputBackground =
            if (password.isEmpty()) R.drawable.login_shape else R.drawable.textbox_input

        binding.emailInput.setBackgroundResource(emailInputBackground)
        binding.passwordInput.setBackgroundResource(passwordInputBackground)
    }


}
