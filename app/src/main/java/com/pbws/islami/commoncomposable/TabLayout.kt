package com.pbws.islami.commoncomposable

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pbws.islami.ui.theme.AlphaBlack
import com.pbws.islami.ui.theme.Black
import com.pbws.islami.ui.theme.Gold
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun TabLayout(tabs: List<String>) {
    val interactionSource = object : MutableInteractionSource {
        override val interactions: Flow<Interaction> = emptyFlow()

        override suspend fun emit(interaction: Interaction) {}

        override fun tryEmit(interaction: Interaction) = true
    }

    var tabIndexSelected by remember {
        mutableStateOf(0)
    }
    TabRow(
        containerColor = Color.Transparent,
        indicator = {},
        divider = {},
        modifier = Modifier
            .padding(top = 16.dp)
            .background(color = AlphaBlack, shape = RoundedCornerShape(8.dp))
            .padding(4.dp),
        selectedTabIndex = tabIndexSelected
    ) {
        tabs.forEachIndexed { index, tabText ->
            Tab(
                interactionSource = interactionSource,
                modifier = Modifier
                    .background(
                        color = if (tabIndexSelected == index) Gold else Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(vertical = 8.dp),
                selected = tabIndexSelected == index,
                onClick = { tabIndexSelected = index }) {
                Text(
                    text = tabText,
                    fontSize = 16.sp,
                    color = if (tabIndexSelected == index) Black else Color.White,
                    fontWeight = if (tabIndexSelected == index) FontWeight.SemiBold else FontWeight.Normal,
                )
            }
        }
    }

}