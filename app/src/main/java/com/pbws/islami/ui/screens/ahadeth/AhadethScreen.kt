package com.pbws.islami.ui.screens.ahadeth

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.pbws.islami.ui.screens.commoncomposable.TafasierAhadethContent

@Composable
fun AhadethScreen(){
    TafasierAhadethContent(content = "osman", title ="osman saad" )
}
@Preview(showSystemUi = true)
@Composable
fun AhadethScreenPreview(){
    TafasierAhadethContent(content = "osman", title ="osman saad" )
}