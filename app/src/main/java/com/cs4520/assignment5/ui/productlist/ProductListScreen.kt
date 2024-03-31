package com.cs4520.assignment5.ui.productlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cs4520.assignment5.R

@Composable
fun ProductListScreen() {
}

@Composable
fun ProductListScreen(
    products: List<Product>,
    currentPage: Int,
    onPrevPage: () -> Unit,
    onNextPage: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight(0.90F)
        ) {
            items(items = products) {
                ProductItem(it)
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
             Button(
                 onClick = onPrevPage
             ) {
                 Image(
                     painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
                     contentDescription = "",
                 )
             }

            Text(
                text = currentPage.toString(),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Button(
                onClick = onNextPage
            ) {
                Image(
                    painter = painterResource(R.drawable.baseline_arrow_forward_ios_24),
                    contentDescription = "",
                )
            }
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    val background = when(product.type) {
        "Equipment" -> R.color.light_red
        "Food" -> R.color.light_yellow
        else -> R.color.white
    }

    val image = when(product.type) {
        "Equipment" -> R.drawable.equipment
        "Food" -> R.drawable.food
        else -> R.drawable.food
    }

    Surface(
        color = colorResource(background)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "product image",
                modifier = Modifier
                    .height(90.dp)
                    .width(90.dp)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                Text(
                    text = product.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                if (product.expiryDate != null) {
                    Text(text = product.expiryDate)
                }
                Text(text = "$ " + product.price.toString())
            }
        }
    }
}

@Preview
@Composable
private fun ProductListPreview() {
    val productList = listOf<Product>(
        Product("Papaya", "Food", "2024-03-08", 2.0),
        Product("Foam Roller", "Equipment", null, 25.0),
        Product("Fig", "Food", "2024-03-05", 1.5),
        Product("Balance Board", "Equipment", null, 30.0),
        Product("Avocado", "Food", "2024-03-08", 2.5),
        Product("Agility Ladder", "Equipment", null, 40.0),
        Product("Guava", "Food", "2024-03-07", 2.0),
        Product("Agility Cones", "Equipment", "2024-03-07", 3.0),
        Product("Passion Fruit", "Food", null, 3.0),
        Product("Exercise Cones", "Equipment", "2024-03-07", 3.0),
    )
    ProductListScreen(productList, 1, {}, {})
}