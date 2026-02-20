package com.thearckay.imccomjetpackcompose

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thearckay.imccomjetpackcompose.ui.theme.IMCComJetpackComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IMCComJetpackComposeTheme() {
                TelaPrincipal()
            }
        }
    }
}
@Composable
fun TelaPrincipal(){
    var sexSelected by remember { mutableStateOf("") }
    var userAge by remember { mutableStateOf("") }
    var userHeight by remember { mutableStateOf("") }
    var userWeight by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("Resultado") }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.padding(20.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Calculadora IMC",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
        Row(
            modifier = Modifier
//                .background(Color.Blue)
                .height(40.dp)
                .fillMaxSize()
                .padding(10.dp),

        ) {
            Text("Informe seu Sexo:")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = sexSelected == "Masculino",
                onClick = { sexSelected = "Masculino" }
            )
            Text("Masculino")

            RadioButton(
                selected = sexSelected == "Feminino",
                onClick = { sexSelected = "Feminino"}
            )
            Text("Feminino")
        }
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                modifier = Modifier.padding(20.dp, 20.dp, 20.dp, 10.dp).fillMaxWidth(),
                value = userAge,
                onValueChange = { userAge = it },
                label = { Text("Informe sua idade") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                maxLines = 1
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(20.dp,10.dp),
                value = userHeight,
                onValueChange = { userHeight = it},
                label = { Text("Informe sua Altura") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                maxLines = 1
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(20.dp,10.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = userWeight,
                onValueChange = { userWeight = it },
                label = { Text("Informe seu peso")},
                maxLines = 1
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.padding(10.dp, 0.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = result,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row() {
            Button(
                modifier = Modifier.fillMaxWidth().padding(20.dp,20.dp),
                onClick = {
                    val informationsIsValid = checkInformations(context, sexSelected, userAge, userHeight, userWeight)

                    if(informationsIsValid) {
                        val imc = calcIMC(userWeight, userHeight)
                        val imcResult = verifyIMCByAge(imc!!, userAge.toInt(), sexSelected)
                        result = imcResult
                    }


                },
            ) {
                Text("Calcular")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TelaPrincipalPrev(){
    IMCComJetpackComposeTheme() {
        TelaPrincipal()
    }
}

private fun checkInformations(context: Context, sexString: String, ageString: String, heightStrin: String, weightString: String): Boolean {

    if(sexString.isBlank()) {
        Toast.makeText(context, "Informe seu Sexo", Toast.LENGTH_SHORT).show()
        return false
    }

    if(ageString.isBlank()) {
        Toast.makeText(context, "Informe sua idade", Toast.LENGTH_SHORT).show()
        return false
    }

    if(heightStrin.isBlank()) {
        Toast.makeText(context, "Informe sua Altura", Toast.LENGTH_SHORT).show()
        return false
    }

    if(weightString.isBlank()) {
        Toast.makeText(context, "Informe seu peso", Toast.LENGTH_SHORT).show()
        return false
    }

    val age = ageString.replace(",", ".").toIntOrNull() ?:0
    if(age <6 || age > 110){
        Toast.makeText(context, "Informe uma idade válida - entre 6 e 110 anos", Toast.LENGTH_SHORT).show()
        return false
    }

    val height = heightStrin.replace(",", ".").toDoubleOrNull() ?: 0.0
    if (height > 2.3 || height < 0.5 ){
        Toast.makeText(context, "Informe uma altura válida - entre 50cm e 2.3 metros", Toast.LENGTH_SHORT).show()
        return false
    }

    return true
}
private fun calcIMC(weightString: String, heightString: String): Double?{
    val height = heightString.replace(",", ".").toDoubleOrNull() ?: 0.0
    val weight = weightString.replace(",", ".").toDoubleOrNull() ?: 0.0

    val imc = weight / (height * height)

    return imc
}

private fun verifyIMCByAge(imc: Double, age: Int, gender: String): String {
    return if (gender == "Masculino") {
        when (age) {
            6 -> calc(imc, 14.5, 16.7, 18.0)
            7 -> calc(imc, 15.0, 17.4, 19.1)
            8 -> calc(imc, 15.6, 16.8, 20.3)
            9 -> calc(imc, 16.1, 18.9, 21.4)
            10 -> calc(imc, 16.7, 19.7, 22.5)
            11 -> calc(imc, 17.2, 20.4, 23.7)
            12 -> calc(imc, 17.8, 21.2, 24.8)
            13 -> calc(imc, 18.5, 22.0, 25.9)
            14 -> calc(imc, 19.2, 22.8, 26.9)
            15 -> calc(imc, 19.9, 23.7, 27.7)
            16 -> calc(imc, 20.5, 24.4, 28.8)
            17 -> calc(imc, 21.1, 25.2, 29.4)
            in 18..110 -> calc(imc, 21.7, 25.7, 30.0)
            else -> "Idade inválida"
        }
    } else {
        when (age) {
            6 -> calc(imc, 14.3, 16.2, 17.4)
            7 -> calc(imc, 14.9, 17.2, 18.9)
            8 -> calc(imc, 15.6, 18.2, 20.3)
            9 -> calc(imc, 16.3, 19.2, 21.7)
            10 -> calc(imc, 17.0, 20.2, 23.2)
            11 -> calc(imc, 17.6, 21.2, 24.5)
            12 -> calc(imc, 18.3, 22.2, 25.9)
            13 -> calc(imc, 18.9, 23.1, 27.7)
            14 -> calc(imc, 19.3, 23.9, 27.9)
            15 -> calc(imc, 19.6, 24.3, 28.8)
            16 -> calc(imc, 19.9, 25.0, 29.6)
            17 -> calc(imc, 20.2, 25.5, 30.1)
            in 18..110 -> calc(imc, 20.4, 25.8, 30.3)
            else -> "Idade inválida"
        }
    }
}
private fun calc(imc: Double, thresholdBelow: Double, thresholdNormal: Double, thresholdOverweight: Double): String {
    return when {
        imc < thresholdBelow -> "Abaixo do peso"
        imc < thresholdNormal -> "Normal"
        imc <= thresholdOverweight -> "Sobrepeso"
        else -> "Obesidade"
    }
}
