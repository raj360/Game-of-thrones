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

    /**
     * Since we don't have business logic for this assignment
     * challenge I have created this to show how it would be done.
     */
    @Test
    fun `checks if name param fields is empty before fetching details`(){
        val canFetchDetails = houseViewModel.isNameParamEmpty()
        assertEquals(true, canFetchDetails)
    }


}