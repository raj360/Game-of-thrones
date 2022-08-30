package com.example.gameofthroneshouses.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gameofthroneshouses.databinding.ActivityDetailsBinding
import com.example.gameofthroneshouses.models.House
import com.example.gameofthroneshouses.utils.HOUSE_NAME
import com.example.gameofthroneshouses.utils.SResult
import com.example.gameofthroneshouses.viewmodels.HouseViewModel
import com.pharmacure.online_pharmacy_app.viewmodels.factory.ViewModelFactory
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.di
import org.kodein.di.instance

class DetailsActivity : AppCompatActivity(), DIAware {

    override val di: DI by di(this)
    private val factory: ViewModelFactory by instance()
    private lateinit var houseViewModel: HouseViewModel
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val name = intent.getStringExtra(HOUSE_NAME)!!

        houseViewModel = ViewModelProvider(this, factory)[HouseViewModel::class.java]
        houseViewModel.setHouseName(name)
        houseViewModel.getHouseDetails().observe(this, Observer(::handleHouseDetailsResult))
    }

    private fun handleHouseDetailsResult(result: SResult<House>) {
        when (result) {
            is SResult.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }

            is SResult.Success -> {
                binding.progressBar.visibility = View.INVISIBLE
                binding.houseName.text = result.data.name
                (
                        "Region: ${result.data.region}." +
                                "\nCort Of Arms: ${result.data.coatOfArms}." +
                                "\nWords: ${result.data.words}.  " +
                                "\nTitles: ${result.data.titles}.  " +
                                "\nSeats: ${result.data.seats}.  " +
                                "\nFounded: ${result.data.founded}.  " +
                                "\nAncestral Weapons: ${result.data.ancestralWeapons}." +
                                "\nFounded: ${result.data.founded}.  "
                        ).also { binding.houseDetails.text = it }
            }

            is SResult.Error -> {
                binding.progressBar.visibility = View.INVISIBLE
                Toast.makeText(this, "Error : ${result.message}", Toast.LENGTH_SHORT).show()
            }

            is SResult.Empty -> {
                binding.progressBar.visibility = View.INVISIBLE
                Toast.makeText(this, "Server not reachable", Toast.LENGTH_SHORT).show()
            }
        }
    }
}