package com.project.bipos.presentation.login

import com.google.firebase.database.*
import com.project.bipos.model.User

class LoginPresenter(private val view: LoginContract.View) : LoginContract.Presenter {

    var reference: DatabaseReference? = null

    override fun start() {
        view.initView()
    }

    override fun loginUser(username: String, password: String) {
        reference = FirebaseDatabase.getInstance().reference.child("users").child(username).child("data")
        reference!!.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val pass: String = dataSnapshot.child("password").value.toString()

                if (dataSnapshot.exists()) {
                    if (password == pass) {
                        view.showLoginSuccess(dataSnapshot)
                    } else {
                        view.showMessage("password salah")
                    }
                } else {
                    view.showMessage("username tidak tersedia")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }

}