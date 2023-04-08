package com.example.alias.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.alias.R

interface NavigationStrategy {

    fun navigate(supportFragmentManager: FragmentManager, containerId: Int)

    abstract class Abstract(protected val fragment: Fragment) : NavigationStrategy {
        override fun navigate(supportFragmentManager: FragmentManager, containerId: Int) {
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_right_to_left,
                    R.anim.exit_right_to_left,
                    R.anim.enter_left_to_right,
                    R.anim.exit_left_to_right
                )
                .executeTransaction(containerId)
                .commit()
        }

        protected abstract fun FragmentTransaction.executeTransaction(containerId: Int): FragmentTransaction
    }

    class Replace(fragment: Fragment) : Abstract(fragment) {
        override fun FragmentTransaction.executeTransaction(containerId: Int): FragmentTransaction =
            replace(containerId, fragment)
    }

    class Add(fragment: Fragment) : Abstract(fragment) {
        override fun FragmentTransaction.executeTransaction(containerId: Int): FragmentTransaction =
            addToBackStack(fragment.javaClass.simpleName)
                .replace(containerId, fragment)
    }
}