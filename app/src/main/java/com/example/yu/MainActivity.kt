package com.example.yu

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.yu.ui.theme.YuTheme
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.yu.ui.theme.monsteraatFontfamily

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val quizQuestions = listOf(
                        Question(
                            text = "Which of these best describes your physical frame?",
                            options = listOf("Small", "Medium", "Large")
                        ),
                        Question(
                            text = "Which best describes your skin?",
                            options = listOf("Oily and soft with freckles or pimples", "Thick, oily, cool skin", "Dry")
                        ),
                        Question(
                            text = "Which best describes your hair?",
                            options = listOf("Straight and fine", "Thick and lustrous", "Dry and curly")
                        ),
                        Question(
                            text = "Which best describes your eyes?",
                            options = listOf("Large, pretty", "Small and dry", "Medium-sized; intense gaze")
                        ),
                        Question(
                            text = "Which best describes how you talk?",
                            options = listOf("Fast and/or a lot!", "My words are sharp and concise.", "My speech is slow and calm.")
                        ),
                        Question(
                            text = "What type of weather is your favorite?",
                            options = listOf("Warm", "Cool", "Cool and dry")
                        ),
                        Question(
                            text = "How is your memory?",
                            options = listOf("I learn quickly, but I also forget quickly.", "I have a great memory!", "It takes me a while to commit something to memory, but once I do I don’t forget it.")
                        ),
                        Question(
                            text = "Which best describes your personality?",
                            options = listOf("Responsible, nurturing, and sensitive", "Creative, joyful, and introspective", "Competitive, perceptive, and efficient")
                        ),
                        Question(
                            text = "Which of these traits do you most identify with?",
                            options = listOf("I can be pretty stubborn.", "I get jealous easily.", "I’m often indecisive.")
                        ),
                        Question(
                            text = "How about these traits? Which sounds the most like you?",
                            options = listOf("I’m very intuitive.", "I’m quite brave.", "I’m a loyal, faithful friend.")
                        ),
                        Question(
                            text = "And these? Which sounds the most like you?",
                            options = listOf("I’m often restless.", "I can be irritable and impatient.", "I’m a loyal, faithful friend.")
                        ),
                        Question(
                            text = "When you’re under stress do you tend to experience insomnia?",
                            options = listOf("Yes", "No")
                        ),
                        Question(
                            text = "When you’re under stress do you tend to feel complacent?",
                            options = listOf("Yes", "No")
                        )
                        // Add more questions as needed
                    )
                    QuizApp(questions = quizQuestions)

                }
            }
        }
    }
}



data class Question(val text: String, val options: List<String>)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun QuizApp(questions: List<Question>) {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var kapha by remember { mutableStateOf(0) }
    var pitta by remember { mutableStateOf(0) }
    var vata by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Know Your Dosha")
                }
            )
        },
//        bottomBar = {
//            BottomAppBar(
//                containerColor = MaterialTheme.colorScheme.primaryContainer,
//                contentColor = MaterialTheme.colorScheme.primary,
//            ) {
//                Text(
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    textAlign = TextAlign.Center,
//                    text = "Bottom app bar",
//                )
//            }
//        },

        ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            //=================================
            if (currentQuestionIndex < questions.size) {
                QuizQuestion(
                    question = questions[currentQuestionIndex],
                    selectedAnswer = selectedAnswer,
                    onOptionSelected = {
                        selectedAnswer = it
                    }
                )
                val i = questions[currentQuestionIndex].options.indexOf(selectedAnswer)

                Spacer(modifier = Modifier.height(10.dp))
                ElevatedButton(

                    onClick = {
                        if (selectedAnswer != null) {

                                // Correct answer

                                when (i) {
                                    0 -> {
                                        vata++
                                    }
                                    1 -> {
                                        kapha++
                                    }
                                    2 -> {
                                        pitta++
2
                                    }
                                    else -> {
                                        println("Default case: i is not 0, 1, or 2")
                                        // Add code for default case
                                    }
                                }

                            currentQuestionIndex++
                            selectedAnswer = null
                        }
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()

                ) {

                    Text(if (currentQuestionIndex < questions.size - 1) "Next Question" else "Finish")
                }
            } else {
                // Display the final score or navigate to a different screen
                QuizResult(kapha = kapha, vitta = vata, pitta = pitta)
            }

        }
    }
}



