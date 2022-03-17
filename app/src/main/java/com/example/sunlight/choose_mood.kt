package com.example.sunlight

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.drawerlayout.widget.DrawerLayout
import com.example.Sunlight.R
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_choose_mood.*
import kotlinx.android.synthetic.main.menu_contents.*

class choose_mood : AppCompatActivity() {

    companion object {
        const val NAME = "NAME"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_mood)
        setSupportActionBar(toolbar)
        val toogle = ActionBarDrawerToggle(this, drawer_layout,toolbar,R.string.open,R.string.close)
        toogle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(toogle)
        toogle.syncState()
        nav_menu.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.profile -> Toast.makeText(this, "This is the profile", Toast.LENGTH_SHORT).show()
                R.id.Chng_flng -> startActivity(Intent(this,choose_mood::class.java))
                R.id.share -> shareApp()
                R.id.exit -> logout()
            }
            true

        }
        card_happy.setOnClickListener { startActivity(Intent(this, CardHappy::class.java)) }
        card_sad.setOnClickListener { startActivity(Intent(this, CardSad::class.java)) }
        card_tired.setOnClickListener { startActivity(Intent(this, CardTired::class.java)) }
        card_anger.setOnClickListener { startActivity(Intent(this, CardAnger::class.java)) }
//        val intent = getIntent()
//        val name = intent.getStringExtra(NAME)
//        name_text.text = "HEY!!  " + name

//        FirebaseAuth.getInstance()
//        checkUser()


        val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
        val isLogin = sharedPref.getString("Email", "1")
//        logout_choosemood.setOnClickListener {
//            FirebaseAuth.getInstance().signOut()
//            sharedPref.edit().remove("Email").apply()
//            Toast.makeText(
//                this,
//                "You Logged Out Successfully",
//                Toast.LENGTH_SHORT
//            )
//                .show()
//            startActivity(Intent(this, LoginActivity::class.java))
//            finish()
//        }

        if (isLogin == "1") {
            val email = intent.getStringExtra("email")
            if (email != null) {
                with(sharedPref.edit())
                {
                    putString("Email", email)
                    apply()
                }
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }
    private fun shareApp(){
        val url = "Paste App Link Here"   //just for reference, change app link
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "type/plain"
        intent.putExtra("Share this",url)
        val chooser = Intent.createChooser(intent,"Share Using...")
        startActivity(chooser)

    }
    //code for logout
    fun logout(){
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this,LoginActivity::class.java))

        finish()

    }




}

