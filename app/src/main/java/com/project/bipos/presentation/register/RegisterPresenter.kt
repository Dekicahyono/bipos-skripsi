package com.project.bipos.presentation.register

import com.google.firebase.database.*
import com.project.bipos.model.Order
import com.project.bipos.model.User

class RegisterPresenter(private val view: RegisterContract.View) : RegisterContract.Presenter {

    private var reference: DatabaseReference? = null

    override fun start() {
        view.initView()
    }

    override fun postRegister(user: User) {
        view.showLoading()
        reference = FirebaseDatabase.getInstance().reference.child("users").child(user.username).child("data")
        reference!!.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                view.hideLoading()
                if (dataSnapshot.exists()) {
                    view.showMessage("username tidak tersedia")
                } else {
                    dataSnapshot.ref.setValue(user)
                    view.showUserRegisterSuccess()
                    view.showMessage("Register sukses")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }

}