@Composable
fun QuizQuestion(question: Question, selectedAnswer: String?, onOptionSelected: (String) -> Unit) {
    Column(
        modifier = Modifier

            .padding(16.dp)
    ) {
        Text(text = question.text, style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(bottom = 16.dp))

        question.options.forEach { option ->
            QuizOption(
                text = option,
                selected = option == selectedAnswer,
                onOptionSelected = { onOptionSelected(option) }
            )

        }
    }
}

@Composable
fun QuizOption(text: String, selected: Boolean, onOptionSelected: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(3.dp)
            .clip(RoundedCornerShape(12.dp))
            .padding(8.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable { onOptionSelected() }
            .background(if (selected) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.surface),
//        border = BorderStroke(
//            width = 1.dp,
//            color = if (selected) {
//                MaterialTheme.colorScheme.primary
//            } else {
//                MaterialTheme.colorScheme.outline
//            }
//        )
        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(selected, onClick = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = text, style = MaterialTheme.typography.headlineSmall)
        }
    }
}

@Composable
fun QuizResult(kapha: Int,vitta:Int,pitta:Int) {
    Surface(shadowElevation = 10.dp) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Your Dosha Score",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Kapha: $kapha",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Viita: $vitta",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Pitta : $pitta",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        // You can add more UI elements or actions for the result screen
    }
    }
}


@Composable
@Preview(showBackground = true)
fun QuizAppPreview() {
    val quizQuestions = listOf(
        Question(
            text = "Which of these best describes your physical frame?",
            options = listOf("Small", "Medium", "Large")
        ),
        Question(
            text = "Which best describes your skin?",
            options = listOf("Oily and soft with freckles or pimples", "Thick, oily, cool skin", "Dry")
        ),
        Question(
            text = "Which best describes your hair?",
            options = listOf("Straight and fine", "Thick and lustrous", "Dry and curly")
        ),
        Question(
            text = "Which best describes your eyes?",
            options = listOf("Large, pretty", "Small and dry", "Medium-sized; intense gaze")
        ),
        Question(
            text = "Which best describes how you talk?",
            options = listOf("Fast and/or a lot!", "My words are sharp and concise.", "My speech is slow and calm.")
        ),
        Question(
            text = "What type of weather is your favorite?",
            options = listOf("Warm", "Cool", "Cool and dry")
        ),
        Question(
            text = "How is your memory?",
            options = listOf("I learn quickly, but I also forget quickly.", "I have a great memory!", "It takes me a while to commit something to memory, but once I do I don’t forget it.")
        ),
        Question(
            text = "Which best describes your personality?",
            options = listOf("Responsible, nurturing, and sensitive", "Creative, joyful, and introspective", "Competitive, perceptive, and efficient")
        ),
        Question(
            text = "Which of these traits do you most identify with?",
            options = listOf("I can be pretty stubborn.", "I get jealous easily.", "I’m often indecisive.")
        ),
        Question(
            text = "How about these traits? Which sounds the most like you?",
            options = listOf("I’m very intuitive.", "I’m quite brave.", "I’m a loyal, faithful friend.")
        ),
        Question(
            text = "And these? Which sounds the most like you?",
            options = listOf("I’m often restless.", "I can be irritable and impatient.", "I’m a loyal, faithful friend.")
        ),
        Question(
            text = "When you’re under stress do you tend to experience insomnia?",
            options = listOf("Yes", "No")
        ),
        Question(
            text = "When you’re under stress do you tend to feel complacent?",
            options = listOf("Yes", "No")
        )
        // Add more questions as needed
    )

    MaterialTheme {
        QuizApp(questions = quizQuestions)
    }
}


