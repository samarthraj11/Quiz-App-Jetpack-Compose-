package com.example.quizapp.repository

import com.example.quizapp.data.DataOrException
import com.example.quizapp.model.Question
import com.example.quizapp.model.QuestionItem
import com.example.quizapp.network.QuestionAPI
import javax.inject.Inject

class QuestionRepository @Inject constructor(private val api: QuestionAPI){
    private val dataOrException = DataOrException<ArrayList<QuestionItem>, Boolean, Exception>()

    suspend fun getAllQuestions(): DataOrException<ArrayList<QuestionItem>,Boolean, Exception> {
        try {
            dataOrException.loading = true
            dataOrException.data = api.getAllQuestions()
            if(dataOrException.data.toString().isNotEmpty()) dataOrException.loading = false
        }
        catch (exception: Exception) {
            dataOrException.e = exception
        }
        return dataOrException
    }

}