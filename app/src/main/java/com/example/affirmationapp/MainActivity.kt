package com.example.affirmationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.affirmationapp.data.Datasource
import com.example.affirmationapp.model.Affirmation
import com.example.affirmationapp.ui.theme.AffirmationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AffirmationAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AffirmationsApp()
                }
            }
        }
    }
}

@Preview
@Composable
fun AffirmationsApp() {
    AffirmationList(affirmationList = Datasource().loadAffirmations(),)
}
@Composable
fun AffirmationCard(
    affirmation: Affirmation,
    modifier: Modifier = Modifier
) {
     Card(
         modifier = modifier.fillMaxWidth(),
         shape = RoundedCornerShape(15.dp),
     ) {
         Column {
             Image(
                 painter = painterResource(id = affirmation.imageResourceId),
                 contentDescription = stringResource(id = affirmation.stringResourceId),
                 modifier = Modifier
                     .fillMaxWidth()
                     .height(194.dp),
                 contentScale = ContentScale.Crop
             )
             Text(
                 text = LocalContext.current.getString(affirmation.stringResourceId),
                 modifier = Modifier.padding(16.dp),
                 style = MaterialTheme.typography.headlineSmall
             )
         }
     }
}
@Preview
@Composable
private fun AffirmationCardPreview() {
    AffirmationCard(affirmation = Affirmation(R.string.affirmation1, R.drawable.image1))
}

@Composable
fun AffirmationList(
    affirmationList: List<Affirmation>,
    modifier: Modifier = Modifier
) {

    LazyColumn(modifier = modifier) {
        item {
            Text(
                text = "Affirmations",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.displayMedium
            )
        }
        items(affirmationList) {affirmation ->
            AffirmationCard(
                affirmation = affirmation,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}