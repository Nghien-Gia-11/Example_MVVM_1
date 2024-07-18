package com.example.example_mvvm_1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.example_mvvm_1.Fragment.CountCharacterFragment
import com.example.example_mvvm_1.Fragment.ProfileFragment
import com.example.example_mvvm_1.Fragment.TriangleFragment
import com.example.example_mvvm_1.Fragment.TriangleTouchFragment
import com.example.example_mvvm_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startFragment(CountCharacterFragment())

        binding.bottomNavBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menuTest1 -> {
                    startFragment(CountCharacterFragment())
                }

                R.id.menuTest2 -> {
                    startFragment(TriangleFragment())
                }

                R.id.menuTest3 -> {
                    startFragment(ProfileFragment())
                }

                else -> {
                    startFragment(TriangleTouchFragment())
                }
            }

            true
        }


    }


    private fun startFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            commit()
        }
    }

}