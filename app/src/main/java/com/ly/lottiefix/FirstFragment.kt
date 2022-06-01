package com.ly.lottiefix

import android.animation.Animator
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ly.lottiefix.databinding.FragmentFirstBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lottie.playAnimation()
        lifecycleScope.launch {
            delay(500L)
            binding.lottie.cancelAnimation()
        }
        binding.lottie.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {
                println("lottie onAnimationStart")
            }

            override fun onAnimationEnd(p0: Animator?) {
                println("lottie onAnimationEnd")
            }

            override fun onAnimationCancel(p0: Animator?) {
                println("lottie onAnimationCancel")
            }

            override fun onAnimationRepeat(p0: Animator?) {
                println("lottie onAnimationRepeat")
            }

        })
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    private fun println(any: Any) {
        Log.d("LottieFixPlugin", "$any")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}