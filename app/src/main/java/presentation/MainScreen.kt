package presentation

import Data.UserEntity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

private val Typography.h6: TextStyle
    get() {
        TODO("Not yet implemented")
    }

@Composable
fun MainScreen(viewModel: UserViewModel, navController: NavController) {
    val users by viewModel.users.observeAsState(emptyList())

    LazyColumn {
        items(users) { user -> UserCard(user) {
            navController.navigate("details/${user.id}")
        }
        }
    }
}

@Composable
fun UserCard(user: UserEntity, onClick: () -> Unit) {
    Card(modifier = Modifier.padding(8.dp).clickable(onClick = onClick)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = user.name, style = MaterialTheme.typography.h6)
            Text(text = user.email)
        }
    }
}