package com.onilrose.samplecoroutine.ui.docs

import android.content.Context
import android.net.Uri
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.*


class DocsViewModel : ViewModel() {

    private var _mPath = MutableLiveData<String>()
    val mPath: LiveData<String> = _mPath

    fun fetchDocs(activity: FragmentActivity?) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                _mPath.value = getDocs(activity)
            }
        }
    }

    suspend fun saveDocs(activity: FragmentActivity?, text: String) = withContext(Dispatchers.IO) {
        val response = launch {
            val file = "temp2.txt"
            val data: String = text
            val fileOutputStream: FileOutputStream?
            try {
                fileOutputStream = activity?.openFileOutput(file, Context.MODE_PRIVATE)
                fileOutputStream?.write(data.toByteArray())
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: NumberFormatException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        response.join()
        fetchDocs(activity)
    }

    suspend fun getDocs(activity: FragmentActivity?) = withContext(Dispatchers.IO) {
        try {
            val filename = "temp2.txt"
            var fileInputStream: FileInputStream? = null
            fileInputStream = activity?.openFileInput(filename)
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder: StringBuilder = StringBuilder()
            var text: String? = null
            while ({ text = bufferedReader.readLine(); text }() != null) {
                stringBuilder.append(text)
            }
            return@withContext stringBuilder.toString()
        } catch (e: java.lang.Exception) {
            return@withContext ""
        }
    }
}
