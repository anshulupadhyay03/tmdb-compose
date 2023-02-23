package com.retroent.moviebuff

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.retroent.moviebuff.features.moviedetails.MovieDetailsScreen
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

    var bottomBarVisibilityState by rememberSaveable { (mutableStateOf(true)) }
    var topBarVisibilityState by rememberSaveable { (mutableStateOf(true)) }

    Scaffold(
        topBar = {
            SetupTopBar(onHamburgerClicked, topBarVisibilityState)
        },
        bottomBar = {
            SetupBottomBar(navController, bottomBarVisibilityState)
        }
    ) { pdValue ->
        ShowMainContent(pdValue, navController)
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val route = navBackStackEntry?.destination?.route
    println("my route $route")
    when (route) {
        "details/{movieId}" -> {
            topBarVisibilityState = false
            bottomBarVisibilityState = false
        }
        else -> {
            topBarVisibilityState = true
            bottomBarVisibilityState = true
        }
    }

}

@Composable
private fun ShowMainContent(paddingValues: PaddingValues, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home",
        Modifier.padding(paddingValues)
    ) {
        bottomNavGraph(navController)
        composable(
            "details/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) {
            MovieDetailsScreen()
        }
    }
}

@Composable
fun SetupBottomBar(navController: NavController, bottomBarVisibilityState: Boolean) {
    AnimatedVisibility(
        visible = bottomBarVisibilityState,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
    ) {
        NavigationBar {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            BOTTOM_LEVEL_NAVIGATION.forEach { tabDetails ->
                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any { it.route == tabDetails.route } == true,
                    onClick = {
                        navController.navigate(tabDetails.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when re-selecting a previously selected item
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(
                                id = if (currentDestination?.hierarchy?.any { it.route == tabDetails.route } == true) tabDetails.selectedIcon else tabDetails.unselectedIcon
                            ),
                            contentDescription = null
                        )
                    },
                    label = { Text(text = stringResource(id = tabDetails.iconTextId)) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupTopBar(onHamburgerClicked: () -> Unit, topBarVisibilityState:Boolean) {
    AnimatedVisibility(
        visible = topBarVisibilityState,
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = slideOutVertically(targetOffsetY = { -it }),
    ) {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.app_name)) },
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
}

@Composable
fun AppDrawer() {
    Row {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(10.dp)
                .border(2.dp, Color.Gray)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxSize(),
                textAlign = TextAlign.Center,
                text = "Hi, All good!"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieBuffTheme {
        // set the preview
    }
}