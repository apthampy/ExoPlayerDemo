package demo.test.exoplayerdemp.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import demo.test.exoplayerdemp.base.BaseViewModel
import demo.test.exoplayerdemp.ui.main.model.CountData
import demo.test.exoplayerdemp.utility.Constant

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
    private  var analytics: FirebaseAnalytics = Firebase.analytics

    fun incrementPausedCount() {
        countData.value = countData.value?.apply {
            pauseCount++
            analytics.setDefaultEventParameters(Bundle().apply {
                this.putInt(Constant.AnalyticsKEy.pauseCount, pauseCount)
            })
        }
    }

    fun progressbarVisibilitySet(visibility: Int) {
        progressBarVisibility.value = visibility
    }
}