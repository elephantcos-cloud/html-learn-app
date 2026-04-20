package com.htmllearn.app.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.*
import com.htmllearn.app.data.repository.AppRepository
import com.htmllearn.app.ui.screens.dashboard.DashboardScreen
import com.htmllearn.app.ui.screens.editor.EditorScreen
import com.htmllearn.app.ui.screens.lessons.ChaptersScreen
import com.htmllearn.app.ui.screens.lessons.LessonDetailScreen
import com.htmllearn.app.ui.screens.profile.ProfileScreen
import com.htmllearn.app.ui.screens.projects.ProjectsScreen
import com.htmllearn.app.ui.screens.reference.ReferenceScreen
import com.htmllearn.app.ui.screens.quiz.QuizScreen
import com.htmllearn.app.ui.screens.settings.SettingsScreen
import com.htmllearn.app.ui.components.BottomNavBar
import com.htmllearn.app.worker.WorkScheduler

sealed class Screen(val route: String) {
    object Dashboard : Screen("dashboard")
    object Chapters  : Screen("chapters")
    object Editor    : Screen("editor")
    object Reference : Screen("reference")
    object Projects  : Screen("projects")
    object Profile   : Screen("profile")
    object Settings  : Screen("settings")
    object LessonDetail : Screen("lesson/{lessonId}") {
        fun go(id: String) = "lesson/$id"
    }
    object Quiz : Screen("quiz/{lessonId}") {
        fun go(id: String) = "quiz/$id"
    }
}

val bottomNavItems = listOf(
    Screen.Dashboard, Screen.Chapters, Screen.Editor, Screen.Reference, Screen.Profile
)

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val ctx = LocalContext.current
    val repo = remember { AppRepository.get(ctx) }

    LaunchedEffect(Unit) {
        repo.ensureUser()
        WorkScheduler.scheduleDailyReminder(ctx)
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val showBottomBar = bottomNavItems.any { it.route == currentRoute }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavBar(currentRoute = currentRoute ?: "") {
                    navController.navigate(it) {
                        popUpTo(Screen.Dashboard.route) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        }
    ) { padding ->
        NavHost(navController, startDestination = Screen.Dashboard.route,
            modifier = Modifier.padding(padding)) {

            composable(Screen.Dashboard.route) {
                DashboardScreen(onNavigate = { navController.navigate(it) })
            }
            composable(Screen.Chapters.route) {
                ChaptersScreen(onLessonClick = { navController.navigate(Screen.LessonDetail.go(it)) })
            }
            composable(Screen.Editor.route) { EditorScreen() }
            composable(Screen.Reference.route) { ReferenceScreen() }
            composable(Screen.Projects.route) { ProjectsScreen() }
            composable(Screen.Profile.route) {
                ProfileScreen(onSettings = { navController.navigate(Screen.Settings.route) })
            }
            composable(Screen.Settings.route) {
                SettingsScreen(onBack = { navController.popBackStack() })
            }
            composable(Screen.LessonDetail.route,
                arguments = listOf(navArgument("lessonId") { type = NavType.StringType })) { back ->
                val id = back.arguments?.getString("lessonId") ?: return@composable
                LessonDetailScreen(
                    lessonId = id,
                    onQuiz   = { navController.navigate(Screen.Quiz.go(id)) },
                    onBack   = { navController.popBackStack() }
                )
            }
            composable(Screen.Quiz.route,
                arguments = listOf(navArgument("lessonId") { type = NavType.StringType })) { back ->
                val id = back.arguments?.getString("lessonId") ?: return@composable
                QuizScreen(lessonId = id, onDone = { navController.popBackStack() })
            }
        }
    }
}
