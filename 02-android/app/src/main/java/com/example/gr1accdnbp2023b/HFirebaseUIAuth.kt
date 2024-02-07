package com.example.gr1accdnbp2023b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.IdpResponse
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth

class HFirebaseUIAuth : AppCompatActivity() {
    //Callback de Intent de Login
    private val respuestaLoginAuthUI = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res: FirebaseAuthUIAuthenticationResult ->
        if (res.idpResponse != null) {
            //Lógica de negocio
            seLogeo(res.idpResponse!!)
        }
    }

    fun seLogeo(
        res: IdpResponse
    ) {
        val btnLogin = findViewById<Button>(R.id.btn_login_firebase)
        val btnLogout = findViewById<Button>(R.id.btn_logout_firebase)
        val tvBienvenido = findViewById<TextView>(R.id.tv_bienvenido)
        tvBienvenido.text = FirebaseAuth.getInstance().currentUser?.displayName
        btnLogout.visibility = View.VISIBLE
        btnLogin.visibility = View.INVISIBLE
        if (res.isNewUser == true) {
            registrarUsuarioPorPrimeraVez(res)
        }
    }

    fun registrarUsuarioPorPrimeraVez(usuerios: IdpResponse) {
        /*
        usuario.email;
        usuario.phoneNumber;
        usuario.user.name;
         */
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hfirebase_uiauth)

        val btnLogin = findViewById<Button>(R.id.btn_login_firebase)
        btnLogin.setOnClickListener {
            // Arreglo de providers
            val providers = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build())

            // Construimos el intent de login
            val logearseIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build()
            // RESPUESTA DEL INTENT DE LOGIN
            respuestaLoginAuthUI.launch(logearseIntent)
        }
        val btnLogout = findViewById<Button>(R.id.btn_logout_firebase)
        btnLogout.setOnClickListener{ seDeslogeo()}
        // Lógica si destruye el aplicativo
        val usuario = FirebaseAuth.getInstance().currentUser
        if (usuario != null) {
            val tvBienvenido = findViewById<TextView>(R.id.tv_bienvenido)
            val btnLogin : Button = findViewById<Button>(R.id.btn_login_firebase)
            val btnLogout : Button = findViewById<Button>(R.id.btn_logout_firebase)
            btnLogout.visibility = View.VISIBLE
            btnLogin.visibility = View.INVISIBLE
            tvBienvenido.text = usuario.displayName
        }
    }

    fun seDeslogeo() {
        val tvBienvenido = findViewById<TextView>(R.id.tv_bienvenido)
        val btnLogin : Button = findViewById<Button>(R.id.btn_login_firebase)
        val btnLogout : Button = findViewById<Button>(R.id.btn_logout_firebase)
        tvBienvenido.text = "Bienvenido"
        btnLogout.visibility = View.INVISIBLE
        btnLogin.visibility = View.VISIBLE
        FirebaseAuth.getInstance().signOut()
    }
}