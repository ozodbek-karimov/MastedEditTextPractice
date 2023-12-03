package pl.ozodbek.mastededittextpractice.util

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.google.android.material.textfield.TextInputEditText
import com.redmadrobot.inputmask.MaskedTextChangedListener
import pl.ozodbek.mastededittextpractice.R

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T) =
    lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }

fun TextInputEditText.setMask(mask: String, onUnmaskedString: (String?) -> Unit) {
    val listener = MaskedTextChangedListener(
        mask,
        false,
        this@setMask,
        null,
        object : MaskedTextChangedListener.ValueListener {
            override fun onTextChanged(
                maskFilled: Boolean,
                extractedValue: String,
                formattedValue: String,
                tailPlaceholder: String,
            ) {
                onUnmaskedString(extractedValue)
            }
        }
    )

    this@setMask.addTextChangedListener(listener)
    this@setMask.setHintTextColor(getColor(R.color.lightMediumGray))
    this@setMask.setText("")
    listener.autoskip = true
}

fun View.getColor(color: Int): Int {
    return ContextCompat.getColor(this.context, color)
}
