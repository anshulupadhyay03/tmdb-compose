package com.retroent.moviebuff

data class TabDetails(
    val route:String,
    val selectedIcon:Int,
    val unselectedIcon:Int,
    val iconTextId:Int
)

val BOTTOM_LEVEL_NAVIGATION = listOf(
    TabDetails(
        route = "bottomMenu/popular",
        selectedIcon = R.drawable.baseline_home_filled,
        unselectedIcon = R.drawable.outline_home,
        iconTextId = R.string.popular
    ),
    TabDetails(
        route = "bottomMenu/upcoming",
        selectedIcon = R.drawable.baseline_home_filled,
        unselectedIcon = R.drawable.outline_home,
        iconTextId = R.string.Upcoming
    ),
    TabDetails(
        route = "bottomMenu/now_playing",
        selectedIcon = R.drawable.baseline_home_filled,
        unselectedIcon = R.drawable.outline_home,
        iconTextId = R.string.Now_playing
    ),
    TabDetails(
        route = "bottomMenu/top_rated",
        selectedIcon = R.drawable.baseline_home_filled,
        unselectedIcon = R.drawable.outline_home,
        iconTextId = R.string.Top_rated
    )

)