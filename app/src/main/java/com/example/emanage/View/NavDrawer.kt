package com.example.emanage.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.emanage.R
import com.example.emanage.ui.theme.EManageTheme

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CompNav() {
    var isDark by remember { mutableStateOf(true) }
    var drawer = rememberDrawerState(DrawerValue.Closed)
    var scope = rememberCoroutineScope()

    EManageTheme(darkTheme = isDark) {
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet(modifier = Modifier
                    .width(280.dp)
                    .shadow(elevation = 10.dp, shape = RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surface)) {
                    Text(
                        text = "EManage App",
                        modifier = Modifier.padding(top = 20.dp, start = 16.dp),
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(50.dp))
                    HorizontalDivider(thickness = 5.dp, color = MaterialTheme.colorScheme.secondary)
                    Spacer(modifier = Modifier.height(16.dp))
                    NavigationDrawerItem(label = {
                        Text(
                            text = if (!isDark) "Dark Mode" else "Light Mode",
                            style = MaterialTheme.typography.bodyLarge,
                            color = if (isDark) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary
                        )
                    },
                        selected = false,
                        onClick = { isDark = !isDark },

                        icon = {
                            Icon(
                                imageVector = if (!isDark) ImageVector.vectorResource(id = R.drawable.baseline_dark_mode_24)
                                else ImageVector.vectorResource(id = R.drawable.baseline_light_mode_24),
                                contentDescription = "",
                                tint = if (!isDark) Color.Black else MaterialTheme.colorScheme.primary
                            )
                        }
                    )
                    
                    NavigationDrawerItem(label = {Text(
                        text = "Made with ❤️ by Papaji",
                        style = MaterialTheme.typography.bodyLarge,
                        color = if (isDark) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary
                    ) },
                        selected = false,
                        onClick = { /*TODO*/ })


                }
            },
            drawerState = drawer,

        ) {


        CompToolbar(drawer = drawer, scope = scope )



}}}