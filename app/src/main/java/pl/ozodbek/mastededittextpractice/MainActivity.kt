package pl.ozodbek.mastededittextpractice

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import pl.ozodbek.mastededittextpractice.databinding.ActivityMainBinding
import pl.ozodbek.mastededittextpractice.util.Constants.Companion.MASK_FOR_CARD_NUMBERS_INPUT
import pl.ozodbek.mastededittextpractice.util.Constants.Companion.MASK_FOR_DATE_NUMBERS_INPUT
import pl.ozodbek.mastededittextpractice.util.Constants.Companion.MASK_FOR_PHONE_NUMBER_INPUT
import pl.ozodbek.mastededittextpractice.util.gone
import pl.ozodbek.mastededittextpractice.util.onClick
import pl.ozodbek.mastededittextpractice.util.setMask
import pl.ozodbek.mastededittextpractice.util.show
import pl.ozodbek.mastededittextpractice.util.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private var unMuskedValueOfPhoneNumber: String? = null
    private var unMuskedValueOfCardNumber: String? = null
    private var unMuskedValueOfDateNumber: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        setUpActionBar()
        setupMaskedEditText()
        setupClickListeners()
    }

    private fun setupMaskedEditText() {
        binding.phoneNumberEdittext.setMask(MASK_FOR_PHONE_NUMBER_INPUT) {
            unMuskedValueOfPhoneNumber = it?.trim() ?: ""
        }

        binding.bankCardEdittext.setMask(MASK_FOR_CARD_NUMBERS_INPUT) {
            unMuskedValueOfCardNumber = it?.trim() ?: ""
        }

        binding.dateEdittext.setMask(MASK_FOR_DATE_NUMBERS_INPUT) {
            unMuskedValueOfDateNumber = it?.trim() ?: ""
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupClickListeners() {
        binding.showButton.onClick {
            val phoneNumberBefore = unMuskedValueOfPhoneNumber.toString()
            val cardNumberBefore = unMuskedValueOfCardNumber.toString()
            val dateNumberBefore = unMuskedValueOfDateNumber.toString()

            if (areFieldsValid(phoneNumberBefore, cardNumberBefore, dateNumberBefore)) {
                hideInputLayouts()
                showTextViews()
                setTextViewsText()
            }
        }

        binding.clearButton.onClick {
            clearTextViews()
            clearEditTexts()
            showInputLayouts()
        }
    }

    private fun hideInputLayouts() {
        binding.phoneNumberInputLayout.gone()
        binding.bankCardInputLayout.gone()
        binding.dateInputLayout.gone()
    }

    private fun showTextViews() {
        binding.phoneNumberTv.show()
        binding.bankCardTv.show()
        binding.dateTv.show()
    }

    @SuppressLint("SetTextI18n")
    private fun setTextViewsText() {
        binding.phoneNumberTv.text = "+998$unMuskedValueOfPhoneNumber"
        binding.bankCardTv.text = unMuskedValueOfCardNumber
        binding.dateTv.text = unMuskedValueOfDateNumber
    }

    private fun clearTextViews() {
        binding.phoneNumberTv.text = null
        binding.bankCardTv.text = null
        binding.dateTv.text = null
    }

    private fun clearEditTexts() {
        binding.phoneNumberEdittext.text = null
        binding.bankCardEdittext.text = null
        binding.dateEdittext.text = null
    }

    private fun showInputLayouts() {
        binding.phoneNumberInputLayout.show()
        binding.bankCardInputLayout.show()
        binding.dateInputLayout.show()
    }

    private fun areFieldsValid(
        phoneNumberBefore: String,
        cardNumberBefore: String,
        dateNumberBefore: String,
    ): Boolean {
        if (phoneNumberBefore.isBlank()) {
            showError(binding.phoneNumberInputLayout, "Please enter PhoneNumber !")
            return false
        }
        releaseView(binding.phoneNumberInputLayout)

        if (cardNumberBefore.isBlank()) {
            showError(binding.bankCardInputLayout, "Please enter Card Number !")
            return false
        }
        releaseView(binding.bankCardInputLayout)


        if (dateNumberBefore.isBlank()) {
            showError(binding.dateInputLayout, "Please enter Date !")
            return false
        }
        releaseView(binding.dateInputLayout)

        return true
    }

    private fun releaseView(textInputLayout: TextInputLayout) {
        textInputLayout.error = null
        textInputLayout.clearFocus()
    }

    private fun showError(inputLayout: TextInputLayout, errorMessage: String) {
        inputLayout.requestFocus()
        inputLayout.error = errorMessage
    }

    private fun setUpActionBar() {
        setSupportActionBar(binding.toolbar)
        title = "MaskedEditText"
    }

}
