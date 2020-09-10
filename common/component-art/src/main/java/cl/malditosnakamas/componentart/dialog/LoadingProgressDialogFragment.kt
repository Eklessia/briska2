package cl.malditosnakamas.componentart.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import cl.malditosnakamas.componentart.R

class LoadingProgressDialogFragment(
    private val fragmentManagerDialog: FragmentManager
) : DialogFragment() {

    companion object {
        const val TAG = "ProgressDialog"
        const val MARGIN_DIALOG = 40
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.loading_progress_dialog, null)
            builder.setView(view)
            builder.create().apply {
                val back = ColorDrawable(Color.TRANSPARENT)
                val inset = InsetDrawable(back, MARGIN_DIALOG)
                window?.setBackgroundDrawable(inset)
            }
        } ?: throw IllegalStateException("Activity cannot be null $TAG")
    }

    fun show(){
        show(fragmentManagerDialog, TAG)
    }
}