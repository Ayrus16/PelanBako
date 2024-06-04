package id.ac.unpas.pelanbako.ui.screens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.unpas.pelanbako.base.LiveCoroutinesViewModel
import id.ac.unpas.pelanbako.models.item
import id.ac.unpas.pelanbako.repositories.TodoRepository
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(private val todoRepository: TodoRepository) : LiveCoroutinesViewModel() {

    private val _isDone: MutableLiveData<Boolean> = MutableLiveData(false)
    val isDone: LiveData<Boolean> = _isDone

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _item: MutableLiveData<item> = MutableLiveData()
    val item: LiveData<item> = _item

    private val _isDeleted: MutableLiveData<Boolean> = MutableLiveData(false)
    val isDeleted: LiveData<Boolean> = _isDeleted

    private val _todo: MutableLiveData<Boolean> = MutableLiveData(false)
    val todos : LiveData<List<item>> = _todo.switchMap {
        _isLoading.postValue(true)
        launchOnViewModelScope {
            todoRepository.loadItems(
                onSuccess = {
                    _isLoading.postValue(false)
                },
                onError = {
                    _isLoading.postValue(false)
                    Log.e("ItemViewModel", it)
                }
            ).asLiveData()
        }
    }

    suspend fun insert(
        id: String,
        name: String,
        description: String,
        price: Int,
        stock: Int
    ) {
        _isLoading.postValue(true)
        todoRepository.insert(item(id, name, description, price, stock),
            onSuccess = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _todo.postValue(true)
            },
            onError = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _todo.postValue(true)
            }
        )
    }

    suspend fun update(
        id: String,
        name: String,
        description: String,
        price: Int,
        stock: Int
    ) {
        _isLoading.postValue(true)
        todoRepository.update(item(id, name, description, price, stock),
            onSuccess = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _todo.postValue(true)
            },
            onError = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _todo.postValue(true)
            }
        )
    }

    suspend fun delete(id: String) {
        _isLoading.postValue(true)
        todoRepository.delete(id,
            onSuccess = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _todo.postValue(true)
                _isDeleted.postValue(true)
            },
            onError = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _todo.postValue(true)
                _isDeleted.postValue(false)
            }
        )
    }

    suspend fun find(id: String) {
        val todo = todoRepository.find(id)
        todo?.let {
            _item.postValue(it)
        }
    }
}