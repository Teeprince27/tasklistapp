package com.temitope.temilistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.temitope.temilistapp.ui.theme.TemiListAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TemiListAppTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}


@Composable
fun MyApp(modifier: Modifier = Modifier) {
   var showOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        if (showOnboarding) {
            OnboardingScreen(onContinueClicked = { showOnboarding = false })
        } else {
            Greetings()
        }
    }
}

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    onContinueClicked: () -> Unit = {}
)
 {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to My Application")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text( "Continue")
        }

    }
}

@Composable
fun Greetings(
    names: List<String> = List(1000) { "$it" },
    modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.padding(vertical = 4.dp)
    ) {
        items(names) { name ->
            Greeting(name)
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name)
    }
}


@Composable
fun CardContent(names: String){
var expandable by rememberSaveable { mutableStateOf(false) }

    Row (modifier = Modifier
        .padding(12.dp)
        .animateContentSize(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
    ){
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ){
            Text(text = "We are at No")
            Text(text = names,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                ))

            if (expandable) {
                Text(
                    text = ("Nigeria is a great country , " +
                            "We just haven't gotten the best leader. ").repeat(4),
                )
            }
        }

        IconButton(onClick = { expandable = !expandable }) {
            Icon(imageVector = if (expandable) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore ,
                contentDescription = if(expandable) {"Show More"} else{"Show Less"} )
        }
    }
}




@Preview
@Composable
fun MyAppPreview() {
    TemiListAppTheme {
        MyApp(modifier = Modifier.fillMaxSize())
    }
}

@Preview
@Composable
fun OnboardingPreview() {
    TemiListAppTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TemiListAppTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingsPreview() {
    TemiListAppTheme {
        Greetings()
    }
}