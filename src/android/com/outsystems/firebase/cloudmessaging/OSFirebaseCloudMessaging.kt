package com.outsystems.firebase.cloudmessaging;

import com.outsystems.plugins.firebasemessaging.controller.FirebaseMessagingController
import com.outsystems.plugins.firebasemessaging.controller.FirebaseMessagingInterface
import com.outsystems.plugins.firebasemessaging.controller.FirebaseMessagingManager
import com.outsystems.plugins.firebasemessaging.model.FirebaseMessagingErrors
import com.outsystems.plugins.sociallogins.CordovaImplementation
import org.apache.cordova.CallbackContext
import org.json.JSONArray

class OSFirebaseCloudMessaging(override var callbackContext: CallbackContext?) : CordovaImplementation() {

    private val controllerDelegate = object: FirebaseMessagingInterface {
        override fun callbackToken(token: String) {
            sendPluginResult(token)
        }
        override fun callbackSuccess() {
            sendPluginResult(true)
        }
        override fun callbackError(error: FirebaseMessagingErrors) {
            sendPluginResult(false, Pair(error.code, error.description))
        }
    }
    private val messaging = FirebaseMessagingManager()
    private val controller = FirebaseMessagingController(controllerDelegate, messaging)

    override fun execute(action: String, args: JSONArray, callbackContext: CallbackContext): Boolean {
        when (action) {
            "getToken" -> {
                controller.getToken()
            }
            "subscribe" -> {
                val topic = ""
                controller.subscribe(topic)
            }
            "unsubscribe" -> {
                val topic = ""
                controller.unsubscribe(topic)
            }
            else -> return false
        }
        return true
    }

    override fun areGooglePlayServicesAvailable(): Boolean {
        TODO("Not yet implemented")
    }

}