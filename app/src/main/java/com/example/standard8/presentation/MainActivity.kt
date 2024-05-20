package com.example.standard8.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.standard8.R
import com.example.standard8.databinding.ActivityMainBinding
import com.example.standard8.presentation.bookmark.BookMarkFragment
import com.example.standard8.presentation.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

private const val TAG_SEARCH = "Home_fragment"
private const val TAG_BOOK = "book_mark_fragment"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setFragment(TAG_SEARCH, HomeFragment())

        binding.navigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.navi_book -> setFragment(TAG_BOOK, BookMarkFragment())
                R.id.navi_search -> setFragment(TAG_SEARCH,HomeFragment())
            }
            true
        }
    }
    private fun setFragment(tag: String, fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val fragTransaction = manager.beginTransaction()

        if (manager.findFragmentByTag(tag) == null){
            fragTransaction.add(R.id.mainFrame, fragment, tag)
        }

        val search = manager.findFragmentByTag(TAG_SEARCH)
        val book = manager.findFragmentByTag(TAG_BOOK)

        if (search != null){
            fragTransaction.hide(search)
        }

        if (book != null){
            fragTransaction.hide(book)
        }


        if (tag == TAG_SEARCH) {
            if (search!=null){
                fragTransaction.show(search)
            }
        }
        else if (tag == TAG_BOOK) {
            if (book != null) {
                fragTransaction.show(book)
            }
        }

        fragTransaction.commitAllowingStateLoss()
    }
}