package com.example.emanage.View

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.emanage.Model.ExpenseList
import com.example.emanage.ui.theme.LocalExtendedColors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompToolbar(drawer: DrawerState, scope: CoroutineScope) {
    val extendedColors = LocalExtendedColors.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "eManage App",
                        color = MaterialTheme.colorScheme.primary, // HERE HAVE TO START
                        style = MaterialTheme.typography.headlineLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        drawer.apply {
                            scope.launch {
                                if (isOpen) close() else open()
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.secondary,
                    actionIconContentColor = MaterialTheme.colorScheme.primary

                )
            )
        },
        floatingActionButton = {
            FloatingButton()


        })
    { innerpadding ->

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerpadding)) {

            CompFilterChip()


        }

    }
}

@Composable
fun FloatingButton() {
    val extendedColors = LocalExtendedColors.current

    var showBottomsheet by remember { mutableStateOf(false) }
    FloatingActionButton(
        onClick = {
//            Toast.makeText(context, "Button is pressed", Toast.LENGTH_SHORT).show()
            showBottomsheet = true
        },
        containerColor = extendedColors.sixth
    ) {

        Icon(imageVector = Icons.Default.Add, contentDescription = "")

        if (showBottomsheet){
            CompBottomSheet(onDismiss = {showBottomsheet = false})
        }
    }

}



