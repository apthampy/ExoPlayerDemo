package demo.test.exoplayerdemp.utility

import demo.test.exoplayerdemp.base.BaseActivity
import demo.test.exoplayerdemp.base.BaseFragment

object FragmentHelper {
    fun replaceFragment(
        activity: BaseActivity,
        containerId: Int,
        fragment: BaseFragment,
        fragmentTag: String? = fragment.tag,
        isAddBackStack: Boolean = false
    ) {
        var fragmentTransaction = activity.supportFragmentManager.beginTransaction()
            .replace(containerId, fragment, fragmentTag)
        if (isAddBackStack)
            fragmentTransaction = fragmentTransaction.addToBackStack(fragmentTag)
        fragmentTransaction.commitNow()
    }

    fun addFragment(
        activity: BaseActivity,
        containerId: Int,
        fragment: BaseFragment,
        fragmentTag: String? = fragment.tag,
        isAddBackStack: Boolean = false
    ) {
        var fragmentTransaction = activity.supportFragmentManager.beginTransaction()
            .add(containerId, fragment, fragmentTag)
        if (isAddBackStack)
            fragmentTransaction = fragmentTransaction.addToBackStack(fragmentTag)
        fragmentTransaction.commitNow()
    }
}