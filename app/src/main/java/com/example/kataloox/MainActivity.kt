import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kataloox.RetrofitClient
import com.example.kataloox.ui.theme.KatalooxTheme
import com.example.kataloox.usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            obtenerUsuarios()
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    private fun obtenerUsuarios() {
        var usuarios by remember { mutableStateOf<List<usuario>>(emptyList()) }
        var errorMessage by remember { mutableStateOf<String?>(null) }

        LaunchedEffect(true) {
            try {
                val result = RetrofitClient.apiService.getUsuarios() // llamada a la API
                usuarios = result
            } catch (e: Exception) {
                errorMessage = "Error al obtener usuarios: ${e.message}"
                Log.e("ErrorAPI", errorMessage ?: "Error desconocido")
            }
        }

        Scaffold(
            content = {
                if (errorMessage != null) {
                    Text("Error: $errorMessage", modifier = Modifier.padding(16.dp))
                } else {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(usuarios) { usuario ->
                            Text(
                                "Usuario: ${usuario.id} - ${usuario.usuario} - ${usuario.email}",
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
            }
        )
    }
}
