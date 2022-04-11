package com.khalid.login.uiLogin

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.core.view.isNotEmpty
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.khalid.login.R
import com.khalid.login.databinding.FragmentLoginBinding
import java.util.regex.Pattern


class LoginFragment : Fragment() , State {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding
    private val viewModel:LoginViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.state=this
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.emailEditText?.doOnTextChanged { text, start, before, count ->
            if (binding?.emailTextField?.isNotEmpty() == true) {
                binding!!.emailTextField.isErrorEnabled = false

            }
        }
        binding?.passwordEditText?.doOnTextChanged { text, start, before, count ->
            if (binding!!.passwordTextField.isNotEmpty()) {
                binding!!.passwordTextField.isErrorEnabled = false
            }
        }


        binding?.button?.setOnClickListener {
            isEntryValid()
        }


    }

    private fun isEntryValid(): Boolean {
        return entryValid(
            binding?.emailEditText?.text.toString(),
            binding?.passwordEditText?.text.toString()
        )
    }

    private fun entryValid(
        email: String,
        password: String,
    ): Boolean {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding?.emailTextField?.error = "Email required"
            onFailure("email wrong")
            return false
        } else if (password.isBlank()) {
            binding?.passwordTextField?.error = "Password required"
            onFailure("password wrong")
            return false
        }else{
            viewModel.userLogin(email,password)
            return true

        }
    }

    override fun onStarted() {
        binding?.progressBar?.visibility=View.VISIBLE
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        onStarted()
        loginResponse.observe(this , Observer {
            binding?.progressBar?.visibility=View.INVISIBLE
            if (it=="Not Found"){
                Toast.makeText(context, "Email or password is incorrect", Toast.LENGTH_SHORT).show()

            }else {
                val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
                findNavController().navigate(action)
            }
        })
    }

    override fun onFailure(massage: String) {
        binding?.progressBar?.visibility=View.INVISIBLE
        Toast.makeText(context, massage, Toast.LENGTH_SHORT).show()

    }

}


