package com.example.sumtest

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.test.espresso.IdlingResource
import com.example.sumtest.databinding.ActivityMainBinding
import com.example.sumtest.utils.BasicIdlingResource
import kotlinx.coroutines.launch

class SumActivity : AppCompatActivity() {
    // The Idling Resource which will be null in production.
    private var mIdlingResource: BasicIdlingResource? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: SumViewModel by viewModels()

        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val textChangedListener : TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.onTextChanged()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    binding.loading.visibility = if (it.loading) VISIBLE else GONE
                    binding.result.visibility = if (it.result != null) VISIBLE else GONE
                    binding.result.text = it.result
                    binding.error.visibility = if (it.error != null) VISIBLE else GONE
                    binding.error.text = it.error
                    if (it.result != null) {
                        binding.firstNumber.removeTextChangedListener(textChangedListener)
                        binding.firstNumber.text = null
                        binding.firstNumber.addTextChangedListener(textChangedListener)

                        binding.secondNumber.removeTextChangedListener(textChangedListener)
                        binding.secondNumber.text = null
                        binding.secondNumber.addTextChangedListener(textChangedListener)
                        mIdlingResource?.setIdleState(true)
                    }
                    if (it.error != null) {
                        mIdlingResource?.setIdleState(true)
                    }
                }
            }
        }

        binding.cta.setOnClickListener {
            viewModel.onAddClicked(
                binding.firstNumber.text.toString(),
                binding.secondNumber.text.toString()
            )
            (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(currentFocus?.windowToken, HIDE_NOT_ALWAYS)
            mIdlingResource?.setIdleState(false)
        }

        binding.firstNumber.addTextChangedListener(textChangedListener)
        binding.secondNumber.addTextChangedListener(textChangedListener)
    }

    /**
     * Only called from test, creates and returns a new [BasicIdlingResource].
     */
    @VisibleForTesting
    fun getIdlingResource(): IdlingResource? {
        if (mIdlingResource == null) {
            mIdlingResource = BasicIdlingResource()
        }
        return mIdlingResource
    }
}