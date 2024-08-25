package com.example.trendingtimesjetpack.presentation.news.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.trendingtimesjetpack.R
import com.example.trendingtimesjetpack.data.dto.news.Article

@Composable
fun NewsItem(article: Article?,onClick: (String) -> Unit) {
    Row(modifier = Modifier
        .height(120.dp)
        .padding(4.dp)
        .clickable(interactionSource = null, indication = null) {
            onClick(article?.url ?: "https://google.com")
        }) {
        AsyncImage(
            model = article?.urlToImage,
            contentDescription = "Article Image",
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Transparent),
            placeholder = painterResource(id = R.drawable.logo2),
            contentScale = ContentScale.Crop
        )

        Column(verticalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = article?.title ?: "",
                maxLines = 3,
                modifier = Modifier.padding(7.dp),
                color = Color.Black,
                fontWeight = FontWeight.Black,
                fontSize = 16.sp,
                overflow = TextOverflow.Ellipsis,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Filled.Share,
                        contentDescription = "Share News",
                        tint = Color.Black
                    )
                }
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = null,
                    tint = Color.Black
                )
                Text(text = article?.timeDifference ?: "1 Day Ago", color = Color.Black)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun NewsItemPrev() {
    NewsItem(
        article = Article(
            urlToImage = "https://play-lh.googleusercontent.com/OABfRfKxyvf6PUg_6YBJXvbKSbegFsBKvFDEm5jOA0rC5k1la-OwmnWz6GK55vr3EGA",
            title = "why companies are biuing million-dollar domains and why it's paying off",
            publishedAt = "2024-08-26T12:34:45Z"
        ),
        onClick = {}
    )
}