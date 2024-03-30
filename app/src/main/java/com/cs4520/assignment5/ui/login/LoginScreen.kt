package com.cs4520.assignment5.ui.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.cs4520.assignment5.R

@Composable
fun LoginScreen(
    onSuccessLogin: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        TextField(
            value = username,
            onValueChange = {
                username = it
            },
            placeholder = {
                Text(text = "Username")
            },
            modifier = Modifier.fillMaxWidth()

        )

        TextField(
            value = password,
            onValueChange = {
                password = it
            },
            placeholder = {
                Text(text = "Password")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (username == "admin" && password == "admin") {
                    Toast.makeText(context, R.string.login_success_message, Toast.LENGTH_SHORT).show()
                    onSuccessLogin()
                } else {
                    Toast.makeText(context, R.string.login_fail_message, Toast.LENGTH_SHORT).show()
                }
            },
        ) {
            Text(text = "Login")
        }
    }

}