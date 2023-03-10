package com.plcoding.cryptocurrencyappyt.presentation.coin_list.component

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.plcoding.cryptocurrencyappyt.data.models.Coin
import java.lang.reflect.Modifier

@Composable
fun CoinListItem(
    coin:Coin,
    onItemClick:(Coin)->Unit
) {

    Row(modifier =
            Modifier.fillMaxWidth().clickable{ onItemClick(coin)}
                .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = "${coin.rank}.${coin.name}.${coin.symbol}",
            style = MaterialTheme.typography.body1,
            overflow =  TextOverflow.Ellipsis
        )

        Text(
            text = if(coin.is_active) "active" else "inactive",
            color = if(coin.is_active) Color.Green else Color.Red,
            fontStyle = FontStyle.FONT_SLANT_ITALIC,
            style = MaterialTheme.typography.body2,
            overflow =  TextOverflow.Ellipsis,
            modifier = Modifier.align(CenterVertically)
        )
        
    }
    
}