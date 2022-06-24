package com.example.mobileapp

import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.app.ActivityCompat
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val mapsButton = findViewById<Button>(R.id.mapsButton)
        val welcomeSign = findViewById<TextView>(R.id.welcomeSign)
        val sendSMS = findViewById<Button>(R.id.sendSMS)
        val dateOfArrival = findViewById<EditText>(R.id.dateArr)
        val dateOfDeparture = findViewById<EditText>(R.id.dateDep)
        val phoneNumber = findViewById<EditText>(R.id.phoneNumber)
        val email = findViewById<EditText>(R.id.email)
        val houseNum = findViewById<EditText>(R.id.houseNum)
        val additionalInfo = findViewById<EditText>(R.id.additionalInfo)
        val contactInfo = findViewById<Button>(R.id.contactInfo)

        val loggedUser = intent.getStringExtra("loginShow")
        welcomeSign.text = "Welcome " + loggedUser.toString() + "!"

        mapsButton.setOnClickListener{
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }

        checkPermission()

        sendSMS.setOnClickListener {
            val customer = Customer(
                dateArr = dateOfArrival.text.toString(),
                dateDep = dateOfDeparture.text.toString(),
                phoneNum = phoneNumber.text.toString(),
                email = email.text.toString(),
                houseNum = houseNum.text.toString(),
                additionalInfo = additionalInfo.text.toString()
            )

            val message = "Date of arrival: " + customer.dateArr + "\n" +
                    "Date of departure: " + customer.dateDep + "\n" +
                    "Phone number: " + customer.phoneNum + "\n" +
                    "Email: " + customer.email + "\n" +
                    "Number of houses: " + customer.houseNum + "\n" +
                    "Additional info: " + customer.additionalInfo
            Log.d("sms_data", message)
            sendSMS("+48123456789", message)
        }

        contactInfo.setOnClickListener {
            val intent = Intent(this, ContactInfoActivity::class.java)
            startActivity(intent)
            onPause()
        }



    }

    private fun sendSMS(phoneNumber: String, message: String) {
        val sentPI: PendingIntent = PendingIntent.getBroadcast(this, 0, Intent("SMS_SENT"), 0)
        SmsManager.getDefault().sendTextMessage(phoneNumber, null, message, sentPI, null)
    }

    private fun checkPermission(){
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.SEND_SMS), 101)
        }
    }
}