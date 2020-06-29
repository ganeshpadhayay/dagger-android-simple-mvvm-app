package com.example.dagger2_practical.ui.main.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.dagger2_practical.SessionManager
import com.example.dagger2_practical.models.Post
import com.example.dagger2_practical.network.main.MainApi
import com.example.dagger2_practical.ui.main.Resource
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class PostsViewModel @Inject constructor(var mainApi: MainApi, var sessionManager: SessionManager) : ViewModel() {

    companion object {
        private const val TAG = "PostsViewModel"
    }

    private var posts: MediatorLiveData<Resource<List<Post>>> = MediatorLiveData<Resource<List<Post>>>()

    fun observePosts(): LiveData<Resource<List<Post>>> {
        posts.value = Resource.loading(null)

        val source: LiveData<Resource<List<Post>>> = LiveDataReactiveStreams.fromPublisher(mainApi.getPostsFromUser(sessionManager.authUser.value?.data?.id!!).subscribeOn(Schedulers.io()).onErrorReturn {
            val postError = Post()
            postError.id = -1
            var posts: MutableList<Post> = ArrayList()
            posts.add(postError)
            posts
        }.map {
            if (it.isNotEmpty()) {
                if (it[0].id == -1) {
                    return@map Resource.error("Something went wrong here", null)
                }
                return@map Resource.success(it)
            } else {
                return@map Resource.error("Something went wrong here", null)
            }
        }) as LiveData<Resource<List<Post>>>

        posts.addSource(source) { postResponse ->
            posts.value = postResponse
            posts.removeSource(source)
        }

        return posts
    }


}