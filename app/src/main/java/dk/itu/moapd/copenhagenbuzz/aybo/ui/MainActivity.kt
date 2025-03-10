package dk.itu.moapd.copenhagenbuzz.aybo.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import dk.itu.moapd.copenhagenbuzz.aybo.R
import dk.itu.moapd.copenhagenbuzz.aybo.databinding.ActivityMainBinding

/**
 * MainActivity is the entry point of the app that initializes and manages the UI.
 * It handles navigation and toolbar interactions, including the top app bar and bottom navigation bar.
 *
 * @constructor Initializes the UI components and navigation for the MainActivity.
 */
class MainActivity : AppCompatActivity() {

    // A set of private constants used in this class. (exercise 1)
    private lateinit var binding: ActivityMainBinding

    companion object {
        private val TAG = MainActivity::class.qualifiedName
    }

    private lateinit var topAppBar: MaterialToolbar
    private lateinit var bottomNavigationView: BottomNavigationView

    /**
     * Called when the activity is created. This method sets up the layout, the app bar,
     * and the bottom navigation view with navigation.
     *
     * @param savedInstanceState The saved instance state that is passed when the activity is restored.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        // Sets the system windows to allow app content to extend to the edges of the screen
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        // Inflate the layout and bind it with the activity's root view
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.topAppBar)

        // Initialize UI components
        bottomNavigationView = binding.bottomNavigation
        topAppBar = binding.topAppBar

        // Set a listener for the top app bar menu items
        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.add -> {
                    // Opens the AddEventBottomSheet fragment when the "add" menu item is clicked
                    AddEventBottomSheet().show(supportFragmentManager, AddEventBottomSheet.TAG)
                    true
                }
                R.id.login -> {
                    // Navigate to the LoginActivity when the "login" menu item is clicked
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.logout -> {
                    // Navigate to the LoginActivity when the "logout" menu item is clicked
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        // Set up the navigation controller for bottom navigation view
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)
    }

    /**
     * Called to create the options menu for the activity.
     * This method inflates the menu resource for the top app bar.
     *
     * @param menu The menu to be inflated.
     * @return Returns true if the menu is created successfully, false otherwise.
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_top_app_bar, menu)
        return true
    }

    /**
     * Prepares the options menu before it is displayed.
     * This method is used to show or hide menu items based on the login state.
     *
     * @param menu The menu that will be modified.
     * @return Returns true if the menu is prepared successfully.
     */
    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menu.findItem(R.id.login).isVisible = !intent.getBooleanExtra("IsLoggedIn", false)
        menu.findItem(R.id.logout).isVisible = intent.getBooleanExtra("IsLoggedIn", false)
        return true
    }
}
