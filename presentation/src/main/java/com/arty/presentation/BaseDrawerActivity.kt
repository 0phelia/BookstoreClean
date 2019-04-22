package com.arty.presentation

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout

open class BaseDrawerActivity : AppCompatActivity() {

    // UI stuff
    private lateinit var drawer: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupActionBar()
    }

    override fun onBackPressed() {
        when {
            drawer.isDrawerOpen(GravityCompat.START) -> drawer.closeDrawer(GravityCompat.START)
            supportFragmentManager.backStackEntryCount > 0 -> {
                animateHamburgerIcon(false)
                supportFragmentManager.popBackStack()
            }
            else -> super.onBackPressed()
        }
    }

    private fun setupActionBar() {
        supportActionBar?.elevation = 0f
        supportActionBar?.setHomeButtonEnabled(true)
        toolbar = findViewById(R.id.toolbar)
        drawer = findViewById(R.id.drawer_layout)
        setSupportActionBar(toolbar)
        drawerToggle = ActionBarDrawerToggle(this, drawer, R.string.drawer_open, R.string.drawer_close)
        drawer.addDrawerListener(drawerToggle)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        drawerToggle.syncState()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if (supportFragmentManager.backStackEntryCount > 0) {
                    onBackPressed()
                } else {
                    drawer.openDrawer(GravityCompat.START)
                }
                return true
            }
        }
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun animateHamburgerIcon(isHomeAsUp: Boolean) {
        val anim = if (isHomeAsUp) ValueAnimator.ofFloat(0f, 1f) else ValueAnimator.ofFloat(1f, 0f)
        anim.apply {
            addUpdateListener { valueAnimator ->
                val slideOffset = valueAnimator.animatedValue as Float
                drawerToggle.onDrawerSlide(drawer, slideOffset)
            }
            interpolator = DecelerateInterpolator()
            duration = 500
            start()
        }
    }

}
