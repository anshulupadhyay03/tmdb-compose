package com.retroent.moviebuff

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.retroent.moviebuff.features.nowplaying.NowPlayingMovies
import com.retroent.moviebuff.features.popularmovies.PopularMoviesScreen
import com.retroent.moviebuff.features.toprated.TopRatedMovies
import com.retroent.moviebuff.features.upcomingmovies.UpcomingMovies
import com.retroent.moviebuff.ui.theme.MovieBuffTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieBuffTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                    val scope = rememberCoroutineScope()
                    val navController = rememberNavController()
                    ModalNavigationDrawer(
                        drawerState = drawerState,
                        drawerContent = {
                            ModalDrawerSheet {
                                AppDrawer()
                            }
                        }
                    ) {
                        AppScaffoldContent(navController) {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffoldContent(navController: NavHostController, onHamburgerClicked: () -> Unit) {
    Scaffold(
        modifier = Modifier,
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        topBar = {
            SetupTopBar(onHamburgerClicked)
        },
        bottomBar = {
            SetupBottomBar(navController)
        }
    ) { pdValue ->
        ShowMainContent(pdValue, navController)
    }
}

@Composable
private fun ShowMainContent(paddingValues: PaddingValues, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BOTTOM_LEVEL_NAVIGATION[0].route,
        Modifier.padding(paddingValues)
    ) {
        composable(BOTTOM_LEVEL_NAVIGATION[0].route) { PopularMoviesScreen() }
        composable(BOTTOM_LEVEL_NAVIGATION[1].route) { UpcomingMovies() }
        composable(BOTTOM_LEVEL_NAVIGATION[2].route) { NowPlayingMovies() }
        composable(BOTTOM_LEVEL_NAVIGATION[3].route) { TopRatedMovies() }
    }
}

@Composable
fun SetupBottomBar(navController: NavController) {
    var tabState by rememberSaveable { mutableStateOf(0) }

    /* NavigationRail(
         modifier = Modifier,
         containerColor = Color.Transparent,
         contentColor = MaterialTheme.colorScheme.onSurfaceVariant
     ) {

     }*/

    NavigationBar {
        BOTTOM_LEVEL_NAVIGATION.forEachIndexed { index, tabDetails ->
            val isSelected = tabState == index
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    tabState = index
                    navController.navigate(tabDetails.route)
                },
                icon = {
                    Icon(
                        painter = painterResource(
                            id = if (isSelected) tabDetails.selectedIcon else tabDetails.unselectedIcon
                        ),
                        contentDescription = null
                    )
                },
                label = { Text(text = stringResource(id = tabDetails.iconTextId)) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupTopBar(onHamburgerClicked: () -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.secondary),
        navigationIcon = {
            IconButton(
                onClick = {
                    onHamburgerClicked()
                }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.menu_top_icon),
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
fun AppDrawer() {
    Row {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(MaterialTheme.colorScheme.onBackground)
                .padding(10.dp)
                .border(2.dp, Color.Gray)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxSize(),
                textAlign = TextAlign.Center,
                text = "Hi All good!"
            )
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieBuffTheme {
        Greeting("Android")
    }
}