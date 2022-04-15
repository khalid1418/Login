package com.khalid.login.uiLogin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import com.khalid.login.databinding.FragmentLoginBinding
import com.khalid.login.utlis.hide
import com.khalid.login.utlis.show
import com.khalid.login.utlis.ViewModelFactory


class LoginFragment : Fragment() , State {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding
    private val viewModel:LoginViewModel by activityViewModels{
        ViewModelFactory()
    }

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



        binding?.button?.setOnClickListener {
            isEntryValid()
        }


    }

    private fun isEntryValid(): Boolean {
        return viewModel.entryValid(
            binding?.emailEditText?.text.toString(),
            binding?.passwordEditText?.text.toString()
        )
    }



    override fun onStarted() {
        binding?.progressBar?.show()
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        onStarted()
        loginResponse.observe(this) {
            binding?.progressBar?.hide()
            if (it == "Not Found") {
                Toast.makeText(context, "Email or password is incorrect", Toast.LENGTH_SHORT).show()

            } else {
                val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
                findNavController().navigate(action)
            }
        }

    }

    override fun onFailure(massage: String) {
        binding?.progressBar?.hide()
        Toast.makeText(context, massage, Toast.LENGTH_SHORT).show()

    }

}


