package com.example.gameofthroneshouses.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gameofthroneshouses.databinding.ActivityHousesListBinding
import com.example.gameofthroneshouses.models.House
import com.example.gameofthroneshouses.utils.SResult
import com.example.gameofthroneshouses.viewmodels.HouseViewModel
import com.pharmacure.online_pharmacy_app.viewmodels.factory.ViewModelFactory
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.di
import org.kodein.di.instance

class HousesListActivity : AppCompatActivity(), DIAware {

    override val di: DI by di(this)
    private val factory: ViewModelFactory by instance()
    private lateinit var houseViewModel: HouseViewModel
    private lateinit var binding: ActivityHousesListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHousesListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        houseViewModel = ViewModelProvider(this, factory)[HouseViewModel::class.java]
        houseViewModel.getHousesList().observe(this, Observer(::handleHousesResult))

    }

    private fun handleHousesResult(result: SResult<List<House>>){
        when(result){
            is SResult.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }

            is SResult.Success -> {
                binding.progressBar.visibility = View.INVISIBLE
                binding.housesRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
                binding.housesRecyclerView.adapter = CustomAdapter(result.data, this)
            }

            is SResult.Error -> {
                binding.progressBar.visibility = View.INVISIBLE
                Toast.makeText(this,"Error : ${result.message}", Toast.LENGTH_SHORT).show()
            }

            is SResult.Empty -> {
                binding.progressBar.visibility = View.INVISIBLE
                Toast.makeText(this,"Server not reachable", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
