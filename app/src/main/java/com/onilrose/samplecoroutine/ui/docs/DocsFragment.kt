package com.onilrose.samplecoroutine.ui.docs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.onilrose.samplecoroutine.R
import kotlinx.android.synthetic.main.docs_fragment.*
import android.content.Intent
import android.R.attr.path
import android.content.Context
import kotlinx.coroutines.*
import java.io.*
import kotlin.coroutines.CoroutineContext
import android.text.method.ScrollingMovementMethod




class DocsFragment : Fragment() {

    companion object {
        fun newInstance() = DocsFragment()
    }

    private lateinit var viewModel: DocsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.docs_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DocsViewModel::class.java)

        viewModel.apply {
            mPath.observe(this@DocsFragment, Observer { text ->
                progress_docs.visibility = View.GONE
                textView.text = text
            })
        }

        viewModel.fetchDocs(activity)

        textView.movementMethod = ScrollingMovementMethod()

        button_save.setOnClickListener {
            progress_docs.visibility = View.VISIBLE
            runBlocking {
                viewModel.saveDocs(activity, multiAutoCompleteTextView.text.toString())
            }
        }


    }

}
