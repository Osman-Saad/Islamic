package com.pbws.islami.ui.screens.radio

import android.content.Intent
import android.util.Log
import androidx.annotation.OptIn
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.util.UnstableApi
import com.pbws.domain.entity.soundentity.quran.QuranFilesItem
import com.pbws.domain.entity.soundentity.radio.RadioSoundItem
import com.pbws.islami.R
import com.pbws.islami.ui.screens.commoncomposable.IslamicText
import com.pbws.islami.ui.screens.commoncomposable.SoundItem
import com.pbws.islami.ui.screens.commoncomposable.TabLayout
import com.pbws.islami.ui.screens.splash.suwarList
import com.pbws.islami.ui.theme.Gold
import kotlinx.coroutines.launch

@OptIn(UnstableApi::class)
@Preview(showSystemUi = true)
@Composable
fun RadioScreen(viewModel: RadioViewModel = hiltViewModel()) {
    var state = viewModel.state

    var isLoading by rememberSaveable {
        mutableStateOf(false)
    }
    var radioAudioList by rememberSaveable {
        mutableStateOf<List<RadioSoundItem>>(emptyList())
    }
    var quranAudioList by rememberSaveable {
        mutableStateOf<List<QuranFilesItem>>(emptyList())
    }

    LaunchedEffect(Unit) {
        viewModel.channel.send(RadioIntent.GetSounds)
    }
    LaunchedEffect(Unit) {
        state.collect { state ->
            when (state) {
                is RadioState.Error -> {
                    isLoading = false
                }

                is RadioState.Ideal -> {}
                is RadioState.Loading -> {
                    isLoading = true
                }

                is RadioState.RadioAndQuranSoundsRetrievedSuccess -> {
                    isLoading = false
                    radioAudioList = state.radioSounds
                    quranAudioList = state.quranSounds
                }
            }
        }
    }

    var tabIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    var isQuran by rememberSaveable {
        mutableStateOf(true)
    }

    var tabsList = listOf(
        stringResource(R.string.radio_tab_text),
        stringResource(R.string.quran_tab_text)
    )
    LaunchedEffect(tabIndex) {
        when (tabIndex) {
            0 -> {
                isQuran = false
            }

            1 -> {
                isQuran = true
            }
        }
    }
    var context = LocalContext.current
    val serviceIntent = Intent(context, AudioService::class.java)
    RadioScreenContent(
        isLoading = isLoading,
        radioList = radioAudioList,
        quranList = quranAudioList,
        isQuran = isQuran,
        tabList = tabsList,
        onTabClick = { tabIndex = it },
        onAudioOpenClick = {title, url, audioIsPlay ->
            Log.d("URL", url)
            serviceIntent.action = if (audioIsPlay) "play" else "stop"
            serviceIntent.putExtra("audioUrl", url)
            serviceIntent.putExtra("contentText", title)
            context.startService(serviceIntent)
        },
        onForwardClick = {
            serviceIntent.action = "seek_forward"
            context.startService(serviceIntent)
        },
        onBackwardClick = {
            serviceIntent.action = "seek_backward"
            context.startService(serviceIntent)
        }
    )
}

@Composable
fun RadioScreenContent(
    isLoading: Boolean,
    isQuran: Boolean,
    tabList: List<String>,
    quranList: List<QuranFilesItem>,
    radioList: List<RadioSoundItem>,
    onTabClick: (Int) -> Unit,
    onAudioOpenClick: (String,String, Boolean) -> Unit,
    onForwardClick: () -> Unit,
    onBackwardClick: () -> Unit
) {

    val quranAudioPlayStates = remember{ mutableStateListOf<Boolean>() }
    val radioAudioPlayStates = remember { mutableStateListOf<Boolean>() }
    val coroutineScope = rememberCoroutineScope()

    // Initialize the state list with false for each item in Quran or Radio
    if (isQuran) {
        if (quranAudioPlayStates.size != quranList.size) {
            if(quranIsOpen){
                quranAudioPlayStates.addAll(List(quranList.size) { index-> index== indexOfAudioIsOpen })
            }else{
                quranAudioPlayStates.addAll(List(quranList.size) { false })
            }
        }
    } else {
        if (radioAudioPlayStates.size != radioList.size) {
           if(!quranIsOpen){
               radioAudioPlayStates.addAll(List(radioList.size) { index-> index== indexOfAudioIsOpen })
           }else{
               radioAudioPlayStates.addAll(List(radioList.size) { false})
           }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.radio_background),
                contentScale = ContentScale.FillBounds
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(64.dp))
            IslamicText()
            TabLayout(tabs = tabList, onTabClick = {
                onTabClick(it)
            })
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    AnimatedVisibility(visible = isLoading) {
                        CircularProgressIndicator(color = Gold)
                    }
                    AnimatedVisibility(visible = !isLoading) {
                        if (isQuran) {
                            LazyColumn(
                                modifier = Modifier.weight(1f),
                                contentPadding = PaddingValues(vertical = 16.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                items(quranList) { item ->
                                    val index = quranList.indexOf(item)
                                    SoundItem(
                                        title = suwarList[item.chapterId!!-1].name!!,
                                        isPlaying = quranAudioPlayStates[index],
                                        onOpenSoundClick = { audioIsPlay ->
                                            coroutineScope.launch {
                                                quranAudioPlayStates.fill(false)
                                                radioAudioPlayStates.fill(false)
                                                indexOfAudioIsOpen = index
                                                quranIsOpen = true
                                                quranAudioPlayStates[index] = audioIsPlay
                                                onAudioOpenClick(suwarList[item.chapterId!!-1].name!!,item.audioUrl!!, audioIsPlay)
                                            }
                                        },
                                        onBackwardClick = { coroutineScope.launch { onBackwardClick() } },
                                        onForwardClick = { coroutineScope.launch { onForwardClick() } }
                                    )
                                }
                            }
                        } else {
                            LazyColumn(
                                modifier = Modifier.weight(1f),
                                contentPadding = PaddingValues(vertical = 16.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                items(radioList) { item ->
                                    val index = radioList.indexOf(item)
                                    SoundItem(
                                        title = item.name!!,
                                        isPlaying = radioAudioPlayStates[index],
                                        onOpenSoundClick = { audioIsPlay ->
                                            radioAudioPlayStates.fill(false)
                                            quranAudioPlayStates.fill(false)
                                            indexOfAudioIsOpen = index
                                            quranIsOpen = false
                                            radioAudioPlayStates[index] = audioIsPlay
                                            onAudioOpenClick(item.name!!, item.url!!, audioIsPlay)
                                        },
                                        onBackwardClick = { onBackwardClick() },
                                        onForwardClick = { onForwardClick() }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}





