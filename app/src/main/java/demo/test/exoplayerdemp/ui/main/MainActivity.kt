package demo.test.exoplayerdemp.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.StyledPlayerView
import dagger.hilt.android.AndroidEntryPoint
import demo.test.exoplayerdemp.R
import demo.test.exoplayerdemp.base.BaseActivity
import demo.test.exoplayerdemp.databinding.ActivityMainBinding
import demo.test.exoplayerdemp.utility.Constant.PLAYER_URL

@AndroidEntryPoint
class MainActivity : BaseActivity(), Player.Listener {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var player: ExoPlayer
    private lateinit var playerView: StyledPlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initPlayer()
    }

    private fun initPlayer() {
        setupPlayer()
        addFile()
    }

    private fun setupPlayer() {
        player = ExoPlayer.Builder(this).build()
        playerView = findViewById(R.id.video_view)
        playerView.player = player
        player.addListener(this)
    }

    override fun onPlaybackStateChanged(state: Int) {
        when (state) {
            Player.STATE_BUFFERING -> {
                viewModel.progressbarVisibilitySet(View.VISIBLE)
            }
            Player.STATE_READY -> {
                viewModel.progressbarVisibilitySet(View.GONE)
            }
            Player.STATE_ENDED -> {
            }
            Player.STATE_IDLE -> {
            }
        }
    }

    override fun onPlayWhenReadyChanged(playWhenReady: Boolean, reason: Int) {
        super.onPlayWhenReadyChanged(playWhenReady, reason)
        if (playWhenReady) {
            //resume
        } else {
            //puased
            viewModel.incrementPausedCount()
        }
    }

    private fun addFile() {
        val mediaItem = MediaItem.fromUri(PLAYER_URL)
        player.setMediaItem(mediaItem)
        player.prepare()
    }

    override fun onStop() {
        super.onStop()
        player.release()
    }

    override fun onResume() {
        super.onResume()
        initPlayer()
    }
}