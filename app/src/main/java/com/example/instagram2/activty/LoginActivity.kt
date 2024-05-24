package com.example.instagram2.activty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.instagram2.R
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_activity)

        auth = FirebaseAuth.getInstance();

        val emailEditText: EditText = findViewById(R.id.editTextEmail);
        val senhaEditText: EditText = findViewById(R.id.editTextSenha);
        val loginButton: Button = findViewById(R.id.buttonEntrar);
        val cadastroButton: TextView = findViewById(R.id.textViewCadastrar);
        val barraProgress: ProgressBar = findViewById(R.id.progressBarLogin);


        loginButton.setOnClickListener{
            val email = emailEditText.text.toString().trim();
            val senha = senhaEditText.text.toString().trim();


            if(email.isEmpty() || senha.isEmpty()){
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show();
                return@setOnClickListener
            }
            barraProgress.visibility = View.VISIBLE;
            auth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful){
                        val user = auth.currentUser
                        val intent = Intent(this@LoginActivity, MainActivity::class.java);
                        Toast.makeText(this, "Login Bem-Sucedido", Toast.LENGTH_SHORT).show();
                        barraProgress.visibility = View.GONE;
                        startActivity(intent);
                        // navgar para a proxima tela
                    }else{
                        barraProgress.visibility = View.GONE;
                        Toast.makeText(this, "Falha na autenticação: ${task.exception?.message}", Toast.LENGTH_SHORT).show();
                    }


                }

        }

        cadastroButton.setOnClickListener{
            val intent = Intent(this@LoginActivity, CadastroActivity::class.java);
            startActivity(intent);
        }





    }

}