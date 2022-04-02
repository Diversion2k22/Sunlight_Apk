package com.example.sunlight

import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import com.example.Sunlight.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_card_happy.*
import kotlinx.android.synthetic.main.activity_choose_mood.*
import kotlinx.android.synthetic.main.menu_contents.*

class CardHappy : AppCompatActivity() {

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_happy)
        music_card.setOnClickListener{
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://open.spotify.com/playlist/3csF6smVDSSnG8QJxAUqSR")
            startActivity(openURL)
        }
        chill_location.setOnClickListener{
            val gmmIntentUri =
                Uri.parse("geo:0,0?q=" + Uri.encode("Park, Restaurants"))
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        movie_card.setOnClickListener{ startActivity(Intent(this, movie::class.java))}



        game_card.setOnClickListener{
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.crazygames.com/")
            startActivity(openURL)
        }
        logout_cardhappy.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

    }
}
}