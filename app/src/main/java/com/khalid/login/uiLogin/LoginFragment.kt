package com.khalid.login.uiLogin

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isEmpty
import androidx.core.view.isNotEmpty
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import com.khalid.login.R
import com.khalid.login.databinding.FragmentLoginBinding
import java.util.regex.Pattern


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding

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
            return false
        } else if (password.isBlank()) {
            binding?.passwordTextField?.error = "Password required"
            return false
        }
        return true
    }
}


