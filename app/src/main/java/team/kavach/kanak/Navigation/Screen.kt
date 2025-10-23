package team.kavach.kanak.Navigation

import kotlinx.serialization.Serializable


enum class Destination {
    Home,
    Alert,
    Farm,
    Weather
}


sealed class Screen (val route : String) {
    object Home : Screen(Destination.Home.name)
    object Alert : Screen(Destination.Alert.name)

    object Farm : Screen(Destination.Farm.name)
    object Weather : Screen(Destination.Weather.name)
}