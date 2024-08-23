package com.example.trendingtimesjetpack.presentation.news.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.trendingtimesjetpack.R

@Composable
fun NewsTabView(
    modifier: Modifier = Modifier,
    categoryList: List<String>,
    onTabClick: (Int) -> Unit,
    selectedIndex: Int
) {
    LazyRow(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(categoryList) { index ->
            Box {
                Text(
                    text = index,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clickable(interactionSource = null, indication = null) {
                            onTabClick(categoryList.indexOf(index))
                        },
                    fontWeight = FontWeight.Bold,
                    color = if (categoryList.indexOf(index) == selectedIndex) colorResource(
                        id = R.color.Lavender
                    ) else Color.Black,
                )
            }
        }
    }

}