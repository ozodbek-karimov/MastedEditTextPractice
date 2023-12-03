package pl.ozodbek.mastededittextpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import pl.ozodbek.mastededittextpractice.databinding.ActivityMainBinding
import pl.ozodbek.mastededittextpractice.util.Constants.Companion.MASK_FOR_CARD_NUMBERS_INPUT
import pl.ozodbek.mastededittextpractice.util.Constants.Companion.MASK_FOR_DATE_NUMBERS_INPUT
import pl.ozodbek.mastededittextpractice.util.Constants.Companion.MASK_FOR_PHONE_NUMBER_INPUT
import pl.ozodbek.mastededittextpractice.util.setMask
import pl.ozodbek.mastededittextpractice.util.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private var unMuskedValue: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupMaskedEditText(binding.phoneNumberEdittext, MASK_FOR_PHONE_NUMBER_INPUT)

        setupMaskedEditText(binding.bankCardEdittext, MASK_FOR_CARD_NUMBERS_INPUT)

        setupMaskedEditText(binding.dateEdittext, MASK_FOR_DATE_NUMBERS_INPUT)

    }

    private fun setupMaskedEditText(editText: TextInputEditText, mask:String) {
        editText.setMask(
            mask
        ) { unMuskedString ->
            unMuskedValue = unMuskedString?.trim() ?: ""
        }
    }
}