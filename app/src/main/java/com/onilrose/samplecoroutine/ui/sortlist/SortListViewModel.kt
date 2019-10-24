package com.onilrose.samplecoroutine.ui.sortlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SortListViewModel : ViewModel() {
    private val _mList = MutableLiveData<List<String>>()
    val mList: LiveData<List<String>> = _mList

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean> = _showLoading

    init {
        initList()
    }

    private fun initList() {
        val list: MutableList<String> = arrayListOf()
        for (item in 900 downTo 1) {
            list.add("Item $item")
        }
        _mList.value = list
    }

    fun sortList() {
        _showLoading.value = true
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                _mList.value = sort()
            }
        }
    }

    fun sortListDesc() {
        _showLoading.value = true
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                _mList.value = sortDesc()
            }
        }
    }

    private suspend fun sort() = withContext(Dispatchers.Default) {
        val indexList: MutableList<Int> = arrayListOf()
        _mList.value?.forEach {
            indexList.add(it.substringAfterLast(" ").toInt())
        }
        val sortedList: MutableList<String> = arrayListOf()
        indexList.sorted().forEach {
            sortedList.add("Item $it")
        }
        _mList.value?.toList()?.sorted()

        return@withContext sortedList
    }

    suspend fun sortDesc() = withContext(Dispatchers.Default) {
        val indexList: MutableList<Int> = arrayListOf()
        _mList.value?.forEach {
            indexList.add(it.substringAfterLast(" ").toInt())
            //indexList.add(it.takeLast(1).toInt())
        }
        val sortedList: MutableList<String> = arrayListOf()
        indexList.sortedDescending().forEach {
            sortedList.add("Item $it")
        }
        return@withContext sortedList
    }

    fun hideLoading() {
        _showLoading.value = false
    }

}
