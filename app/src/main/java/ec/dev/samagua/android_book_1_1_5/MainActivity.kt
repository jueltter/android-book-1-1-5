package ec.dev.samagua.android_book_1_1_5

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ec.dev.samagua.android_book_1_1_5.ui.theme.Androidbook115Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Androidbook115Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GreetingScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun GreetingScreen(modifier: Modifier = Modifier) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var greeting by remember { mutableStateOf("") }
    val baseGreeting = stringResource(R.string.welcome_to_the_app)
    val toastText = stringResource(R.string.please_enter_a_name)
    val context = LocalContext.current;


    androidx.constraintlayout.compose.ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        val (firstNameField, lastNameField, enterButton, greetingText) = createRefs()

        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text(stringResource(R.string.first_name_text)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .constrainAs(firstNameField) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text(stringResource(R.string.last_name_text)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .constrainAs(lastNameField) {
                    top.linkTo(firstNameField.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Button(
            onClick = {
                greeting = "";

                val tmp1 = firstName.trim();
                val tmp2 = lastName.trim();
                if (tmp1.isNotEmpty() && tmp2.isNotEmpty()) {
                    greeting = "$baseGreeting $firstName $lastName!"
                }
                else {
                    Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
                }


            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,  // button color
                contentColor = Color.White  // Text color
            ),
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(enterButton) {
                    top.linkTo(lastNameField.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Text(stringResource(R.string.enter_button_text))
        }

        Text(
            text = greeting,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(top = 16.dp)
                .constrainAs(greetingText) {
                    top.linkTo(enterButton.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingScreenPreview() {
    GreetingScreen()
}