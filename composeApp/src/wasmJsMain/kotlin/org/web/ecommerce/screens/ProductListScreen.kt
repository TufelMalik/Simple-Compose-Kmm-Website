package org.web.ecommerce.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.web.ecommerce.data.Product


@Composable
fun ProductListScreen(
    products: State<List<Product>>,
    onItemClick: (Int?) -> Unit,
    onBackClick: () -> Unit,
    onNextClick: () -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Products")
                }
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            actions = {
                IconButton(onClick = onNextClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Next"
                    )
                }
            }
        )
    }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(items = products.value) { product ->
                ProductItem(product, onItemClick)
            }
        }
    }
}

@Composable
fun ProductItem(product: Product, navToDetails: (Int?) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF2F2F2))
            .padding(8.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navToDetails(product.id) },
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                // Product Image
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                ) {
//                    Image(
//                        painter = rememberImagePainter(product.thumbnail ?: ""),
//                        contentDescription = product.title,
//                        modifier = Modifier
//                            .size(120.dp)
//                            .clip(RoundedCornerShape(8.dp)),
//                        contentScale = ContentScale.Crop
//                    )

                    // Discount Badge
                    if ((product.discountPercentage ?: 0.0) > 0) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .background(Color.Red, shape = RoundedCornerShape(bottomEnd = 8.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "-${product.discountPercentage}%",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.body2
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                // Product Details
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    // Product Title
                    Text(
                        text = product.title ?: "Unknown Product",
                        style = MaterialTheme.typography.h3,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // Ratings (Amazon Style)
                    val avgRating = product.reviews?.map { it.rating ?: 0 }?.average()?.toInt() ?: 0
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        repeat(avgRating) {
                            Icon(
                                Icons.Filled.Star,
                                contentDescription = "Star",
                                tint = Color(0xFFFFD700)
                            ) // Gold Color
                        }
                        if (avgRating == 0) {
                            Text(
                                text = "No Ratings",
                                color = Color.Gray,
                                style = MaterialTheme.typography.h3
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // Price and Discounted Price
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "$${product.price}",
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        if ((product.discountPercentage ?: 0.0) > 0) {
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "${product.discountPercentage}% OFF",
                                color = Color.Red,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.h6
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // Availability Status
                    Text(
                        text = product.availabilityStatus ?: "In Stock",
                        color = if (product.availabilityStatus == "In Stock") Color(0xFF008000) else Color.Red,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.h6
                    )
                }
            }
        }
    }
}