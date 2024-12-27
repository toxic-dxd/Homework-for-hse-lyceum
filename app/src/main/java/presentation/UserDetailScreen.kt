package presentation

import Data.UserEntity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp


private val Typography.h4: TextStyle
    get() {
        TODO("Not yet implemented")
    }

@Composable
fun UserDetailScreen(viewModel: UserViewModel, userId: Int) {
    val userDetails = remember { mutableStateOf<UserEntity?>(null) }

    LaunchedEffect(userId) {
        userDetails.value = viewModel.getUserDetails(userId)
    }

    userDetails.value?.let { user ->
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = user.name, style = MaterialTheme.typography.h4)
            Text(text = user.email)
        }
    }
}