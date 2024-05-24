package com.example.instagram2.activty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.instagram2.R
import com.example.instagram2.helper.ConfigFirebase
import com.example.instagram2.model.User
import com.google.firebase.auth.FirebaseAuth

class CadastroActivity : AppCompatActivity() {


    private lateinit var emailCadastroEditText: EditText
    private lateinit var senhaCadastroEditText: EditText
    private lateinit var userNameCadastroEditText: EditText
    private lateinit var buttonCadastrar: Button

    private lateinit var user: User

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_actvity)


        iniciarComponentes();



        buttonCadastrar.setOnClickListener{

            val textoUser = userNameCadastroEditText.text.toString().trim()
            val textoEmail = emailCadastroEditText.text.toString().trim()
            val textoSenha = senhaCadastroEditText.text.toString().trim()

            if (!textoUser.isEmpty() || !textoSenha.isEmpty() || !textoSenha.isEmpty()){
                user = User(nome = textoUser, email = textoEmail, senha = textoSenha)
                cadastrarUsuario(user)

            }else{
                Toast.makeText(this, "Preencha todos os campos para realizar o cadastro", Toast.LENGTH_SHORT).show()

            }

        }

    }

    public fun iniciarComponentes(){
        emailCadastroEditText = findViewById(R.id.editTextEmailCadastro);
        senhaCadastroEditText = findViewById(R.id.editTextSenhaCadastro);
        userNameCadastroEditText = findViewById(R.id.editTextUser);
        buttonCadastrar = findViewById(R.id.buttonCadastrarCadastro);
    }

    public fun cadastrarUsuario(user:User){

        auth = ConfigFirebase.getFirebaseAuth()
        auth.createUserWithEmailAndPassword(user.email, user.senha).addOnCompleteListener(this){
            task ->
            if (task.isSuccessful){
                Toast.makeText(this, "User criado com sucesso", Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(this, "Erro ao cadastrar user: ${task.exception?.message}", Toast.LENGTH_SHORT).show()

            }
        }

    }
}