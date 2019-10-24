package com.onilrose.samplecoroutine.ui.animation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.onilrose.samplecoroutine.R
import kotlinx.android.synthetic.main.content_animated.*
import kotlinx.android.synthetic.main.fragment_animated.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AnimatedFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AnimatedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AnimatedFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_animated, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val animationModel = ViewModelProviders.of(this).get(AnimationModel::class.java)
        animationModel.observe(this, animationView)

        addButton.setOnClickListener {
            animationModel.addAnimation()
        }

        removeButton.setOnClickListener {
            animationModel.clearAnimations()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment AnimatedFragment.
         */
        @JvmStatic
        fun newInstance() = AnimatedFragment()
    }
}
