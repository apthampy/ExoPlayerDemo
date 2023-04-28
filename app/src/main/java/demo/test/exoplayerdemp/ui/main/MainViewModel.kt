package demo.test.exoplayerdemp.ui.main

import android.view.View
import androidx.lifecycle.MutableLiveData
import demo.test.exoplayerdemp.base.BaseViewModel
import demo.test.exoplayerdemp.ui.main.model.CountData

class MainViewModel() : BaseViewModel() {
    val countData: MutableLiveData<CountData> by lazy {
        MutableLiveData<CountData>().also {
            it.value = CountData()
        }
    }
    val progressBarVisibility: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().also {
            it.value = View.VISIBLE
        }
    }

    fun incrementPausedCount() {
        countData.value = countData.value?.apply {
            pauseCount++
        }
    }

    fun progressbarVisibilitySet(visibility: Int) {
        progressBarVisibility.value = visibility
    }
}