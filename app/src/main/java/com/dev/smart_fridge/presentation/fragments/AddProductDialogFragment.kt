package com.dev.smart_fridge.presentation.fragments

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.dev.smart_fridge.R
import java.util.Calendar

class AddProductDialogFragment : DialogFragment() {

    interface AddProductDialogListener {
        fun onDialogPositiveClick(productName: String, expiryDate: String)
    }

    var listener: AddProductDialogListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_add_product, null)

            val editTextProductName = view.findViewById<EditText>(R.id.editTextProductName)
            val editTextProductDate = view.findViewById<EditText>(R.id.editTextProductDate)

            editTextProductDate.setOnClickListener {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog =
                    DatePickerDialog(requireContext(), { _, year, monthOfYear, dayOfMonth ->
                        val dateString = "${dayOfMonth}/${monthOfYear + 1}/$year"
                        editTextProductDate.setText(dateString)
                    }, year, month, day)

                datePickerDialog.show()
            }


            builder.setView(view)
                .setPositiveButton("Add") { dialog, id ->
                    listener?.onDialogPositiveClick(
                        editTextProductName.text.toString(),
                        editTextProductDate.text.toString()
                    )
                }
                .setNegativeButton("Cancel") { dialog, id ->
                    getDialog()?.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
