package noblur.com.exomindtest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.collect.Lists
import com.nhaarman.mockitokotlin2.capture
import noblur.com.exomindtest.data.entities.User
import noblur.com.exomindtest.data.repository.UserDataSource
import noblur.com.exomindtest.data.repository.UserRepository
import noblur.com.exomindtest.homecomponent.HomePageViewModel
import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class HomePageViewModelTest {


    @get:Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var userRepository: UserRepository
    @Captor
    private lateinit var loadTasksCallbackCaptor:
            ArgumentCaptor<UserDataSource.GetUsersCallback>

    private lateinit var homePageViewModel: HomePageViewModel
    private lateinit var users: MutableList<User>


    @Before fun setupHomePageViewModel(){

        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this)

        homePageViewModel = HomePageViewModel(userRepository)

        val user1 = User(1,"name1","username1","email1","phone1","website1")
        val user2 = User(2,"name2","username2","email2","phone2","website2")
        val user3 = User(3,"name3","username3","email3","phone3","website3")


        users = Lists.newArrayList(user1,user2,user3)
    }

   @Test fun loadEmptyUsersFromRepository_EmptyResults(){

        users.clear()

       homePageViewModel.start()

       verify<UserRepository>(userRepository).getUsers(capture(loadTasksCallbackCaptor))
        loadTasksCallbackCaptor.value.onUsersLoaded(users)

       MatcherAssert.assertThat(LiveDataTestUtil.getValue(homePageViewModel.empty), Is.`is`(true))


    }
}