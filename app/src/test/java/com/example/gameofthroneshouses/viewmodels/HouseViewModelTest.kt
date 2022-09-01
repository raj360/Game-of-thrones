package com.example.gameofthroneshouses.viewmodels

import com.example.gameofthroneshouses.models.House
import com.example.gameofthroneshouses.usecases.HouseUseCase
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HouseViewModelTest{
    @Mock
    lateinit var houseUseCase: HouseUseCase
    private lateinit var houseViewModel: HouseViewModel


    @Before
    fun setup(){
        houseViewModel = HouseViewModel(houseUseCase)
    }

    @Test
    fun cantGetHouseWithoutName(){
        houseViewModel.setHouseName("")

        val houseDetails = houseViewModel.getHouseDetails()

        assertEquals(false, houseDetails)
    }


}