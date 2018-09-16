package com.ablanco.imageprovider

import android.app.Activity
import android.app.Fragment
import android.content.Intent

/**
 * Created by Álvaro Blanco Cabrero on 16/09/2018.
 * ImageProvider.
 *
 * Handler class that uses a Fragment to start intents for result and deliver the result back in
 * a callback. Used to abstract caller from manage onActivityResult calls
 */
internal class RequestHandler {

    /**
     * Start the given intent for result. Result data will be delivered in the callback.
     * Note: intent MUST set an Activity as a component
     */
    fun startForResult(
        activity: Activity,
        intent: Intent,
        callback: (resultCode: Int, data: Intent?) -> Unit
    ) {
        getRequestFragment(activity)?.startForResult(intent, callback)
                ?: callback(Activity.RESULT_CANCELED, null)
    }

    private fun getRequestFragment(activity: Activity): RequestFragment? {
        var requestFragment = activity
            .fragmentManager
            .findFragmentByTag(REQUEST_FRAGMENT_TAG) as? RequestFragment?
        return if (requestFragment == null) {
            requestFragment = RequestFragment()
            activity.fragmentManager
                .beginTransaction()
                .add(requestFragment, REQUEST_FRAGMENT_TAG)
                .commitAllowingStateLoss()
            activity.fragmentManager.executePendingTransactions()
            requestFragment
        } else {
            requestFragment
        }
    }

    companion object {
        private const val REQUEST_FRAGMENT_TAG = "request_fragment"
    }

    class RequestFragment : Fragment() {

        private var callback: ((resultCode: Int, data: Intent?) -> Unit)? = null
        fun startForResult(intent: Intent, callback: (resultCode: Int, data: Intent?) -> Unit) {
            this.callback = callback
            startActivityForResult(intent, RC)
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            callback?.invoke(resultCode, data)
            callback = null
        }

        companion object {
            private const val RC = 45909
        }
    }
}