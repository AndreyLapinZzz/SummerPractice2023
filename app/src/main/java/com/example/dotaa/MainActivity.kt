package com.example.firstapp

import android.nfc.Tag
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.dotaa.R
import kotlin.math.ceil
import kotlin.math.floor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(5, 11, 24, 255))
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                MainImageAndDotaIcon()
                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Tags()
                    Description()
                    DataScreen()
                    ReviewAndRating()
                    InstallButton()
                }
            }
        }
    }
}

@Composable
fun MainImageAndDotaIcon() {
    Card(
        modifier = Modifier
            .height(350.dp)
            .background(Color(5, 11, 24, 255))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(5, 11, 24, 255))
        )

        Box(
            modifier = Modifier
                .height(300.dp), contentAlignment = Alignment.TopCenter
        ) {
            MainImage()
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        ) {
            Dota()
        }
    }
}

@Composable
private fun MainImage(){
    Image(
        painterResource(id = R.drawable.up),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            //.fillMaxHeight(0.9f)
            .height(300.dp)
        ,contentScale = ContentScale.Crop
    )
}

@Composable
private fun Dota() {
    Box(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.BottomStart
    ) {
        Row (
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            DotaIcon()
            Column(
                modifier = Modifier
            ) {
                Text(
                    "DoTA 2",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                RatingBar(rating = 5.0)
            }
            Text(
                "70M",
                fontSize = 12.sp,
                color = Color(69, 69, 77, 255)
            )
        }
    }
}

@Composable
fun DotaIcon() {
    Box (
        modifier = Modifier
            .size(88.dp)
            .clip(RoundedCornerShape(15))
            .border(BorderStroke(2.dp, Color(31, 36, 48, 255)))
            .background(Color(31, 36, 48, 255))
        , contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier
            .size(84.dp)
            .clip(RoundedCornerShape(14))
            .background(Color.Black)
            , contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .size(54.dp),
                painter = painterResource(id = R.drawable.dota),
                contentDescription = "dota2",
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun RatingBar(
    rating: Double = 0.0,
    starsColor: Color = Color(244, 209, 68, 255),
) {
    val stars = 5
    val filledStars = floor(rating).toInt()
    val unfilledStars = (stars - ceil(rating)).toInt()
    val halfStar = !(rating.rem(1).equals(0.0))
    Row {
        repeat(filledStars) {
            Icon(imageVector = Icons.Outlined.Star, contentDescription = null, tint = starsColor)
        }
        if (halfStar) {
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = starsColor
            )
        }
        repeat(unfilledStars) {
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = starsColor
            )
        }
    }
}

@Composable
fun Tags() {
    val listOfTags = listOf("MOBA", "MULTIPLAYER", "STRATEGY")
    Row(modifier = Modifier
        .horizontalScroll(rememberScrollState())
        , horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        for (i in 0 until listOfTags.size)
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(Color(68, 169, 244, 61))
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                    text = listOfTags[i],
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(68, 169, 244, 255)
                )
            }
    }
}

@Composable
private fun Description() {
    Text(
        text = "Dota 2 is a multiplayer online battle arena (MOBA) game which has two teams of five players compete to collectively destroy a large structure defended by the opposing team known as the \"Ancient\", whilst defending their own.",
        Modifier.padding(horizontal = 5.dp),
        fontSize = 12.sp,
        color = Color(238, 242, 251, 179),
        lineHeight = 19.sp
    )
}

@Composable
private fun InstallButton() {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(14))
            .background(Color(244, 209, 68, 255))
            .fillMaxWidth()
            .height(64.dp)
        , contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Install",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

data class Data(
    var painter: Painter
)

@Composable
fun DataScreen() {
    val listOfData = listOf(
        Data(painterResource(R.drawable.pic1)),
        Data(painterResource(R.drawable.pic1)),
        Data(painterResource(R.drawable.pic2)),
        Data(painterResource(R.drawable.pic1)),
        Data(painterResource(R.drawable.pic2))
    )
    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(listOfData.size) { index ->
            DataListItem(listOfData[index])
        }
    }
}

@Composable
fun DataListItem(data: Data) {
    Row(
        modifier = Modifier
            .padding(5.dp)
    ) {
        Image(
            painter = data.painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun Ratings () {
    Text(
        text = "Review & Ratings",
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "4.9",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Column (verticalArrangement = Arrangement.spacedBy(8.dp)) {
            RatingBar(rating = 4.9)
            Text(
                text = "70M Reviewers",
                fontSize = 12.sp,
                color = Color(168, 173, 183, 255)
            )
        }
    }
}

@Composable
fun Reviews() {
    val mapOfReviews = mapOf(
        0 to listOfNotNull(
            Data(painterResource(R.drawable.avatar1)),
            "Auguste Conte",
            "February 13, 2018",
            "“Once you start to learn its secrets, there’s a wild and exciting variety of play here that’s unmatched, even by its peers.”"
        ),
        1 to listOfNotNull(
            Data(painterResource(R.drawable.avatar2)),
            "Jang Marcelino",
            "February 14, 2019",
            "“Once you start to learn its secrets, there’s a wild and exciting variety of play here that’s unmatched, even by its peers.”"
        )
    )
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        for (i in 0 until mapOfReviews.size) {
            ColumnListItem(mapOfReviews[i])
            if (i != mapOfReviews.size - 1)
                Divider(color = Color(26, 31, 41, 255), thickness = 1.dp)
        }
    }
//    LazyColumn(
//        modifier = Modifier
//            .fillMaxHeight(0.5f)
//            .wrapContentHeight(unbounded = true),
//        verticalArrangement = Arrangement.spacedBy(24.dp)
//    ) {
//        items(mapOfReviews.size) { index ->
//            ColumnListItem(mapOfReviews[index])
//        }
//    }
}

@Composable
fun ColumnListItem(data: List<Any>?) {
    val img = data!![0] as Data
    var name = data[1] as String
    val date = data[2] as String
    val review = data[3] as String
    Box() {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = img.painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    Text(
                        name,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                    Text(
                        date,
                        fontSize = 12.sp,
                        color = Color(255, 255, 255, 102)
                    )
                }
            }
            Text(
                review,
                fontSize = 12.sp,
                color = Color(168, 173, 183, 255),
                lineHeight = 20.sp,
                letterSpacing = 0.5.sp
            )
        }

    }
}

@Composable
fun ReviewAndRating() {
    Box() {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Ratings()
            Reviews()
        }
    }
}