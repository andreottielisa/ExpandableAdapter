package com.andreotti.expandable.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andreotti.expandable.sample.adapter.ExpandableSampleAdapter
import com.andreotti.expandable.sample.databinding.ActivityMainBinding
import com.andreotti.expandable.sample.factory.SampleModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val factory by lazy { SampleModelFactory(resources) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }
        binding.recyclerView.adapter = ExpandableSampleAdapter(factory.create())
    }
}